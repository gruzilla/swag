package swag.dev;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import swag.rest.session.UserSession;


@Stateless
@Path("less")
public class LessTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserSession session;
	
	@EJB
	FullTest full;
	
	Integer beanCounter = 0;
	
	public LessTest() {
		System.out.println("full test instanciated");
	}
	
	@GET
	@Produces("application/json")
	public String get() {
		
		/*
		if (wsContext == null) { return "ws is null"; }
		if (!wsContext.getMessageContext().containsKey("counter")) {
			wsContext.getMessageContext().put("counter", 0);
		}
		return "amount: "+wsContext.getMessageContext().get("counter");
		*/
		return "less amount: "+beanCounter+"\n"+
		(session==null?"sessnull":session.isValid())+"\n"+
		(em==null?"emnull":"")+"\n";
	}
	/*
	Cannot resolve an ambiguous dependency between
	[ManagedBean-class swag.db.model.Building,
	ManagedBean-class swag.db.model.ResourceBuilding, 
	ManagedBean-class swag.db.model.TroopBuilding]
 
	 */
	@GET
	@Path("inc")
	@Produces("application/json")
	public String increment() {
		/*
		if (wsContext == null) { return "ws is null"; }
		Integer counter = 0;
		if (!wsContext.getMessageContext().containsKey("counter")) {
			wsContext.getMessageContext().put("counter", counter);
		} else {
			counter++;
			wsContext.getMessageContext().put("counter", counter);
			counter = (Integer)wsContext.getMessageContext().get("counter");
		}
		*/
		beanCounter++;
		
		return "incamount: "+beanCounter+"\n"+
		(session==null?"sessnull":session.isValid())+"\n"+
		(em==null?"emnull":"em exists")+"\n";
	}
	
	@GET
	@Path("dec")
	@Produces("application/json")
	public String decrement() {
		/*
		if (wsContext == null) { return "ws is null"; }
		Integer counter = 0;
		if (!wsContext.getMessageContext().containsKey("counter")) {
			wsContext.getMessageContext().put("counter", counter);
		} else {
			counter--;
			wsContext.getMessageContext().put("counter", counter);
			counter = (Integer)wsContext.getMessageContext().get("counter");
		}
		*/
		beanCounter--;
		return "decamount: "+beanCounter+"\n"+
		(session==null?"sessnull":session.isValid())+"\n"+
		(em==null?"emnull":"em exists")+"\n";
	}
}
