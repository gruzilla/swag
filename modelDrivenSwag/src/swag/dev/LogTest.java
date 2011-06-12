package swag.dev;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import swag.Logger;

@Stateless
@Path("log")
public class LogTest {
	
	public LogTest() {
		System.out.println("LogTest instanciated!");
	}
	
	@GET
	@Produces("application/json")
	public String get() {
		
		String msg = new String("a test entry to the log");
		
		Logger.writeLog(msg);
		
		return("Wrote '"+msg+"' to logs/swag.log in glassfish domain");
	}
}
