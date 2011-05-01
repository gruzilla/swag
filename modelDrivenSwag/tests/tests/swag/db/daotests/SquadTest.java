package tests.swag.db.daotests;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import swag.db.dao.BaseDAO;
import swag.db.dao.SquadDAO;
import swag.db.dao.UserDAO;
import swag.db.model.MapSquare;
import swag.db.model.Squad;
import swag.db.model.SquadMovement;
import swag.db.model.Troop;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class SquadTest extends AbstractDBTest {
	
	private static SquadDAO squadDAO = new SquadDAO();
	private static UserDAO userDAO = new UserDAO();
	private static User user;
	
	@BeforeClass
	public static void initDAO() throws Exception {
		UserDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		SquadDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		user = UserTest.createUser("squadowner", "", "", 1, "squadowner", "");
		userDAO.persist(user);
	}
	
	@Test
	public void create() {
		Squad squad = createSquad(user, null, null, "roflwafl", null);
		squadDAO.persist(squad);
	}
	
	@Test
	public void read() {
		Squad squad = createSquad(user, null, null, "omggay", null);
		squadDAO.persist(squad);
		int id = squad.getId();
		Squad read = squadDAO.get(id);
		Assert.assertEquals(squad, read);
	}
	
	@Test
	public void delete() {
		Squad squad = createSquad(user, null, null, "dsdfsdfsd", null);
		squadDAO.persist(squad);
		int id = squad.getId();
		squadDAO.delete(squad);
		Assert.assertNull(squadDAO.get(id));
	}
	
	public static Squad createSquad(User owner, MapSquare square, SquadMovement movement, String name, Set<Troop> troop) {
		Squad squad = new Squad();
		squad.setBelongsToUser(owner);
		squad.setIsOnMapSquare(square);
		squad.setMovementspeed(10.0);
		squad.setMovesSquadMovement(movement);
		squad.setName(name);
		squad.setPartOfTroopSet(troop);
		return squad;
	}
}
