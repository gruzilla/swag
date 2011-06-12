package swag.db.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;
import java.io.Serializable;

@Embeddable
public class UserResourceCountPK implements Serializable {

	private static final long serialVersionUID = 2450934802481070836L;

	private Integer userID;
	private Integer resourcesID;

	public UserResourceCountPK() {
	}

	public UserResourceCountPK(Integer userID, Integer resourcesID) {
		super();
		this.userID = userID;
		this.resourcesID = resourcesID;
	}



	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer zomgbbq) {
		this.userID = zomgbbq;
	}

	public Integer getResourcesID() {
		return resourcesID;
	}

	public void setResourcesID(Integer ygos) {
		this.resourcesID = ygos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		UserResourceCountPK other = (UserResourceCountPK) obj;

		if (!userID.equals(other.userID))
			return false;
		if (!resourcesID.equals(other.resourcesID))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result
				+ ((resourcesID == null) ? 0 : resourcesID.hashCode());
		return result;
	}

}
