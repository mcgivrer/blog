package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	static private int propNbHistoryLines = Integer.parseInt(Play.configuration.getProperty("posts.histo.max.display.by.page","10"));
	static private int propNbposts = Integer.parseInt(Play.configuration.getProperty("posts.home.max.display.by.page","4"));
	

	
	/**
	 * Home page
	 */
    public static void index() {
    	List<Post> histoposts = Post.find("order by createdAt").fetch(propNbHistoryLines);
    	List<Post> posts = Post.find("order by createdAt").fetch(propNbposts);
        render( histoposts, posts);
    }
    /**
     * Show particular post
     * @param id
     */
    public static void show(Long id){
    	Post showpost = Post.findById(id);
    	List<Post> histoposts = Post.find("order by createdAt").fetch(propNbHistoryLines);
    	List<Post> posts = Post.find("order by createdAt").fetch(propNbposts);
    	render(showpost, posts, histoposts);
    }

}