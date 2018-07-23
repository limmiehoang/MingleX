<%@page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="cg" uri="/WEB-INF/tld/customTagLibrary" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Chat">
    <div class="flex-container" style="padding-top: 53px;">

        <!-- Navbar -->
        <jsp:include page="/WEB-INF/components/navbar.jsp" >
            <jsp:param name="active" value="Chat"/>
        </jsp:include>

        <%--Chatroom Sidebar--%>
        <div class="sidebar alice-blue w3-bar-block">
            <div class="chatroom-sidebar">
                <c:forEach var="chatmate" items="${chatmates}">
                    <a class="chat-item" href="#<c:out value="${chatmate.id}"/>"><c:out value="${chatmate.username}"/></a>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- Chatroom Content -->
    <div class="container chat with-sidebar">
        <div id="chatroom">
        </div>
        <div id="invite-message" class="hidden">
            <p>You haven't chatted yet. Do you want to connect with <span class="chatmate-username"></span>?</p>
                <input type="submit" id="invite" class="btn btn-primary" value="Mingle">
        </div>
        <div id="accept-message" class="hidden">
            <p><span class="chatmate-username"></span> wants to connect with you.</p>
                <input type="submit" id="accept" class="btn btn-primary" value="Ready to mingle!">
        </div>
        <div id="wait-message" class="hidden">
            <p>You sent an invite to <span class="chatmate-username"></span>. Waiting for <span class="chatmate-username"></span>'s response...</p>
        </div>
        <div id="connect-message" class="hidden">
            <p><span class="chatmate-username"></span> has accepted your invite.</p>
                <input type="submit" id="connect" class="btn btn-primary" value="OK">
        </div>
        <div class="chatbox hidden">
            <div class="form-group row">
                <div class="col-md-11 field">
                    <textarea rows="2"
                                placeholder="Type a message..."
                                id="chatbox"
                                class="form-control"></textarea>
                </div>

                <div class="col-md-1 field">
                    <input type="submit" id="sendMessage" class="btn btn-primary" value="Send">
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
    <script src="js/secure-random.js"></script>
    <script src="js/key-exchange-tools.js"></script>
    <script>
        var roomId = null;
        var chatmateId = null;
        var chatmateUsername = null;
        var invite_message = null;
        var invite_feedback = null;
        // when user click on sidebar link
        $(".chatroom-sidebar a").on("click", function() {
            // set active for this element (link of user is chatting with)
            $(".chatroom-sidebar a").each(function() {
                $(this).removeClass("active");
            })
            if(!$(this).hasClass("active")) {
                $(this).addClass("active");
            }

            // clean the message area
            resetMessageArea()

            chatmateId = this.href.split("#")[1];
            chatmateUsername = this.innerHTML;

            console.log({"chatmateId": chatmateId});
            $.ajax({
                url: "/room/chatmate",
                method: "GET",
                data: {
                    chatmateId: chatmateId
                },
                success: function(res) {
                        console.log(res);
                        responseHandler(res);
                }
            })
            scrollToBottom();
        })
        
        function responseHandler(res) {
            console.log({responseHandler: res.status});
            switch (res.status) {
                case "waiting":
                    $(".chatbox").addClass("hidden");
                    $("#wait-message").removeClass("hidden");
                    $(".chatmate-username").text(chatmateUsername);
                    break;
                case "invited":
                    $(".chatbox").addClass("hidden");
                    $("#accept-message").removeClass("hidden");
                    $(".chatmate-username").text(chatmateUsername);
                    invite_message = res.invite.message;
                    break;
                case "accepted":
                    $(".chatbox").addClass("hidden");
                    $("#wait-message").addClass("hidden");
                    $("#connect-message").removeClass("hidden");
                    $(".chatmate-username").text(chatmateUsername);
                    invite_feedback = res.invite.feedback;
                    break;
                case "unconnected":
                    $(".chatbox").addClass("hidden");
                    $("#invite-message").removeClass("hidden");
                    $(".chatmate-username").text(chatmateUsername);
                    break;
                case "connected":
                    $(".chatbox").removeClass("hidden");
                    $("#chatroom").removeClass("hidden");
                    roomId = res.chatroom.id;
                    enableMessageSender(res, roomId);
                    viewMessage(res);
                    break;
                default:
                    break;
            }
        }

        function viewMessage(res) {

            // remove old messages
            $("#chatroom").empty();

            var sender = $(location).attr("href").split("#")[1];
            var messages = res.messages; // message array
            if (messages == null) {
                return;
            }
            messages.forEach(message => {
                console.log(message);
                // if message is of current user, show in left side
                // if message is of another user, show in right side
                if(message.sender.id != sender) {
                    var msgElement = `<div class="message right">` +
                                    `<p><span>` + message.content +
                                    `</span></p></div>`;
                } else {
                    var msgElement = `<div class="message left">` +
                                    `<p><span>` + message.content +
                                    `</span></p></div>`;
                }
                $("#chatroom").append(msgElement);
            });
        }

        $("#invite").on("click", function() {
            var url = "/room/invite";
            var cur_url = $(location).attr("href");
            var user_id = cur_url.split("#")[1];
            var p = generate_prime(16);
            var g = first_primitive_root(p);
            var my_x = generate_private_number(32);
            localStorage.setItem("pn" + chatmateId, my_x);
            var my_y = mod_exp(g, my_x, p);
            postHandler(url, {
                recipient: {
                    id: user_id
                },
                message: g + "," + p + "," + my_y
            })
        })

        $("#accept").on("click", function() {
            var url = "/room/accept";
            var cur_url = $(location).attr("href");
            var user_id = cur_url.split("#")[1];
            var g = invite_message.split(",")[0];
            var p = invite_message.split(",")[1];
            var y = invite_message.split(",")[2];
            var my_x = generate_private_number(32);
            var my_y = mod_exp(g, my_x, p);
            var key = mod_exp(y, my_x, p);
            localStorage.setItem("k" + chatmateId, key);
            postHandler(url, {
                chatmate: {
                    id: user_id
                },
                invite: {
                    feedback: my_y + "," + p
                }
            })
        })
        
        $("#connect").on("click", function() {
            var url = "/room/connect";
            var cur_url = $(location).attr("href");
            var user_id = cur_url.split("#")[1];
            var y = invite_feedback.split(",")[0];
            var p = invite_feedback.split(",")[1];
            var my_x = localStorage.getItem("pn" + chatmateId);
            var key = mod_exp(y, my_x, p);
            localStorage.setItem("k" + chatmateId, key);
            postHandler(url, {
                chatmate: {
                    id: user_id
                }
            })
        })

        function postHandler(url, data) {
            console.log({url, data})
            $.ajax({
                url: url,
                method: "POST",
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(res) {
                    console.log(res);
                    resetMessageArea();
                    responseHandler(res);
                }
            })
        }

        function resetMessageArea() {
            // clean the message area
            $(".chat > div").each(function() {
                if (!$(this).hasClass("hidden") && !$(this).hasClass("chatbox")) {
                    $(this).addClass("hidden");                                
                }
            })
        }

        function enableMessageSender(res, roomId) {
            $("#sendMessage").unbind("click").on("click", function() {
                sendMessage(roomId);
            })
        }

        function sendMessage(roomId) {
            var message = $("#chatbox").val(); // get message from textarea
            $("#chatbox").val("");            // remove message from textarea
            if(message.trim() == "") return;

            // show message just be sent
            var msgElement = `<div class="message right">` +
                            `<p><span>` + message +
                            `</span></p></div>`;
            $("#chatroom").append(msgElement);
            scrollToBottom();

            var url = "/room/chat";
            var cur_url = $(location).attr("href");
            var user_id = cur_url.split("#")[1];
            var data = {
                chatroom: {
                    id: roomId
                },
                content: message
            }
            $.ajax({
                url: url,
                method: "POST",
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function(res) {
                    console.log(res);
                }
            })
        }
        function updateMessage(user_id) {
            if(typeof(user_id) == "undefined") return;
            console.log("Update message! user_id=" + user_id);
            $.ajax({
                url: "/room/chatmate",
                method: "GET",
                data: {
                    chatmateId: user_id
                },
                success: function(res) {
                        console.log(res);
                        responseHandler(res);
                }
            })
        }

        // Auto update message after 6s
        setInterval(function() {
            var user_id = $(location).attr("href").split("#")[1];
            updateMessage(user_id);
        }, 6000)

        function scrollToBottom() {
            $("#chatroom").animate({ scrollTop: 9999 }, 100);
        }

        $("#chatbox").on("keydown", function(e) {
            if(e.keyCode == 13) {
                console.log("Press enter in message box => Send the message ...");
                sendMessage(roomId);
            }
        })
    </script>
</t:wrapper>