package swag.dev;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import swag.rest.session.UserSession;
import swag.singletons.Chats;


@Stateful
@SessionScoped
@Path("full")
public class FullTest {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	EntityManager em;
	
	@Inject
	UserSession session;
	
	@EJB
	Chats chats;
	
	Integer beanCounter = 0;
	
	public FullTest() {
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
		return "normaL amount: "+beanCounter+"\n"+
			(session==null?"sessnull":"valid?:"+session.isValid())+"\n"+
			(em==null?"emnull":"emexists")+"\n"+
			(chats==null?"chatsnull":"chatsexist: "+(chats.getManager()==null?"singleemNull" : "single em exists"))+"\n";
	}
	
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
