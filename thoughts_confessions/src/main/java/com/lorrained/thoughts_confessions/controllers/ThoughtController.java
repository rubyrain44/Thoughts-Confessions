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
import com.lorrained.thoughts_confessions.models.Thought;
import com.lorrained.thoughts_confessions.models.User;
import com.lorrained.thoughts_confessions.services.CommentService;
import com.lorrained.thoughts_confessions.services.ThoughtService;
import com.lorrained.thoughts_confessions.services.UserService;


@Controller
@RequestMapping("/thoughts")
public class ThoughtController {
	
	@Autowired
	private UserService userServ;
	@Autowired
	private ThoughtService thoughtServ;
	@Autowired
	private CommentService commentServ;
	

	///THOUGHTS DASHBOARD - SHOWS ALL THOUGHTS 
	@GetMapping("")
	public String thoughtsDashboard(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		model.addAttribute("allThoughts", thoughtServ.allThoughts() );
		return "thoughts_dash.jsp";
	}
	
	
	///SHOW ALL OF USERS THOUGHTS
	@GetMapping("/myThoughts")
	public String showUsersThoughts(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		return "thoughts_usersThoughts.jsp";
	}	

	
	//////////////////////////////////	
	
	
	///SHOW ONE THOUGHT
	@GetMapping("/{id}")
	public String showOneThought(@PathVariable("id") Long id, @ModelAttribute("commentThought") Comment comment, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		model.addAttribute("user", loggedUser);
		model.addAttribute("oneThought", thoughtServ.findThought(id));
		return "thoughts_show_one.jsp";
	}
	
	
	///COMMENT ON ONE THOUGHT
	@PostMapping("/createComment/{idT}")
	public String commentOnThought(@Valid @ModelAttribute("commentThought") Comment comment, @PathVariable("idT") Long idT, BindingResult result, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
//		model.addAttribute("user", loggedUser);
		Thought thought = thoughtServ.findThought(idT);
		comment.setUser(loggedUser);
		comment.setThought(thought);
//		comment.setConfession(null);
        if (result.hasErrors()) {
            return "thoughts_show_one.jsp";
        } else {
            commentServ.createComment(comment);
            return "redirect:/thoughts/{idT}";
        }
	}
	
	///DELETE COMMENT
	@GetMapping("/delete/{idT}/comment/{idC}")
	public String deleteComment(@PathVariable("idC") Long idC, @PathVariable("idT") Long idT) {
		commentServ.deleteComment(idC);
		return "redirect:/thoughts/{idT}";
	}
	
	
	///LIKE ON ONE THOUGHT
	@GetMapping("/like/{id}")
	public String likeThought(@PathVariable("id") Long id, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		Thought thought = thoughtServ.findThought(id);
		thoughtServ.likeThought(thought, loggedUser);
		return "redirect:/thoughts/{id}";
	}
	
	
	///UNLIKE ON ONE THOUGHT
	@GetMapping("/unlike/{id}")
	public String unlikeThought(@PathVariable("id") Long id, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		Thought thought = thoughtServ.findThought(id);
		thoughtServ.unlikeThought(thought, loggedUser);
		return "redirect:/thoughts/{id}";
	}
	

	//////////////////////////////////	
	

	///CREATE THOUGHT PAGE
	@GetMapping("/add")
	public String addThought(@ModelAttribute("thought") Thought thought, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		return "thoughts_create.jsp";
	}
	
	///CREATE THOUGHT - TO THE DATABASE (POST)
	@PostMapping("/create")
	public String createThought(@Valid @ModelAttribute("thought") Thought thought, BindingResult result, Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(id);
		model.addAttribute("user", loggedUser);
		thought.setUser(loggedUser);
        if (result.hasErrors()) {
            return "thoughts_create.jsp";
        } else {
            thoughtServ.createThought(thought);
            return "redirect:/thoughts";
        }
	}
	
	///EDIT THOUGHT PAGE
	@GetMapping("/edit/{id}")
	public String editThought(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		model.addAttribute("user", loggedUser);
		model.addAttribute("thought", thoughtServ.findThought(id));
        return "thoughts_edit.jsp";
	}
	
	///EDIT THOUGHT - TO THE DATABASE (PUT)
	@PutMapping("/edit/{id}")
	public String edit(@Valid @ModelAttribute("thought") Thought thought, Model model, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		Long idU = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(idU);
		model.addAttribute("user", loggedUser);
		thought.setUser(loggedUser);		
		if (result.hasErrors()) {
            return "thoughts_edit.jsp";
        } else {
        	thought.setId(id);
            thoughtServ.updateThought(thought);
            return "redirect:/thoughts";
        }
	}
	
	///DELETE THOUGHT FROM THE DATABASE
	@GetMapping("/delete/{id}")
	public String deleteThought(@PathVariable("id") Long id) {
		thoughtServ.deleteThought(id);
		return "redirect:/thoughts";
	}
}
