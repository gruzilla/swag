package swag.rest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	
	public static void main(String[] args) throws NamingException {
		
		Context context = new InitialContext();
		AuthenticationBeanRemote remote = (AuthenticationBeanRemote) context.lookup(AuthenticationBeanRemote.EJB_NAME);
		remote.login("firedA", "wat");
	}

}
