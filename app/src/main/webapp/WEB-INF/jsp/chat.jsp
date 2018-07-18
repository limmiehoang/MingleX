<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
                <a class="chat-item active">
                    Alice
                </a>
                <a class="chat-item">
                    Bob
                </a>
                <a class="chat-item">
                    Clever
                </a>
            </div>
        </div>
    </div>

    <!-- Chatroom Content -->
    <div class="container chat with-sidebar">
        <div class="chatroom">
            <div class="message left">
                <p><span>Hi there!</span></p>
            </div>

            <div class="message right">
                <p><span>Hi there!</span></p>
            </div>

            <div class="message left">
                <p><span>Hi there!</span></p>
            </div>
        </div>
        <div class="chatbox">
            <form action="/" method="POST">
                <div class="form-group row">
                    <div class="col-md-11 field">
                        <textarea rows="2"
                                       placeholder="Share your message here..." class="form-control"></textarea>
                    </div>

                    <div class="col-md-1 field">
                        <input type="submit" id="submit" class="btn btn-primary" value="Send">
                    </div>
                </div>
            </form>
        </div>
    </div>


</t:wrapper>