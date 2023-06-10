package com.lorrained.thoughts_confessions.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lorrained.thoughts_confessions.models.Comment;
import com.lorrained.thoughts_confessions.models.Confession;
import com.lorrained.thoughts_confessions.models.User;
import com.lorrained.thoughts_confessions.services.CommentService;
import com.lorrained.thoughts_confessions.services.ConfessionService;
import com.lorrained.thoughts_confessions.services.UserService;


@Controller
@RequestMapping("/confessions")
public class ConfessionController {
	
	@Autowired
	private UserService userServ;
	@Autowired
	private ConfessionService confessionServ;
	@Autowired
	private CommentService commentServ;
	

	///CONFESSIONS DASHBOARD - SHOWS ALL CONFESSIONS 
	@GetMapping("")
	public String confessionDashboard(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		model.addAttribute("allConfessions", confessionServ.allConfessions() );
		return "confessions_dash.jsp";
	}
	
	///SHOW ALL OF USERS CONFESSIONS
	@GetMapping("/myConfessions")
	public String showUsersConfessions(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		model.addAttribute("confessions", confessionServ.allConfessions());
		return "confessions_usersConfessions.jsp";
	}	

	
	//////////////////////////////////	
	
	
	///SHOW ONE CONFESSION
	@GetMapping("/{id}")
	public String showOneConfession(@PathVariable("id") Long id, @ModelAttribute("commentConfession") Comment comment, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		model.addAttribute("user", loggedUser);
		model.addAttribute("oneConfession", confessionServ.findConfession(id));
		return "confessions_show_one.jsp";
	}
	
	///COMMENT ON ONE CONFESSION
	@PostMapping("/createComment/{idC}")
	public String commentOnConfession(@Valid @ModelAttribute("commentConfession") Comment comment, @PathVariable("idC") Long idC, BindingResult result, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
//		model.addAttribute("user", loggedUser);
		Confession confession = confessionServ.findConfession(idC);
		comment.setUser(loggedUser);
		comment.setConfession(confession);
//		comment.setConfession(null);
        if (result.hasErrors()) {
            return "confessions_show_one.jsp";
        } else {
            commentServ.createComment(comment);
            return "redirect:/confessions/{idC}";
        }
	}
	
	///DELETE COMMENT
	@GetMapping("/delete/{idCnf}/comment/{idC}")
	public String deleteComment(@PathVariable("idC") Long idC, @PathVariable("idCnf") Long idT) {
		commentServ.deleteComment(idC);
		return "redirect:/confessions/{idCnf}";
	}
	
	///LIKE ON ONE CONFESSION
	@GetMapping("/like/{id}")
	public String likeConfession(@PathVariable("id") Long id, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		Confession confession = confessionServ.findConfession(id);
		confessionServ.likeConfession(confession, loggedUser);
		return "redirect:/confessions/{id}";
	}
	
	///UNLIKE ON ONE CONFESSION
	@GetMapping("/unlike/{id}")
	public String unlikeConfession(@PathVariable("id") Long id, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		Confession confession = confessionServ.findConfession(id);
		confessionServ.unlikeConfession(confession, loggedUser);
		return "redirect:/confessions/{id}";
	}

	
	//////////////////////////////////	
	
	
	///CREATE CONFESSION PAGE
	@GetMapping("/add")
	public String addConfession(@ModelAttribute("confession") Confession confession, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		return "confessions_create.jsp";
	}
	
	///CREATE CONFESSION - TO THE DATABASE (POST)
	@PostMapping("/create")
	public String createConfession(@Valid @ModelAttribute("confession") Confession confession, BindingResult result, Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		confession.setUser(loggedUser);
        if (result.hasErrors()) {
            return "confessions_create.jsp";
        } else {
        	confessionServ.createConfession(confession);
            return "redirect:/confessions";
        }
	}
	
	///EDIT CONFESSION PAGE
	@GetMapping("/edit/{id}")
	public String editConfession(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		model.addAttribute("user", loggedUser);
		model.addAttribute("confession", confessionServ.findConfession(id));
        return "confessions_edit.jsp";
	}
	
	///EDIT CONFESSION - TO THE DATABASE (PUT)
	@PutMapping("/edit/{id}")
	public String edit(@Valid @ModelAttribute("confession") Confession confession, Model model, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		model.addAttribute("user", loggedUser);
		confession.setUser(loggedUser);		
		if (result.hasErrors()) {
            return "confessions_edit.jsp";
        } else {
        	confession.setId(id);
        	confessionServ.updateConfession(confession);
            return "redirect:/confessions";
        }
	}
	
	///DELETE CONFESSION FROM THE DATABASE
	@GetMapping("/delete/{id}")
	public String deleteConfession(@PathVariable("id") Long id) {
		confessionServ.deleteConfession(id);
		return "redirect:/confessions";
	}
	
}
