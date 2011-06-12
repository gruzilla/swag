package swag.rest;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateful
@SessionScoped
@Path("user")
public class User {
	@PersistenceContext
	private EntityManager em;
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<swag.db.model.User> list() {
		TypedQuery<swag.db.model.User> qry = em.createQuery("SELECT u FROM swa_user u", swag.db.model.User.class);
		List<swag.db.model.User> result = qry.getResultList();
		
		System.out.println("selected all users: "+result.size());
		
		return result;
	}
}
