package tests.swag.utility;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;

public class ExtractDataSet {

	
	private static final String DATASET_DTD = "testdataset.dtd";
	private static final String DATASET_XML = "testdataset.xml";
	private static String DATABASE_DRIVER = "org.postgresql.Driver";
	private static String DATABASE_URL = "jdbc:postgresql://localhost/swa";
	private static String DATABASE_USER = "swa";
	private static String DATABASE_PASSWORD = "swa11";
	private static Object DATATYPE_FACTORY = new PostgresqlDataTypeFactory();
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		
		
		if (args.length == 0) {
			System.err.println("Error, you need at least 1 table as parameter");
			return;
		}
		
		Class.forName(DATABASE_DRIVER);
		
		Connection jdbcConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, DATATYPE_FACTORY);
        
        /* partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("city_Application", "SELECT * FROM city_Application WHERE AccessRight_idAccessRight='0'");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial-testdataset.xml"));
        */  
        //"full" database export. Only the specified Tables!
        IDataSet fullDataSet = connection.createDataSet(args);
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream(DATASET_DTD));
        FlatXmlWriter datasetWriter = new FlatXmlWriter(new FileOutputStream(DATASET_XML));
        datasetWriter.setDocType(DATASET_DTD);
        datasetWriter.write(fullDataSet);
	}

}
