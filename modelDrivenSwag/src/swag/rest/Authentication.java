package swag.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
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

import org.apache.log4j.Logger;

import swag.db.model.User;

@ManagedBean
@Path("auth")
@SessionScoped
public class Authentication {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;
	
	@PersistenceContext(unitName="swag",type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@EJB
	private StatefulBlahLocal blah;

	
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
		return blah.login(username, hashedpw);
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