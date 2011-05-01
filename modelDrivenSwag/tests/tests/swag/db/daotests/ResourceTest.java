package tests.swag.db.daotests;
import java.sql.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import swag.db.dao.BaseDAO;
import swag.db.dao.ResourceBuildingDAO;
import swag.db.dao.ResourcesDAO;
import swag.db.dao.UserDAO;
import swag.db.model.Base;
import swag.db.model.BaseSquare;
import swag.db.model.Resources;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class ResourceTest extends AbstractDBTest {
	
	private static UserDAO userDAO = new UserDAO();
	private static BaseDAO baseDAO = new BaseDAO();
	private static ResourceBuildingDAO resourceBuildingDAO = new ResourceBuildingDAO();
	private static ResourcesDAO resourcesDAO = new ResourcesDAO();
	
	@BeforeClass
	public static void initDAO() throws Exception {
		UserDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		BaseDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		ResourceBuildingDAO.initializeEntityManagerFactory(getEntityManagerFactory());
		ResourcesDAO.initializeEntityManagerFactory(getEntityManagerFactory());
	}
	
	@Test
	public void create() {
		resourcesDAO.persist(createResources("createiron"));
	}
	
	@Test
	public void read() {
		Resources res = createResources("createiron");
		resourcesDAO.persist(res);
		int id = res.getId();
		Resources read = resourcesDAO.get(id);
		Assert.assertEquals(res, read);
	}
	
	@Test
	public void delete() {
		Resources res = createResources("wefsdf32wfs");
		resourcesDAO.persist(res);
		int id = res.getId();
		resourcesDAO.delete(res);
		Assert.assertNull(resourcesDAO.get(id));
	}
	
	public static Resources createResources(String name) {
		Resources resources = new Resources();
		resources.setDefaultResource(true);
		resources.setName(name);
		return resources;
	}
}
