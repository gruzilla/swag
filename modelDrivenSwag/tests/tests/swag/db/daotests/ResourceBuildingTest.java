package tests.swag.db.daotests;
import java.sql.Date;
import java.util.HashSet;
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
import swag.db.model.ResourceBuilding;
import swag.db.model.Resources;
import swag.db.model.User;
import tests.swag.utility.AbstractDBTest;



public class ResourceBuildingTest extends AbstractDBTest {
	
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
		resourceBuildingDAO.persist(createBuilding(null));
	}
	
	@Test
	public void read() {
		ResourceBuilding building = createBuilding(null);
		resourceBuildingDAO.persist(building);
		int id = building.getId();
		ResourceBuilding read = resourceBuildingDAO.get(id);
		Assert.assertEquals(building, read);
	}
	
	@Test
	public void delete() {
		ResourceBuilding building = createBuilding(null);
		resourceBuildingDAO.persist(building);
		int id = building.getId();
		resourceBuildingDAO.delete(building);
		Assert.assertNull(resourceBuildingDAO.get(id));
	}
	
	@Test
	public void associationWithResources() {
		//create wood
		Resources res = ResourceTest.createResources("wood");
		resourcesDAO.persist(res);
		//create the building that produces wood
		ResourceBuilding building = createBuilding(res);
		resourceBuildingDAO.persist(building);
		//and another one
		ResourceBuilding building2 = createBuilding(res);
		resourceBuildingDAO.persist(building2);
		//add the building association to the resources
		res.getProducesResourceBuildingSet().add(building);
		res.getProducesResourceBuildingSet().add(building2);
		//update the resources in the db
		resourcesDAO.update(res);
	}
	
	public static ResourceBuilding createBuilding(Resources produces) {
		ResourceBuilding building = new ResourceBuilding();
		building.setProducesResources(produces);
		building.setCreationTime(new Date(2623464326432L));
		building.setCurrentHealth(100);
		building.setLevel(10);
		building.setMaxHealth(100);
		return building;
	}
}
