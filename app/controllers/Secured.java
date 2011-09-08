/**
 * 
 */
package controllers;

import models.User;

/**
 * Security Implementation module.
 * 
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 *
 */
public class Secured extends Secure.Security {
	
	/**
	 * Verify user authentication.
	 * @param username
	 * @param password
	 * @return
	 */
	static boolean authenticate(String email, String password){
		return User.connect(email,password);
	}
	/**
	 * Verify User <code>profile</code>.
	 * @param profile compare profile to the user profile.
	 * @return true if user corresponding to the <code>profile</code>.
	 */
    static boolean check(String profile) {
        return User.find("byEmail", connected()).<User>first().verifyProfile(profile);
    }
	
    /**
     * Redirection to Admin controller on satisfied authentication else redirect to Application controller.
     * User will access to Administration side if he gets "Admin" profile. 
     */
	static void onAuthenticated() {
		if(check(User.UserProfile.ADMINISTRATOR.toString())){
			Admin.index();
		}else{
			Application.index();
		}
	}
	
	/**
	 * Redirection to Application controller on disconnection. 
	 */
	static void onDisconnected() {
	    Application.index();
	}
}
