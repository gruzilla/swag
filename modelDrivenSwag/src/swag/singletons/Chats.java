package swag.singletons;

import java.util.HashMap;

import javax.ejb.Singleton;

import swag.singletons.chats.Conversation;

@Singleton
public class Chats {
	
	private HashMap<String, Conversation> conversations = new HashMap<String, Conversation>();
	private HashMap<Integer, String> userMappings = new HashMap<Integer, String>();
	
	public Chats() {
		System.out.println("chats created");
	}
	
	public Conversation createConversation(Integer userAId, Integer userBId) {
		Conversation conv = new Conversation(userAId, userBId);
		conversations.put(conv.getId(), conv);
		userMappings.put(userAId, conv.getId());
		userMappings.put(userBId, conv.getId());
		
		System.out.println("created chat "+conv.getId());
		return conv;
	}
	
	public Conversation getConversation(Integer userId) {
		if (!userMappings.containsKey(userId)) return null;
		return conversations.get(userMappings.get(userId));
	}
}
