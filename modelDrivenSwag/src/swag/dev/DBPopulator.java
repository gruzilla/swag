package swag.dev;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import swag.Utils;
import swag.db.dao.TroopTypesDAO;
import swag.db.model.Base;
import swag.db.model.BaseSquare;
import swag.db.model.Map;
import swag.db.model.MapSquare;
import swag.db.model.ResourceBuilding;
import swag.db.model.Resources;
import swag.db.model.SquareBoost;
import swag.db.model.TroopBuilding;
import swag.db.model.TroopBuildingTypes;
import swag.db.model.TroopTypes;
import swag.db.model.User;
import swag.db.model.UserResourceCount;
import swag.db.model.UserResourceCountPK;

@Stateless
@Path("populatedb")
public class DBPopulator {
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	public void insertValues(){
		User user1 = new User(
				1000,
				"roflcopter", 
				Utils.md5(Utils.md5("test")+ "lol"), 
				"rofl copter", 
				"addr", 
				"lol", 
				"kek@eugn.com", 
				1);
		User user2 = new User(
				1001,
				"lollerskater", 
				Utils.md5(Utils.md5("test")+ "lol"), 
				"loller skater", 
				"addr", 
				"lol", 
				"gaylord@buttsecks.com", 
				1);
		
		Map map = new Map(
				1000,
				"themap",
				100);

		MapSquare [][] mapSquares = new MapSquare[10][10];
		int mapSquareIdCounter = 1000;
		for (int x = 0; x <= 9; x++){
			for (int y = 0; y <= 9; y++){
				mapSquares[x][y] = new MapSquare(mapSquareIdCounter, x, y);
				mapSquares[x][y].setPartOfMap(map);
				mapSquareIdCounter++;
			}
		}
		
		Base base1 = new Base(
				1000, 
				"roflbase", 
				mapSquares[3][4], 
				new Date(System.currentTimeMillis()), 
				true, 
				null, 
				user1);
		
		Base base2 = new Base(
				1001, 
				"lolbase", 
				mapSquares[5][8], 
				new Date(System.currentTimeMillis()), 
				true, 
				null, 
				user2);
		
		int baseSquareIdCounter = 1000;
		HashSet<BaseSquare> base1Squares = new HashSet<BaseSquare>();
		HashSet<BaseSquare> base2Squares = new HashSet<BaseSquare>();
 		for (int x = 0; x <= 2; x++){
			for (int y = 0; y <= 2; y++){
				base1Squares.add(new BaseSquare(
						baseSquareIdCounter,
						x,
						y,
						null,
						null,
						base1));
				baseSquareIdCounter++;
				
				base2Squares.add(new BaseSquare(
						baseSquareIdCounter,
						x,
						y,
						null,
						null,
						base2));
				
				baseSquareIdCounter++;
			}
		}
		base1.setIsOnBaseSquareSet(base1Squares);
		base2.setIsOnBaseSquareSet(base2Squares);
		
		Resources resource1 = new Resources(1000, "beer", true);
		Resources resource2 = new Resources(1001, "condoms", true);
		
		UserResourceCount user1resource1 = new UserResourceCount(
				new UserResourceCountPK(1000, 1000),
				user1, 
				resource1, 
				0);
		UserResourceCount user1resource2 = new UserResourceCount(
				new UserResourceCountPK(1000, 1001),
				user1, 
				resource2, 
				0);
		UserResourceCount user2resource1 = new UserResourceCount(
				new UserResourceCountPK(1001, 1000),
				user2, 
				resource1, 
				0);
		UserResourceCount user2resource2 = new UserResourceCount(
				new UserResourceCountPK(1001, 1001),
				user2, 
				resource2, 
				0);
		
		SquareBoost mapSquareBoost1 = new SquareBoost(
				1000,
				1.5,
				null,
				mapSquares[7][3], 
				resource1);
		
		SquareBoost mapSquareBoost2 = new SquareBoost(
				1001,
				1.5,
				null,
				mapSquares[4][4], 
				resource2);
		
		mapSquares[7][3].setBoostedBySquareBoost(mapSquareBoost1);
		mapSquares[4][4].setBoostedBySquareBoost(mapSquareBoost2);
		
		Iterator<BaseSquare> base1Iterator = base1Squares.iterator();
		ResourceBuilding resource1building = new ResourceBuilding(
				1000,
				1,
				100,
				100,
				new Date(System.currentTimeMillis()),
				base1Iterator.next(),
				resource1);
		
		ResourceBuilding resource2building = new ResourceBuilding(
				1001,
				1,
				100,
				100,
				new Date(System.currentTimeMillis()),
				base2Squares.iterator().next(),
				resource2);
		
		TroopTypes troop1 = new TroopTypes(1000, "nipple warrior", 100.0, 1.0, 10, 15);
		TroopTypes troop2 = new TroopTypes(1001, "knight of the golden clit", 100.0, 1.5, 10, 25);
		
		TroopBuilding troopbuilding = new TroopBuilding(
				1002,
				1,
				100,
				100,
				new Date(System.currentTimeMillis()),
				base1Iterator.next(),
				"barracks",
				null);
				
		
		TroopBuildingTypes troopBuildingType = new TroopBuildingTypes(
				1003,
				"barracks",
				troop1,
				troopbuilding);
		
		mergeOrPersist(user1);
		mergeOrPersist(user2);
		mergeOrPersist(map);
		for (int x = 0; x <= 9; x++){
			for (int y = 0; y <= 9; y++){
				mergeOrPersist(mapSquares[x][y]); 
			}
		}
		mergeOrPersist(base1);
		mergeOrPersist(base2);
		for(Iterator<BaseSquare> iter = base1Squares.iterator(); iter.hasNext();){
			mergeOrPersist(iter.next());
		}
		for(Iterator<BaseSquare> iter = base2Squares.iterator(); iter.hasNext();){
			mergeOrPersist(iter.next());
		}
		
		mergeOrPersist(resource1);
		mergeOrPersist(resource2);
		
		mergeOrPersist(user1resource1);
		mergeOrPersist(user1resource2);
		mergeOrPersist(user2resource1);
		mergeOrPersist(user2resource2);
		
		mergeOrPersist(mapSquareBoost1);
		mergeOrPersist(mapSquareBoost2);

		mergeOrPersist(resource1building);
		mergeOrPersist(resource2building);
		
		mergeOrPersist(troop1);
		mergeOrPersist(troop2);
		
		mergeOrPersist(troopbuilding);
		mergeOrPersist(troopBuildingType);
	}
	
	private void mergeOrPersist(Object entity){
		try{
			em.persist(entity);
		}
		catch(EntityExistsException e){
			em.merge(entity);
		}
	}
}
	