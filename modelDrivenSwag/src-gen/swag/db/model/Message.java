package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_message")
public class Message implements Serializable {

	private static final long serialVersionUID = 5886577644186878657L;

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String message;

	private Date time;

	private User fromUser;

	private User toUser;

	@Column(nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@ManyToOne(optional = true)
	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User varUser) {
		fromUser = varUser;
	}

	@ManyToOne(optional = true)
	public User getToUser() {
		return toUser;
	}

	public void setToUser(User varUser) {
		toUser = varUser;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Message other = (Message) obj;

		if (!fromUser.equals(other.fromUser))
			return false;

		if (!toUser.equals(other.toUser))
			return false;

		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;

		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((fromUser == null) ? 0 : fromUser.hashCode());

		result = prime * result + ((toUser == null) ? 0 : toUser.hashCode());

		result = prime * result + ((message == null) ? 0 : message.hashCode());

		result = prime * result + ((time == null) ? 0 : time.hashCode());

		return result;
	}

}
