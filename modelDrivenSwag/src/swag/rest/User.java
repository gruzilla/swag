package swag.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import swag.singletons.SessionManager;

@Stateless
@Path("user")
public class User {
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	SessionManager sessionManager;

	@Context
	private HttpServletRequest request;
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<swag.db.model.User> list() {
		TypedQuery<swag.db.model.User> qry = em.createQuery("SELECT u FROM swa_user u", swag.db.model.User.class);
		List<swag.db.model.User> result = qry.getResultList();
		
		result.remove(sessionManager.getSession(request).getUser());
		
		return result;
	}
}

