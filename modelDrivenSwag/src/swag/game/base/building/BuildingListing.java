package swag.game.base.building;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swag.db.model.User;
import swag.rest.session.UserSession;

/**
 * Session Bean implementation class BuildingListing
 */

/**
 * select u.id,b.creationTime,bs.positionX, bs.positionY, bu.level, 
 * bu.currentHealth, bu.maxHealth, bu.creationTime from 
 * swa_user u, swa_base b,swa_basesquare bs, swa_building bu 
 * where bu.id = bs.id and b.id = bs.id and u.id = b.id; 
 */


@Stateful
@SessionScoped
public class BuildingListing {

	
	@EJB
	private UserSession userSession;
	
	@PersistenceContext
	private EntityManager em;
	

	private String queryBuildingList = "select b.creationTime, bs.positionX, bs.positionY, bu.level, bu.currentHealth, bu.maxHealth, bu.creationTime" +
	            		" from User u, Base b, BaseSquare bs, Building bu where bu.id = bs.id and bs.id = b.id and b.id = u.id and u.id = ?" +
	            		" (Extract ( Milliseconds from CURRENT_TIMESTAMP) - 30000) > EXTRACT (MILLISECONDS from b.creationTime)";

	
    /**
     * Default constructor. 
     */
    public BuildingListing() {
        // TODO Auto-generated constructor stub
    }
    
    public List listUserBuildings() {
    User user = userSession.getUser();

   // Query query = em.createNamedQuery("listBuildings").setParameter(1, user.getId());
    Query createdQuery = em.createNativeQuery(queryBuildingList);
    List resultList = createdQuery.setParameter(0, user.getId()).getResultList();
    return resultList;
    }

}
