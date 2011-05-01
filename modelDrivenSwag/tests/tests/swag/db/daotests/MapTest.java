package tests.swag.db.daotests;
import java.util.HashSet;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import swag.db.dao.BaseDAO;
import swag.db.dao.MapDAO;
import swag.db.dao.MapSquareDAO;
import swag.db.dao.SquadDAO;
import swag.db.dao.UserDAO;
import swag.db.model.Base;
import swag.db.model.Map;
import swag.db.model.MapObject;
import swag.db.model.MapSquare;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class MapTest extends AbstractDBTest {
	private static UserDAO userDAO = new UserDAO();
	private static BaseDAO baseDAO = new BaseDAO();
	private static MapDAO mapDAO = new MapDAO();
	private static MapSquareDAO mapSquareDAO = new MapSquareDAO();
	private static SquadDAO squadDAO = new SquadDAO();
	private static HashSet<User> mapUsers = new HashSet<User>();
	private static HashSet<MapSquare> mapSquares = new HashSet<MapSquare>();
	
	@BeforeClass
	public static void initDAO() throws Exception {
		UserDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		MapDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		MapSquareDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		BaseDAO.initializeEntityManagerFactory(getEntityManagerFactory());
	}
	
	@Test
	public void bigTest() {
		//insert all the squares
		setupSquares();
		//insert all the users
		setupUsers();
		//create the map with the squares
		Map map = createMap("awesomemap");
		//persist it
		mapDAO.persist(map);
		int id = map.getId();
		Map read = mapDAO.get(id);
		//check if it is really in the db
		Assert.assertEquals(map, read);
		//assign the map as the parent of the squares
		for(MapSquare square : mapSquares) {
			square.setPartOfMap(map);
			mapSquareDAO.update(square);
		}
		Base base = BaseTest.createBase(mapUsers.iterator().next(), "mapbase", null);
		//but the base on the first square
		MapSquare square = mapSquares.iterator().next();
		base.setIsOnMapSquare(square);
		//persist the base
		baseDAO.persist(base);
		//put the base in the square as well
		HashSet<MapObject> squareObjects = new HashSet<MapObject>();
		squareObjects.add(base);
		square.setIsOnMapObjectSet(squareObjects);
		//update the square
		mapSquareDAO.update(square);
	}	
	
	public static void setupUsers() {
		mapUsers.add(UserTest.createUser("mapa", "map", "map", 1, "map", "map"));
		mapUsers.add(UserTest.createUser("mapb", "map", "map", 1, "map", "map"));
		mapUsers.add(UserTest.createUser("mapc", "map", "map", 1, "map", "map"));
		mapUsers.add(UserTest.createUser("mapd", "map", "map", 1, "map", "map"));
		mapUsers.add(UserTest.createUser("mape", "map", "map", 1, "map", "map"));
		for(User user : mapUsers) {
			userDAO.persist(user);
		}
	}
	
	public static void setupSquares() {
		MapSquare square;
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				square = new MapSquare();
				square.setPositionX(x);
				square.setPositionY(y);
				mapSquares.add(square);
				mapSquareDAO.persist(square);
			}
		}
	}
	
	public static Map createMap(String name) {
		Map map = new Map();
		map.setMaxUsers(10);
		map.setName(name);
		map.setPlaysOnUserSet(mapUsers);
		map.setPartOfMapSquareSet(mapSquares);
		return map;
	}
}
