package tests.swag.db.daotests;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import swag.db.dao.BaseDAO;
import swag.db.dao.UserDAO;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class UserTest extends AbstractDBTest {
	
	private static UserDAO userDAO = new UserDAO();
	@BeforeClass
	public static void initDAO() throws Exception {
		UserDAO.initializeEntityManagerFactory(getEntityManagerFactory());
	}
	
	@Test
	public void createUser() {
		userDAO.persist(createUser("a", "a", "a", 1, "a", "a"));
	}
	
	@Test
	public void readUser() {
		User user = createUser("b", "b", "b", 1, "b", "b");
		userDAO.persist(user);
		int id = user.getId();
		User read = userDAO.get(id);
		Assert.assertEquals(user, read);
	}
	
	@Test
	public void deleteUser() {
		User user = createUser("c", "c", "c", 1, "c", "c");
		userDAO.persist(user);
		int id = user.getId();
		userDAO.delete(user);
		Assert.assertNull(userDAO.get(id));
	}
	
	public static User createUser(String name, String pw, String salt, int timeZone, String userName, String address) {
		User user = new User();
		user.setName(name);
		user.setPassword(pw);
		user.setSalt(salt);
		user.setTimezone(1);
		user.setUsername(userName);
		user.setAddress(address);
		return user;
	}
}
