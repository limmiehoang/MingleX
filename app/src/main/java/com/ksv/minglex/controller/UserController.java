package com.ksv.minglex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ksv.minglex.service.ParsingXmlService;
import com.ksv.minglex.storage.StorageFileNotFoundException;
import com.ksv.minglex.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ksv.minglex.model.Status;
import com.ksv.minglex.model.User;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.service.StatusService;
import com.ksv.minglex.service.UserService;
import com.ksv.minglex.setting.SecuritySetting;
import com.ksv.minglex.storage.StorageException;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private SecuritySetting securitySetting;

    private final StorageService storageService;
    @Autowired
    public UserController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    ParsingXmlService parsingXmlService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginView(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user != null) {
			return new ModelAndView("redirect:/profile");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User curUser = new User();
		curUser.setUsername(request.getParameter("username"));
		curUser.setPassword(request.getParameter("password"));
		curUser.setGender(request.getParameter("gender"));

		// Validation
		if (curUser.getUsername() == null || curUser.getUsername().length() == 0) {
			modelAndView.addObject("errorMessage", "Username is required");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		if (curUser.getPassword() == null || curUser.getPassword().length() == 0) {
			modelAndView.addObject("errorMessage", "Password is required");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		// Authentication
		User resUser = userService.authenticateUser(curUser);
		if (resUser != null) {
			// Session fixation
			sessionService.sessionFixation(request);
			sessionService.setCurrentUser(request, resUser);
			return new ModelAndView("redirect:/profile");
		} else {
			// Has error
			modelAndView.addObject("errorMessage", "FAILED");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user",
					"There is already a user registered with the username provide");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			return new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profileView(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Status> statuses;
		String idStr = request.getParameter("id");

		// Redirect to /profile if this is current user
		if (Integer.toString(user.getId()).equals(idStr)) {
			return new ModelAndView("redirect:/profile");
		}

		if (idStr == null || idStr.length() == 0) {
			statuses = statusService.findByUser(Integer.toString(user.getId()));
		} else {
			statuses = statusService.findByUser(idStr);
			try {
				User otherUser = userService.findUserById(idStr);
				modelAndView.addObject("otherUser", otherUser);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		Status status = new Status();
		modelAndView.addObject("status", status);
		modelAndView.addObject("curUser", user);
		modelAndView.addObject("statuses", statuses);
		modelAndView.addObject("storedXSS", securitySetting.getStoredXSS());
		modelAndView.setViewName("profile");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		// Remove cookie (client side)
		sessionService.eraseCookie(request, response);
		sessionService.removeCurrentSession(request);
		return new ModelAndView("redirect:/login");
	}

    @RequestMapping(value = "/profile/upload", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        User curUser = sessionService.getCurrentUser(request);
        if (curUser == null) {
            return new ModelAndView("redirect:/login");
        }
        if(file.isEmpty()) {
            //do something here
        }
        try {
            if (!"text/xml".equalsIgnoreCase(file.getContentType())) {
                throw new StorageException("Invalid file!");
            }
            String filename = curUser.getId() + ".xml";
            storageService.store(file, filename);

            System.out.println("Trying to store file " + file.getOriginalFilename());
            System.out.println("Content type " + file.getContentType());

            Resource xml_file = storageService.loadAsResource(filename);
            System.out.println("Stored: " + xml_file.getURI().toString());

            User parsedUser = parsingXmlService.XmlToUser(xml_file.getURI().toString());
            System.out.println("parsedUser:" + parsedUser.getGender() + ", " + parsedUser.getLookingfor());

            parsedUser.setId(curUser.getId());
            User updatedUser = userService.updateUser(parsedUser);
            if (updatedUser != null) {
                sessionService.setCurrentUser(request, updatedUser);
            } else {
                System.out.println("Cannot update user " + curUser.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/profile");
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
