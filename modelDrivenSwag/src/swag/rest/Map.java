package swag.rest;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import swag.db.model.MapSquare;

@Stateless
@Path("map")
public class Map {
	@PersistenceContext
	private EntityManager em;
	
	@GET
	@Path("query")
	public String query() {
		TypedQuery<swag.db.model.Map> q = em.createQuery("SELECT m FROM swa_map m WHERE m.name = 'themap'", swag.db.model.Map.class);
		swag.db.model.Map map = q.getResultList().get(0);
		Set<MapSquare> squares = map.getPartOfMapSquareSet();
		JSONArray squareArray = new JSONArray(squares);
		
		JSONObject test = new JSONObject();
		try {
			test.accumulate("number-of-maps", squares.size());	
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		return test.toString();
		//return squareArray.toString();
	}
}
