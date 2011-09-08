package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secured.class)
public class Admin extends Controller {
	
    @Before
    static void setConnectedUser() {
        if(Secured.isConnected()) {
            User user = User.find("byEmail", Secured.connected()).first();
            renderArgs.put("user", user);
        }
    }
	public static void index(){
		render("Administration/index.html");
	}
	
}
