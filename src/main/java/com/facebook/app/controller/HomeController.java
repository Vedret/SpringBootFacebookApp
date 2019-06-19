package com.facebook.app.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Iterator;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.facebook.app.dao.UserDao;
import com.facebook.app.entity.Users;
import com.facebook.app.util.CustomErrorType;

@Controller
public class HomeController {
	
	 @Autowired
	 private UserDao userDao;
	
	 @RequestMapping(value="/users/{user_facebook_id}",method = RequestMethod.GET)	 
	 public ResponseEntity<?> viewUser(@PathVariable String user_facebook_id,Model model) throws IOException{
		 	
		 	Users user = userDao.findById(user_facebook_id);
	        if(user==null) {
	        	return new ResponseEntity(new CustomErrorType("User " + user_facebook_id +" Not found" ),HttpStatus.NOT_FOUND) ;
	        }
	        model.addAttribute("user", user);

	        return new ResponseEntity<Users>(user,HttpStatus.OK) ;
	    }
	 
	 
	  
}
		 
	 
	 


