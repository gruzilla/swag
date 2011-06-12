package swag.rest.session;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import swag.db.model.User;

public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;
	
	private User user;
	private UUID sessionId;
	
	public UserSession() {
		sessionId = UUID.randomUUID();
		System.out.println("we have a sessionid: "+getSessionId()+"\n");
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

	public UUID getSessionId() {
		return sessionId;
	}
}
