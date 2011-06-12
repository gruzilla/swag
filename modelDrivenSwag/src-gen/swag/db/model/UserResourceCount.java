package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_userResourceCount")
public class UserResourceCount {

	private UserResourceCountPK userResourceCountPK;
	private User hasUser;
	private Resources hasResources;

	private Integer amount;

	public UserResourceCount() {
	}
	
	public UserResourceCount(UserResourceCountPK userResourceCountPK,
			User hasUser, Resources hasResources, Integer amount) {
		super();
		this.userResourceCountPK = userResourceCountPK;
		this.hasUser = hasUser;
		this.hasResources = hasResources;
		this.amount = amount;
	}



	@Column(nullable = false)
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@EmbeddedId
	public UserResourceCountPK getUserResourceCountPK() {
		return userResourceCountPK;
	}

	public void setUserResourceCountPK(UserResourceCountPK primaryKey) {
		userResourceCountPK = primaryKey;
	}

	@ManyToOne(optional = true)
	@MapsId("userID")
	public User getHasUser() {
		return this.hasUser;
	}

	public void setHasUser(User lol) {
		this.hasUser = lol;
	}

	@ManyToOne(optional = true)
	@MapsId("resourcesID")
	public Resources getHasResources() {
		return hasResources;
	}

	public void setHasResources(Resources rofl) {
		this.hasResources = rofl;
	}

}
