package swag.rest;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONException;
import org.json.JSONObject;

import swag.Utils;
import swag.db.model.User;
import swag.game.map.MapManager;
import swag.rest.session.UserSession;

@Stateful
@SessionScoped
@Path("auth")
public class Authentication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final boolean DEBUG = true;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserSession session;
	
	private User user;
	
	
	/**
	 * Default constructor. 
	 */
	public Authentication() {
		System.out.println("instanciating auth");
	}

	@GET
	@Path("isloggedin")
	@Produces("application/json")
	public String isloggedin() {
		JSONObject message = new JSONObject();
		
		try {
			message.put("status", user != null);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return message.toString();
	}
	
	@POST
	@Path("register")
	@Produces("application/json")
	public User register(
		@FormParam("username") String username,
		@FormParam("email") String email,
		@FormParam("address") String address,
		@FormParam("name") String name,
		@FormParam("timezone") Integer timezone
	) {
		MapManager.createBase();
		User newUser = new User();
		
		newUser.setAddress(address);
		newUser.setEmail(email);
		newUser.setName(name);
		newUser.setTimezone(timezone);
		newUser.setUsername(username);
		
		String password = "";
		for (int i = 0; i < 8; i++) {
			password += (char)((int)Math.random()*128);
		}
		String salt = "";
		for (int i = 0; i < 8; i++) {
			salt += (char)((int)Math.random()*128);
		}
		
		System.out.println("\n\n\n\n\nNEW USER: "+username+"\nPassword: "+password+"\n\n\n\n");
		
		newUser.setPassword(Utils.md5(Utils.md5(password)+salt));
		newUser.setSalt(salt);
		
		em.persist(newUser);
		
		return newUser;
	}
	
	@POST
	@Path("checkusername")
	@Produces("application/json")
	public String checkusername(@FormParam("username") String username) {
		JSONObject message = new JSONObject();
		
		TypedQuery<User> qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname", User.class);
		qry.setParameter("uname", username);
		List<User> result = qry.getResultList();
		
		try {
			message.put("status", result.size() == 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return message.toString();
	}
	
	/**
	 * Retrieves representation of an instance of Message
	 * @return an instance of String
	 */
	@POST @Path("login")
	@Produces("application/json")
	public Object login(@FormParam("username") String username, @FormParam("hashed") String hashedpw) {
		
		String defaultError = "{\"status\":\"error\"}";
		
		if (session == null) return DEBUG ? "{\"status\":\"session is null\"}" : defaultError;
		if (em == null) return DEBUG ? "{\"status\":\"em is null\"}" : defaultError;
		
		TypedQuery<User> qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname", User.class);
		qry.setParameter("uname", username);
		List<User> result = qry.getResultList();
		
		
		if (user != null) return DEBUG ? "{\"status\":\"user already authenticated\"}" : defaultError;
		if (result.size() != 1) return DEBUG ? "{\"status\":\"user not found\"}" : defaultError;
		
		hashedpw += result.get(0).getSalt();
		
		qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname AND u.password LIKE :pwd", User.class);
		qry.setParameter("uname", username);
		qry.setParameter("pwd", hashedpw);
		result = qry.getResultList();
		
		if (result.size() == 1) {
			user = result.get(0);
			return result.get(0);
		} else {
			return DEBUG ? "{\"status\":\"not found with pw\"}" : defaultError;
		}
	}
}