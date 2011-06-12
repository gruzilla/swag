package swag.singletons.chats;

public class Message {
	private Long time;
	private String message;
	private Integer userId;

	public Message(Integer user, Long time, String message) {
		this.setUserId(user);
		this.setTime(time);
		this.setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getTime() {
		return time;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}
}
