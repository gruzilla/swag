package swag.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import swag.db.model.User;

@Stateful
@Path("auth")
public class Authentication {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;
	
	@PersistenceContext(unitName="swag",type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	/**
	 * Default constructor. 
	 */
	public Authentication() {
	}

	/**
	 * Retrieves representation of an instance of Message
	 * @return an instance of String
	 */
	@POST @Path("login")
	@Produces("application/json")
	public Object login(@FormParam("username") String username, @FormParam("hashed") String hashedpw) {
		if (em == null) return "em is null";
		TypedQuery<User> qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname", User.class);
		qry.setParameter("uname", username);
		List<User> result = qry.getResultList();
		
		if (result.size() != 1) return "user not found";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			hashedpw += result.get(0).getSalt();
			
			md.update(hashedpw.getBytes());
			byte[] digest = md.digest();
			String sha1 = "";
			for ( int i=0; i < digest.length; i++ ) {
				String s = Integer.toHexString( digest[i]&0xFF );
				sha1 += (s.length() == 1 ) ? "0"+s : s;
			}
			
			qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname AND u.password LIKE :pwd", User.class);
			qry.setParameter("uname", username);
			qry.setParameter("pwd", sha1);
			result = qry.getResultList();
			
			if (result.size() == 1) {
				return result.get(0);
			} else {
				return "not found with pw "+sha1;
			}
		} catch (NoSuchAlgorithmException e) {
			return "no such algorithm";
		}
	}

	/**
	 * PUT method for updating or creating an instance of Message
	 * @param content representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("application/json")
	public void putJson(String content) {
	}

}