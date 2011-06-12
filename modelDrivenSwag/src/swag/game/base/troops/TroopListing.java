package swag.game.base.troops;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import swag.db.model.Troop;
import swag.db.model.TroopTypes;


@Path("buildableTroops")
public class TroopListing {

	@PersistenceContext
	private EntityManager em;
	
	private String allTroops = "select * from swa_trooptypes";
	
	public TroopListing () {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TroopTypes> getBuildableTroops() {
		Query query = em.createNativeQuery(allTroops);
		return ((List<TroopTypes>) query.getResultList());
	}
	
}
