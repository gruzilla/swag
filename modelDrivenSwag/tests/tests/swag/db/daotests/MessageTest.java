package tests.swag.db.daotests;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import swag.db.dao.BaseDAO;
import swag.db.dao.MessageDAO;
import swag.db.dao.UserDAO;
import swag.db.model.Message;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class MessageTest extends AbstractDBTest {
	private static UserDAO userDAO = new UserDAO();
	private static MessageDAO messageDao = new MessageDAO();
	private static User from;
	private static User to;
	
	@BeforeClass
	public static void initDAO() throws Exception {
		UserDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		MessageDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		
		User from = UserTest.createUser("msga", "msga", "msga", 1, "msga", "msga");
		userDAO.persist(from);
		User to = UserTest.createUser("msgb", "msgb", "msgb", 1, "msgb", "msgb");
		userDAO.persist(to);
	}
	
	@Test
	public void write() {
		Message msg = createMessage(from, to, "lol", new Date(23523523623L));
		messageDao.persist(msg);
	}
	
	@Test
	public void read() {
		Message msg = createMessage(from, to, "roflwafl", new Date(23523523623L));
		messageDao.persist(msg);
		int id = msg.getId();
		Message read = messageDao.get(id);
		Assert.assertEquals(msg, read);
	}
	
	public static Message createMessage(User from, User to, String content, Date date) {
		Message msg = new Message();
		msg.setFromUser(from);
		msg.setMessage(content);
		msg.setTime(date);
		msg.setToUser(to);
		return msg;
	}
}
