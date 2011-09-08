/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="POSTS")
public class Post extends Model {
	
	@Required
	@MaxSize(value=255,message="home.title.size.max.exceed")
	public String title;
	
	@Required(message="post.header.required")
	public String header;
	
	@Required(message="post.title.required")
	@Lob
	public String content;
	
	@Required
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public User author;
	
	@Required
	public Date createdAt;
	
	/**
	 * @param title
	 * @param header
	 * @param content
	 * @param author
	 * @param createdAt
	 */
	public Post(String title, String header, String content, User author,
			Date createdAt) {
		this.title = title;
		this.header = header;
		this.content = content;
		this.author = author;
		this.createdAt = createdAt;
	}
	
	/**
	 * toString() implementation to allow Post reference into CRUD interface.
	 */
	public String toString(){
		return this.title+" ( "+this.author.username+" ) - "+ this.createdAt;
	}
}
