package tests.swag.utility;


import java.io.File;
import java.net.MalformedURLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class AbstractDBTest {
	

	protected static String PERSISTENCE_UNIT_NAME = null;

	private static String DATABASE_DRIVER = "org.postgresql.Driver";
	private static String DATABASE_URL = "jdbc:postgresql://localhost/swa";
	private static String DATABASE_USER = "swa";
	private static String DATABASE_PASSWORD = "swa11";
	private static final String DATASET = "testdataset.xml";
	private static IDatabaseTester databaseTester = null;
	private static EntityManagerFactory emf = null;
	
	private static IDataSet dataSet = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PERSISTENCE_UNIT_NAME = "foo";
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
		public void setupDatabase() throws Exception {
		//setup();		
	}
	
	public void tearDownDatabase() throws Exception {
		//PersistenceManager.createPersistenceManager("lol");
		//close();
	}
	
	private void setup() throws Exception {
		getDatabaseTester();
		databaseTester.onSetup();
		
	}
	
	private void close() throws Exception {
		databaseTester.onTearDown();
	}
	
	private void getDatabaseTester() throws ClassNotFoundException, MalformedURLException, DataSetException {
		databaseTester = new JdbcDatabaseTester(DATABASE_DRIVER,
				DATABASE_URL, 
				DATABASE_USER, 
				DATABASE_PASSWORD);
		dataSet = new FlatXmlDataSetBuilder().build(new File(DATASET));
		databaseTester.setDataSet(dataSet);
	}
	public static void clear() throws Exception {
		databaseTester = new JdbcDatabaseTester(DATABASE_DRIVER,
				DATABASE_URL, 
				DATABASE_USER, 
				DATABASE_PASSWORD);
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(DATASET));
		DatabaseOperation.DELETE_ALL.execute(databaseTester.getConnection(), dataSet);
	}
		
}
