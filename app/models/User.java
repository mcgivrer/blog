/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Email;
import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author E902375
 *
 */
@Entity
@Table(name="USERS")
public class User extends Model {
	
	/**
	 * Profile definition.
	 * @author FDELORME
	 *
	 */
	public static enum UserProfile{
		USER,
		MODERATOR,
		ADMINISTRATOR
	}
	
	@Required
	@Max(value=30, message="Username must contains at max 30 characters.")
	@Min(value=6, message="Username must contains at minima 6 charcaters.")
	public String username;
	
	@Required
	@Max(value=20, message="Password must contains at max 20 characters.")
	@Min(value=4, message="Password must contains at minima 4 charcaters.")
	public String password;
	
	@Max(value=60, message="Firstname must contains at max 60 characters.")
	public String firstname;
	
	@Max(value=60, message="Lastname must contains at max 60 characters.")
	public String lastname;
	
	@Required
	@Email(message="this field must respect email format.")
	public String email;
	
	@Required
	@Enumerated(EnumType.STRING)
	public UserProfile profile=User.UserProfile.USER;
	
	/**
	 * List of linked posts.
	 */
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL,fetch=FetchType.LAZY )
	public List<Post> posts = new ArrayList<Post>();

	/**
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param posts
	 */
	public User(String username, String password, String firstname,
			String lastname, String email, boolean administrator, List<Post> posts) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.posts = posts;
	}

	/**
	 * toString() to allow username display in CRUD interface.
	 */
	public String toString(){
		return this.username+"("+this.firstname+" "+this.lastname+")";
	}

	/**
	 * test if user is authorized to connect
	 * @param email
	 * @param password
	 * @return
	 */
	public static boolean connect(String email, String password) {
		User user = User.find("byEmail",email).first();
		return user!=null && user.password.equals(password);
	}

	/**
	 * Verify User profile against pProfile.
	 * @param pProfile
	 * @return
	 */
	public boolean verifyProfile(String pProfile) {
        return pProfile.contains(profile.toString());
	}

}
