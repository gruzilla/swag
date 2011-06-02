package swag.rest;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface AuthenticationBeanRemote {
	public static final String EJB_NAME = "BookingBean";
	
	public Object login(String username, String hashedpw);

}
