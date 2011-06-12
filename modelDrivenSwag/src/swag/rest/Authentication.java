package swag.rest;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;
import org.json.JSONObject;

import swag.Utils;
import swag.db.model.User;
import swag.game.map.MapManager;
import swag.rest.session.UserSession;
import swag.singletons.SessionManager;

@Stateless
@Path("auth")
public class Authentication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final boolean DEBUG = true;

	@PersistenceContext
	private EntityManager em;

	@EJB
	private SessionManager sessionManager;
	
	@Context
	private HttpServletRequest request;
	
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
			UserSession session = sessionManager.getSession(request);
			if (session == null) message.put("status", false);
			else message.put("status",  session.getUser() != null);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return message.toString();
	}
	
	@GET
	@Path("logout")
	@Produces("application/json")
	public void logout() {
		
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
		
		String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String password = "";
		for (int i = 0; i < 8; i++) {
			password += validChars.charAt((int)Math.floor(Math.random()*validChars.length()));
		}
		String salt = "";
		for (int i = 0; i < 8; i++) {
			salt += validChars.charAt((int)Math.floor(Math.random()*validChars.length()));
		}
		
		/*
		Mailer m = new Mailer();
		try {
			m.postMail(
					new String[]{email},
					"Welcome to SWAG",
					"Dear "+name+"\n\nYour login is "+username+"\n\nYou may login with your password "+password+"\n\nTY!",
					"swag@swag.at");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		System.out.println("\n\n\n\n\n========================================================\n\n"+
				"NEW USER: "+username+"\nPassword: "+password+"\nSalt: "+salt+"\n\n\n" +
				"========================================================\n\n\n\n");
		
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
	public String login(@FormParam("username") String username, @FormParam("hashed") String hashedpw) {
		
		String defaultError = "{\"status\":\"error\"}";
		
		if (em == null) return DEBUG ? "{\"status\":\"em is null\"}" : defaultError;
		
		TypedQuery<User> qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname", User.class);
		qry.setParameter("uname", username);
		List<User> result = qry.getResultList();
		
		
		if (result.size() != 1) return DEBUG ? "{\"status\":\"user not found\"}" : defaultError;
		
		hashedpw = Utils.md5(hashedpw + result.get(0).getSalt());
		
		qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname AND u.password LIKE :pwd", User.class);
		qry.setParameter("uname", username);
		qry.setParameter("pwd", hashedpw);
		result = qry.getResultList();
		
		if (result.size() == 1) {
			User user = result.get(0);
			JSONObject response = new JSONObject();
			
			UserSession session = sessionManager.createSession();
			session.setUser(user);
			
			System.out.println("assigning user "+user.getUsername()+" to session "+(session==null?"EMPTY":"NOT null"));
			
			try {
				response.put("sessionId", session.getSessionId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return response.toString();
		} else {
			return DEBUG ? "{\"status\":\"not found with pw\"}" : defaultError;
		}
	}
}