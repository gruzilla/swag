package swag.rest;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import swag.rest.session.UserSession;

@Stateful
@SessionScoped
@Path("user")
public class User {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserSession session;

	@Path("list")
	public List<swag.db.model.User> list() {
		if (!session.isValid()) return Arrays.asList(new swag.db.model.User[] {});
		
		TypedQuery<swag.db.model.User> qry = em.createQuery("SELECT u FROM swa_user u", swag.db.model.User.class);
		List<swag.db.model.User> result = qry.getResultList();
		
		return result;
	}
}
