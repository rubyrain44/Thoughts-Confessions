package com.lorrained.thoughts_confessions.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.lorrained.thoughts_confessions.models.LoginUser;
import com.lorrained.thoughts_confessions.models.User;
import com.lorrained.thoughts_confessions.services.UserService;


@Controller
public class HomeController {

	@Autowired
	private UserService userServ;

	
	///ABOUT PAGE
	@GetMapping("/about")
	public String about () {
		return "about.jsp";
	}
	
	///MAIN INDEX PAGE - LOGIN///
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("loginUser", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String loginForm(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User user = userServ.login(loginUser, result);
		if(user == null) {
			return "login.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}
	
	
	///REGISTER PAGE///
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String registerForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		User newUser = userServ.register(user, result);
		if(newUser == null) {
			return "register.jsp";
		}
		session.setAttribute("userId", newUser.getId());
		return "redirect:/dashboard";
	}
	
	
	///LOGOUT///
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null);
		return "redirect:/";	
	}
	
	
	///DASHBOARD///
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		return "dashboard.jsp";
	}
}
