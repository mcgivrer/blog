import models.Post;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * 
 */

/**
 * @author E902375
 *
 */
@OnApplicationStart
public class Bootstrap extends Job {
	public void doJob(){
		if(Post.count()==0){
			Fixtures.deleteAllModels();
			Fixtures.loadModels("initial-data.yml");
		}
	}
}
