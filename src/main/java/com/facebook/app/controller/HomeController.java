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
		 	//i don't need model here for this task , but i will leave it and maybe implement some view latter
		 	Users user = userDao.findById(user_facebook_id);
	        if(user==null) {
	        	return new ResponseEntity(new CustomErrorType("User " + user_facebook_id +" Not found" ),HttpStatus.NOT_FOUND) ;
	        }
	        model.addAttribute("user", user);

	        return new ResponseEntity<Users>(user,HttpStatus.OK) ;
	    }
	 
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Users user, UriComponentsBuilder ucBuilder) {

		// Check if user exists in db
		if (userDao.isUserExist(user)) {

			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A User with FacebookId " + user.getFacebookId() + " already exist."),
					HttpStatus.CONFLICT);
		}

		userDao.saveUser(user);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{user_fb_id}",method =  RequestMethod.DELETE)	
	   public ResponseEntity<?>  deleteUser(@PathVariable(value="user_fb_id") String  user_fb_id) {
			
		      
		        Users user = userDao.findById(user_fb_id);
		        if (user == null) {
		            
		            return new ResponseEntity(new CustomErrorType("Unable to delete. User with facebookID " + user_fb_id + " not found."),
		                    HttpStatus.NOT_FOUND);
		        }
		        userDao.deleteUser(user_fb_id);
		        return new ResponseEntity("User deleted ", HttpStatus.OK);
		    }
	  
}
		 //@RequestMapping(value = "/users/{user_fb_id}/photos", method = RequestMethod.POST)
		 //Will be implemented latter 
	 
	 


