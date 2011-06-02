package swag.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import swag.db.model.User;

/**
 * Session Bean implementation class StatefulBlah
 */
@Stateful(name="Statefulblah", mappedName = StatefulBlahLocal.EJB_NAME)
public class StatefulBlah implements StatefulBlahLocal {

    /**
     * Default constructor. 
     */

	private Logger logger = Logger.getLogger(Authentication.class);
	private User user;
	
	@PersistenceContext
	private EntityManager em;
    public StatefulBlah() {
        // TODO Auto-generated constructor stub
    	System.out.println("lol");
    }
	@Override
	public void lol() {
		System.out.println("rofl");
		// TODO Auto-generated method stub
		
	}
    
    @PostConstruct
    public void post() {
    		System.out.println("lol");
    		lol();
    		System.out.println(em);
    }
	@Override
	public Object login(String username, String hashedpw) {
		
			if (em == null) return "em is null";
			TypedQuery<User> qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname", User.class);
			qry.setParameter("uname", username);
			List<User> result = qry.getResultList();
			
			
			if (user != null) return "user already authenticated";
			if (result.size() != 1) return "user not found";
			
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				
				hashedpw += result.get(0).getSalt();
				
				md.update(hashedpw.getBytes());
				byte[] digest = md.digest();
				String sha1 = "";
				for ( int i=0; i < digest.length; i++ ) {
					String s = Integer.toHexString( digest[i]&0xFF );
					sha1 += (s.length() == 1 ) ? "0"+s : s;
				}
				
				qry = em.createQuery("SELECT u FROM swa_user u WHERE u.username LIKE :uname AND u.password LIKE :pwd", User.class);
				qry.setParameter("uname", username);
				qry.setParameter("pwd", sha1);
				result = qry.getResultList();
				
				if (result.size() == 1) {
					logger.info("user authenticated");
					user = result.get(0);
					return result.get(0);
				} else {
					return "not found with pw "+sha1;
				}
			} catch (NoSuchAlgorithmException e) {
				return "no such algorithm";
			}
		
	}
    
    
    

}
