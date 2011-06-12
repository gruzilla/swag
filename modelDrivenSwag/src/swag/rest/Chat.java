package swag.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;
import org.json.JSONObject;

import swag.singletons.Chats;
import swag.singletons.SessionManager;
import swag.singletons.chats.Conversation;

@Stateless
@Path("chat")
public class Chat {
	@PersistenceContext
	private EntityManager em;

	@EJB
	SessionManager manager;
	
	@EJB
	Chats chats;

	@Context
	private HttpServletRequest request;
	
	/**
	 * Default constructor. 
	 */
	public Chat() {
	}

	/**
	 * Retrieves representation of an instance of Message
	 * @return an instance of String
	 */
	@POST
	@Path("messages")
	@Produces("application/json")
	public String getMessages(@FormParam("activeChats") String activeChats, @FormParam("timeStamp") Long time) {
		System.out.println("message: "+activeChats+" time: "+time);
		String[] userIds = activeChats.split(",");
		JSONObject obj = new JSONObject();
		for (String userIdString : userIds) {
			Integer userId = new Integer(userIdString);
			Conversation conv = chats.getConversation(userId);
			
			if (conv == null) continue;
			
			String[] messages = conv.getMessages(manager.getSession(request).getUser().getId(), time);
			
			try {
				obj.put(userIdString, messages);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj.toString();
	}


	@POST
	@Path("initialize")
	@Produces("application/json")
	public String initialize(@FormParam("userId") Integer userId) {
		Conversation conv = chats.getConversation(userId);
		if (conv == null) {
			conv = chats.createConversation(userId, manager.getSession(request).getUser().getId());
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("status", conv.getId());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String addMessage(@FormParam("message") String message, @FormParam("to") Integer to, @FormParam("timestamp") Long time) {
		Conversation conv = chats.getConversation(to);
		if (conv == null) {
			return "";
		}
		conv.addMessage(to, message);
		return "";
	}
}