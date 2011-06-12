package swag.dev;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;

import swag.rest.session.UserSession;
import swag.singletons.Chats;

@Path("full")
public class FullTest {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	EntityManager em;
	
	@Context
	HttpServletRequest request;
	
	@EJB
	Chats chats;
	
	Integer beanCounter = 0;
	
	public FullTest() {
		System.out.println("full test instanciated");
	}
	
	@GET
	@Produces("application/json")
	public String get(@CookieParam("sessionId") String rofl) {
		
		/*
		if (wsContext == null) { return "ws is null"; }
		if (!wsContext.getMessageContext().containsKey("counter")) {
			wsContext.getMessageContext().put("counter", 0);
		}
		return "amount: "+wsContext.getMessageContext().get("counter");
		*/
		return "normaL amount: "+beanCounter+"\n"+
			(request==null?"reqNULL":"YYEEEEE:"+request.getCookies())+"\n"+
			(em==null?"emnull":"emexists")+"\n"+
			(rofl==null?"sessionId null":"cookie: "+rofl)+"\n"+
			"via cookie class: "+Cookie.valueOf("sessionId").getValue()+"\n"+
			(chats==null?"chatsnull":"chatsexist: "+(chats.getManager()==null?"singleemNull" : "single em exists"))+"\n";
	}
	
	@GET
	@Path("inc")
	@Produces("application/json")
	public String increment(@CookieParam("sessionId") String rofl) {
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
		(request==null?"reqNULL":"YYEEEEE:"+request.getCookies())+"\n"+
		(rofl==null?"sessionId null":"cookie: "+rofl)+"\n"+
		"via cookie class: "+Cookie.valueOf("sessionId").getValue()+"\n"+
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
		(request==null?"reqNULL":"YYEEEEE:"+request.getCookies())+"\n"+
		(em==null?"emnull":"em exists")+"\n";
	}
}
