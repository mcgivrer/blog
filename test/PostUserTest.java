import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class PostUserTest extends UnitTest {

	public User user;
	
	/**
	 * create environment objects
	 */
	@Before
	public void setUp(){
    	user = new User("testuser","password","Test","User","testuser@test.org",true, null);
    	user.save();
    	Long uid = user.getId();
    	assertNotNull("user was not saved", uid);		
	}
	
	/**
	 * test Post creation
	 */
    @Test
    public void createPostTest() {
    	Post post = new Post("test","test","test",user,new Date());
    	user.posts = new ArrayList<Post>();
    	user.posts.add(post);
    	user.save();
    	Long pid = post.getId();
    	assertNotNull("Post was not saved", pid);
    	post.delete();
    	post=null;
    }
    
    /**
     * Test post retrieve through user object.
     */
    @Test
    public void retrievePost(){
    	Post post1 = new Post("test1","test","test",user,new Date());
    	user.posts = new ArrayList<Post>();
    	user.posts.add(post1);
    	Post post2 = new Post("test2","test","test",user,new Date());
    	user.posts.add(post2);
    	Post post3 = new Post("test3","test","test",user,new Date());
    	user.posts.add(post3);
    	Post post4 = new Post("test4","test","test",user,new Date());
    	user.posts.add(post4);
    	user.save();
    	User userRetrievePosts = User.findById(user.id);
    	List<Post> posts = userRetrievePosts.posts;
    	assertNotNull("Posts were not attached to user "+user.username,posts);
    	
    	post1.delete();
    	post2.delete();
    	post3.delete();
    	post4.delete();
    	post1 = null;
    	post2 = null;
    	post3 = null;
    	post4 = null;
    }
    
    @Test
    public void dataTest(){
    	Fixtures.deleteAllModels();
    	Fixtures.loadModels("data.yml");
    	User userRetrievePosts = User.findById(user.id);
    	List<Post> posts = userRetrievePosts.posts;
    	assertNotNull("Posts were not correctly inserted "+user.username,posts);
    }
    
    /**
     * Remove Environment objects from database.
     */
    @After
    public void tearDown(){
    	user.delete();
    	user=null;
    }
}
