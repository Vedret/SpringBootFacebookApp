package com.facebook.app.controller;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import com.facebook.app.dao.UserDao;
import com.facebook.app.entity.Photos;
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
	 
	 @RequestMapping(value="/users/{user_facebook_id}/photos",method = RequestMethod.GET)	 
	 public ResponseEntity<?> viewphotos(@PathVariable String facebookId,Model model) throws IOException{
		 	//i don't need model here for this task , but i will leave it and maybe implement some view latter
		 	Photos photo = userDao.findPhotosById(facebookId);
	        if(photo==null) {
	        	return new ResponseEntity(new CustomErrorType("Photo with userID " + facebookId +" Not found" ),HttpStatus.NOT_FOUND) ;
	        }
	        model.addAttribute("photo", photo);

	        return new ResponseEntity<Photos>(photo,HttpStatus.OK) ;
	    }
	 
	//Delete user and photos from database
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
		 
		 
	 
	 


