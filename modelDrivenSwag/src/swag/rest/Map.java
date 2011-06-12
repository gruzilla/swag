package swag.rest;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import swag.db.model.Base;
import swag.db.model.MapObject;
import swag.db.model.MapSquare;
import swag.db.model.Squad;

@Stateless
@Path("map")
public class Map {
	@PersistenceContext
	private EntityManager em;
	
	@Produces("application/json")
	@GET
	@Path("query")
	public String query() {
		return query_backend();
	}
	
	@GET
	@Path("queryt")
	public String queryt() {
		return query_backend();
	}
	
	private String query_backend() {
		TypedQuery<swag.db.model.Map> q = em.createQuery("SELECT s FROM swa_map s", swag.db.model.Map.class);
		List<swag.db.model.Map> maps = q.getResultList();
		swag.db.model.Map map = maps.get(0);

		//XStream xstream = new XStream(new JettisonMappedXmlDriver());
		//xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
		//xstream.alias("square", MapSquare.class);
		
		JSONArray jsa = new JSONArray();
		String s = "";
		for(MapSquare mapsqr : map.getPartOfMapSquareSet()) {
			JSONObject jso = new JSONObject();
			try {
				jso.append("x", mapsqr.getPositionX());
				jso.append("y", mapsqr.getPositionY());	
			}
			catch(Throwable e) {
				System.err.println("lolol");
				return e.getStackTrace().toString();
			}
			
			try {
				jso.append("resource", mapsqr.getBoostedBySquareBoost().getBoostsResources().getName());
			}
			catch(Throwable e) {
				//none
			}
			try {
				for(MapObject mo : mapsqr.getIsOnMapObjectSet()) {
					if(mo instanceof Base) {
						jso.append("base", ((Base)mo).getBelongsToUser().getName());
					}
					else if(mo instanceof Squad) {
						jso.append("squad", ((Squad)mo).getId());
					}
				}
			}
			catch(Throwable e) {
				//none
			}
			jsa.put(jso);
		}
		return jsa.toString();
	}
}
