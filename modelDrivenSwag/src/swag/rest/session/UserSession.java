package swag.rest.session;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;

import swag.db.model.User;

@Stateful
public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;
	
	@Context
	HttpServletRequest servletRequest;
	
	private User user;
	
	public UserSession() {
		Cookie sessionId = Cookie.valueOf("JSESSIONID");
		System.out.println("we have a sessionid: "+sessionId.getValue()+"\n"+
				"request: "+(servletRequest==null));
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
