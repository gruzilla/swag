package swag.rest.session;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import swag.db.model.User;

@Stateful
public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Context
	private HttpHeaders headers;

	private User user;
	
	public UserSession() {
		System.out.println("we have headers: "+(headers==null));
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
