package com.tanmay.simple3.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanmay.simple3.domain.Admin;
import com.tanmay.simple3.domain.User;
import com.tanmay.simple3.services.CustomUserDetailsService;

@Controller
public class AdminController {
	
    @Autowired
    private CustomUserDetailsService userService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    
    //admin panel
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminsignup() {
        return "admin";
    }
    


  /*  @RequestMapping(value = "/admin1", method = RequestMethod.POST)
    public String createNewAdmin(@ModelAttribute("admin") Admin admin) {
    	
    	LOGGER.info("Inside Page()");
        userService.saveAdmin(admin);
        return "login";

     }     
    */
    
    @RequestMapping(value = "/adminDashboard", method = RequestMethod.POST)
   	public String adminLogin(@RequestParam("email") String email, @RequestParam("password") String password,Model model) {
       	LOGGER.info("Inside admin login method()");
   		Admin admin = userService.findAdminByEmail(email);
   		//String x1 = user.getPassword();
   		LOGGER.info("password: " + admin.getPassword());
   		LOGGER.info("sent password " + password);
   		if (admin.getPassword().equals(password)) {
   			String name = admin.getEmail();
   			model.addAttribute("msg", name) 	;
   			return "AdminDashboard";
   		} 
   		return "error";

   	}
    
  //delete user
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete() {
        return "delete";
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("email") String email) {
		
		if (userService.findUserByEmail(email)!=null) {
			User user = userService.findUserByEmail(email);
			userService.deleteUser(user.getId());
			return "home";
		} 
		return "error";

	}
    
    
    //////////////////////////////
    
    
    
    //Read User
    
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Model model) {
       // return "read";
    	List<User> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "readUser";
    }
    
    @RequestMapping(value = "/readUser", method = RequestMethod.POST)
	public String readUser(@RequestParam("email") String email, Model model) {
		
		if (userService.findUserByEmail(email)!=null) {
			
		    User user = userService.findUserByEmail(email);
			List<User> userList = userService.getAllUsers();
			model.addAttribute("userList", userList);
			return "readUser";
		} 
		return "error";

	}
    
    ////////////////////////////////
    
    
    //update user
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update() {
    	return "updateByid";
    }
    

    @RequestMapping(value = "/updated", method = RequestMethod.POST)
    public String updatedUser(@ModelAttribute("user") User user) {
    	String email = user.getEmail();
    	User user1 = userService.findUserByEmail(email);
    	user.setId(user1.getId());
    	userService.updateUser(user);
        return "updated";
    }
    /////////////////////////////////
    

}
