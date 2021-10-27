
package com.tanmay.simple3.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tanmay.simple3.services.SecurityService;
import com.tanmay.simple3.domain.Admin;
import com.tanmay.simple3.domain.User;
import com.tanmay.simple3.services.CustomUserDetailsService;


@Controller
public class AuthController {

    @Autowired
    private CustomUserDetailsService userService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    

    //test method
    
/*    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
    	
    	ModelAndView modelAndView = new ModelAndView();    
    	modelAndView.setViewName("testHtmlfile");  
    	modelAndView.addObject("user", user);      
    	return modelAndView;    
        return "testHtmlfile";
    }*/
    
    ///////////////////////////////
    
    
    //home method
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    
    ///////////////////////////////
    
    
    ///register user
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signup() {
    //	model.addAttribute("msg"," chal ja bhai please");
        return "registerUser";
    }
    


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("user") User user) {
    	
    	LOGGER.info("Inside Page()");
    	
       User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            return "registerUser";
        }
       else {
            userService.saveUser(user);
            return "login";

        }       
       
    }
    
    
    ////////////////////////////
    
    
    
    ///login process
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
    	LOGGER.info("Inside login Page()");
		User user = userService.findUserByEmail(email);
		//String x1 = user.getPassword();
		LOGGER.info("password: " + user.getPassword());
		LOGGER.info("sent password " + password);
		if (user.getPassword().equals(password)) {
			return "dashboard";
		} 
		return "error";

	}
    
    
    
    //////////////////////////////
    
    
  
    

    
    

}
