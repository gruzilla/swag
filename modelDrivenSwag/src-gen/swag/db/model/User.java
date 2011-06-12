package swag.db.model;

import com.sun.xml.bind.CycleRecoverable;
import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_user")
public class User implements Serializable, CycleRecoverable  {

	private static final long serialVersionUID = 8763303792339334944L;

	private Integer id;
	

	public User() {
	}

	public User(Integer id, String username, String password, String name,
			String address, String salt, String email, Integer timezone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.salt = salt;
		this.email = email;
		this.timezone = timezone;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String username;

	private String password;

	private String name;

	private String address;

	private String salt;

	private String email;

	private Integer timezone;

	private Set<Message> fromMessageSet;

	private Set<Message> toMessageSet;

	private Set<UserResourceCount> hasResourcesSet;

	@Column(nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable = false)
	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable = false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	@OneToMany(mappedBy = "fromUser")
	public Set<Message> getFromMessageSet() {
		return fromMessageSet;
	}

	public void setFromMessageSet(Set<Message> varMessage) {
		fromMessageSet = varMessage;
	}

	@OneToMany(mappedBy = "toUser")
	public Set<Message> getToMessageSet() {
		return toMessageSet;
	}

	public void setToMessageSet(Set<Message> varMessage) {
		toMessageSet = varMessage;
	}

	@OneToMany(mappedBy = "hasUser")
	public Set<UserResourceCount> getHasResourcesSet() {

		return hasResourcesSet;
	}

	public void setHasResourcesSet(Set<UserResourceCount> varResources) {
		hasResourcesSet = varResources;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		User other = (User) obj;

		if (!fromMessageSet.equals(other.fromMessageSet))
			return false;

		if (!toMessageSet.equals(other.toMessageSet))
			return false;

		if (!hasResourcesSet.equals(other.hasResourcesSet))
			return false;

		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;

		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;

		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;

		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((fromMessageSet == null) ? 0 : fromMessageSet.hashCode());

		result = prime * result
				+ ((toMessageSet == null) ? 0 : toMessageSet.hashCode());

		result = prime * result
				+ ((hasResourcesSet == null) ? 0 : hasResourcesSet.hashCode());

		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());

		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());

		result = prime * result + ((address == null) ? 0 : address.hashCode());

		result = prime * result + ((salt == null) ? 0 : salt.hashCode());

		result = prime * result + ((email == null) ? 0 : email.hashCode());

		result = prime * result
				+ ((timezone == null) ? 0 : timezone.hashCode());

		return result;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		User n = new User();
		n.setId(this.getId());
		return n;
	}

	
}
