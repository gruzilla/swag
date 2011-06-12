package swag.singletons.chats;

import java.util.ArrayList;
import java.util.Date;

public class Conversation {
	private Integer aId;
	private Integer bId;
	private String id;
	private ArrayList<Message> messages = new ArrayList<Message>();
	
	public Conversation(Integer userAId, Integer userBId) {
		aId = userAId;
		bId = userBId;
		id = generateId();
	}

	private String generateId() {
		return "" + (Math.random()+aId+bId);
	}

	public String getId() {
		return id;
	}
	
	public void addMessage(Integer from, String message) {
		messages.add(new Message(from, (new Date()).getTime(), message));
	}
	
	public String[] getMessages(Integer userId, Long timestamp) {
		ArrayList<String> send = new ArrayList<String>();
		for (Message msg : messages) {
			if (msg.getTime() > timestamp
				&& msg.getUserId() != userId) {
				send.add(msg.getMessage());
			}
		}
		return send.toArray(new String[]{});
	}
}
