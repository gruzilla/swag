package swag.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import swag.db.model.User;

@Local
public interface StatefulBlahLocal {
	public static final String EJB_NAME = "StatefulBlah";
	
	public Object login(String username, String password);
	
	public void lol() ;
}
