package swag.rest.session;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Cookie;

import swag.db.model.User;

@Stateful
@SessionScoped
public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;
	
	private User user;
	
	public UserSession() {
		Cookie sessionId = Cookie.valueOf("JSESSIONID");
		System.out.println("we have a sessionid: "+sessionId.getValue()+"\n");
		//Response.seeOther(new URI("/"));
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isValid() {
		return user != null;
	}
}
