package swag.rest;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import swag.db.model.User;
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
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			hashedpw += result.get(0).getSalt();
			
			md.update(hashedpw.getBytes());
			byte[] digest = md.digest();
			String md5 = "";
			for ( int i=0; i < digest.length; i++ ) {
				String s = Integer.toHexString( digest[i]&0xFF );
				md5 += (s.length() == 1 ) ? "0"+s : s;
			}
			
			qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname AND u.password LIKE :pwd", User.class);
			qry.setParameter("uname", username);
			qry.setParameter("pwd", md5);
			result = qry.getResultList();
			
			if (result.size() == 1) {
				user = result.get(0);
				return result.get(0);
			} else {
				return DEBUG ? "{\"status\":\"not found with pw\"}"+md5 : defaultError;
			}
		} catch (NoSuchAlgorithmException e) {
			return DEBUG ? "{\"status\":\"no such algorithm\"}" : defaultError;
		}
	}
}