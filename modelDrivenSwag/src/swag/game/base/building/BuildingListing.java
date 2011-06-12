package swag.game.base.building;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import swag.db.model.Building;
import swag.db.model.User;
import swag.rest.session.UserSession;
import swag.singletons.SessionManager;

/**
 * Session Bean implementation class BuildingListing
 */

/**
 * select u.id,b.creationTime,bs.positionX, bs.positionY, bu.level, 
 * bu.currentHealth, bu.maxHealth, bu.creationTime from 
 * swa_user u, swa_base b,swa_basesquare bs, swa_building bu 
 * where bu.id = bs.id and b.id = bs.id and u.id = b.id; 
 */

@Stateless

@Path("base")
public class BuildingListing {

	
	@EJB
	private SessionManager manager;
	
	@PersistenceContext
	private EntityManager em;
	

	private String queryBuildingList = "select bu.id, bu.dtype, bu.creationTime, bu.currentHealth, bu.level, bu.maxhealth, bu.builtonbasesquare_id" +
	            		" from swa_base b, swa_basesquare bs, swa_building bu where bu.builtonbasesquare_id = bs.id and bs.isonbase_id = b.id and b.id = ? AND b.belongstouser_id = ?";

	@Context
	private HttpServletRequest request;

	
    /**
     * Default constructor. 
     */
    public BuildingListing() {
        // TODO Auto-generated constructor stub
    }
    
    @GET
    @Path("buildings/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List listUserBuildings(@PathParam("id") Integer baseId) {
    User user = manager.getSession(request).getUser();

   // Query query = em.createNamedQuery("listBuildings").setParameter(1, user.getId());
    Query createdQuery = em.createNativeQuery(queryBuildingList);
    createdQuery.setParameter(1, baseId);
    createdQuery.setParameter(2, user.getId());
    
    System.out.println("buildings queried " + createdQuery.getResultList().size());
    
    return ((List<Building>)createdQuery.getResultList());
    }

}
