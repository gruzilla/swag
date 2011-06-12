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
import javax.ws.rs.core.Context;

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
public class BuildingListing {

	
	@EJB
	private SessionManager manager;
	
	@PersistenceContext
	private EntityManager em;
	

	private String queryBuildingList = "select b.creationTime, bs.positionX, bs.positionY, bu.level, bu.currentHealth, bu.maxHealth, bu.creationTime" +
	            		" from swa_user u, swa_base b, swa_basesquare bs, swa_building bu where bu.id = bs.id and bs.id = b.id and b.id = u.id and u.id = ?" +
	            		" and (Extract ( Milliseconds from CURRENT_TIMESTAMP) - 30000) > EXTRACT (MILLISECONDS from b.creationTime)";

	@Context
	private HttpServletRequest request;

	
    /**
     * Default constructor. 
     */
    public BuildingListing() {
        // TODO Auto-generated constructor stub
    }
    
    public List listUserBuildings() {
    User user = manager.getSession(request).getUser();

   // Query query = em.createNamedQuery("listBuildings").setParameter(1, user.getId());
    Query createdQuery = em.createNativeQuery(queryBuildingList);
    List resultList = createdQuery.setParameter(1, user.getId()).getResultList();
    return resultList;
    }

}
