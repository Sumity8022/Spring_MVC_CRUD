package com.demos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demos.dto.MyUserDto;
import com.demos.entities.MyUser;
import com.demos.services.MyUserService;

import jakarta.validation.Valid;

@Controller
public class MyAppController {

	@Autowired
	private MyUserService userService;

	// method responsible for displaying home page at first time
	@GetMapping("/")
	public String showHomePage(Model model) {
		return "home";
	}

	// method used to add new form

	@GetMapping("/addnew")
	public String addNewUser(Model model) {

		model.addAttribute("userdata", new MyUserDto());

		return "create-user";

	}

	// used to save user

	@PostMapping("/saveuser")
	public String saveUser(@Valid  @ModelAttribute("userdata") MyUserDto user,BindingResult result ,Model model) {

		if(result.hasErrors())
		{
			model.addAttribute("userdata", user);
			return "create-user";
		}else
		{
		
		 MyUserDto savedObj = userService.saveNewUser(user);

		if (savedObj != null) {
			model.addAttribute("msg", "user register successfuly!!!!");
		} else {
			model.addAttribute("msg", "soemthing went Wrong!!!");
		}

		model.addAttribute("userdata", new MyUserDto());
		return "create-user";
		}

	}

	@GetMapping("/viewall")
	public String getAllUser(Model model, @RequestParam(defaultValue = "0") int page) {
		int pageSize = 3; // number of record per page object

		if (page < 0) {
			page = 0;
		}

		// 0123 4

		Page<MyUserDto> userpage = userService.findPaginationData(PageRequest.of(page, pageSize));

		int totalPages = userpage.getTotalPages();

		if(totalPages!=0)
		{
		if (page > totalPages-1) {
			page = totalPages - 1;

			userpage = userService.findPaginationData(PageRequest.of(page, pageSize));

		}
		}

		model.addAttribute("userpage", userpage);
		model.addAttribute("currentpage", page);

		return "view-user";

	}
	
	
	

	@GetMapping("/delete/")
	public String deleteUser(@RequestParam int id, Model model) {
		System.out.println("hhhhhh");
		userService.deleteUserById(id);

		return "redirect:/viewall";
	}

	@GetMapping("/edituser/{id}")
	public String editMyUser(@PathVariable("id") int id, Model model) {
		// service
		MyUser editUser = userService.editUser(id);

		model.addAttribute("userdata", editUser);

		return "edit-user";

	}

	@PostMapping("/updateuser")
	public String updateUser(@Valid @ModelAttribute("userdata") MyUserDto user,BindingResult result, Model model) {
		// first need to fetch existing object
		// set all latest value to that existing object
		// save that latest object
		
		if(result.hasErrors())
		{
			model.addAttribute("userdata", user);
			return "edit-user";
			
		}

		int uid = user.getUid();

		// fetching existing user data
		MyUser existingUser = userService.getById(uid);

		existingUser.setFName(user.getFName());
		existingUser.setLname(user.getLname());
		existingUser.setEmail(user.getEmail());
		existingUser.setMob(user.getMob());

		// now again need to save this existing object with latest value
		MyUser updateUser = userService.updateUser(existingUser);

		return "redirect:/viewall";

	}

}//
