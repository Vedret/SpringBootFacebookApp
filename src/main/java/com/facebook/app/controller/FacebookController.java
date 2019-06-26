package com.facebook.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.facebook.app.dao.UserDao;
import com.facebook.app.entity.Users;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Date;

@Controller
@RequestMapping("/")
public class FacebookController {
  

	@Autowired
	private UserDao userDao;
    public PagedList<Post> posts ;
	@Autowired
    private Facebook facebook;

	@Autowired
    private ConnectionRepository connectionRepository;

	public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

    @GetMapping
    String index(Model model) {
        model.addAttribute("title", "Spring Boot Facebook! ");
        model.addAttribute("messages", new Date());
        return "index";
    }

    @GetMapping("feed")
    public String feed(Model model) throws IOException {

        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
     
        PagedList<Post> posts = facebook.feedOperations().getPosts();
        
        //(#100) Tried accessing nonexisting field (context) on node type (User)
        //User userProfile = facebook.userOperations().getUserProfile();
             
       
        Users user = new Users ();
        user.setFacebookId(facebook.fetchObject("me", User.class, "name").getId());
        user.setGender(facebook.fetchObject("me", User.class, "gender").getGender());
        user.setName(facebook.fetchObject("me", User.class, "name").getName());
        userDao.saveUser(user);
		userDao.saveImage(posts);
		   	
		model.addAttribute("posts", posts);
        return "feed";
    }

   
    @GetMapping("logout")
    public String logout(Model model) {
        return "/connect/facebookDisconnect";
    }
    
    
    
}
