package swag.dev;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import swag.game.base.building.BuildingListing;

@Path("dbTesting")
@Stateful
@SessionScoped
public class QueryTest {

	@EJB
	BuildingListing listing;
	
	@GET
	@Path("buildingQueryTest")
	public String testQueries() {
		listing.listUserBuildings();
		return listing.toString();
	}
}
