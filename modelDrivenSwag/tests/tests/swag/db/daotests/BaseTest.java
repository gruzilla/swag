package tests.swag.db.daotests;
import java.sql.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import swag.db.dao.BaseDAO;
import swag.db.dao.UserDAO;
import swag.db.model.Base;
import swag.db.model.BaseSquare;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class BaseTest extends AbstractDBTest {
	
	private static UserDAO userDAO = new UserDAO();
	private static BaseDAO baseDAO = new BaseDAO();
	@BeforeClass
	public static void initDAO() throws Exception {
		UserDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		BaseDAO.initializeEntityManagerFactory(getEntityManagerFactory());
	}
	
	@Test
	public void create() {
		baseDAO.persist(createBase(null, "createbase", null));
	}
	
	@Test
	public void read() {
		Base base = createBase(null, "basdfbastsgeqryt325yh", null);
		baseDAO.persist(base);
		int id = base.getId();
		Base read = baseDAO.get(id);
		Assert.assertEquals(base, read);
	}
	
	@Test
	public void delete() {
		Base base = createBase(null, "235fr32rfaer2qf", null);
		baseDAO.persist(base);
		int id = base.getId();
		baseDAO.delete(base);
		Assert.assertNull(baseDAO.get(id));
	}
	
	public static Base createBase(User owner, String name, Set<BaseSquare> baseSquares) {
		Base base = new Base();
		base.setBelongsToUser(owner);
		base.setCreationTime(new Date(3253252352L));
		base.setIsOnBaseSquareSet(baseSquares);
		base.setIsStarterBase(true);
		base.setName(name);
		return base;
	}
}
