package com.uxpsystems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.domain.User;
import com.uxpsystems.service.UserService;

@RestController(value="application/json")
@RequestMapping("/User")
public class UserController {

	@Autowired
	User user;
	
	@Autowired
	UserService userService;
		
	@GetMapping("/editUser/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    User user = userService.returnUserById(id);
	    model.addAttribute("user", user);
	    return "update-user";
	}
	
	@PostMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        user.setId(id);
	        return "update-user";
	    }
	         
	    userService.saveIfPresentInDatabase(user);
	    model.addAttribute("users", userService.returnAllUsers());
	    return "index";
	}
	     
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
	    User user = userService.returnUserById(id);
	    userService.deleteIfPresent(user.getId());
	    model.addAttribute("users", userService.returnAllUsers());
	    return "index";
	}
}
