package swag.singletons.chats;

public class Conversation {
	private Integer aId;
	private Integer bId;
	private String id;
	
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
}
