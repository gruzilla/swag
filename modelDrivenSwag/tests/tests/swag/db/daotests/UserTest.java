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
		User user = new User();
		user.setName("lol");
		user.setPassword("wat");
		user.setSalt("omfg");
		user.setTimezone(1);
		user.setUsername("hahahaha");
		user.setAddress("wonderland");
		userDAO.persist(user);
	}
}
