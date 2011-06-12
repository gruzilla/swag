package swag.db.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;
import java.io.Serializable;

@Embeddable
public class TroopCostPK implements Serializable {

	private static final long serialVersionUID = -8169615659790068999L;

	private Integer troopTypesID;
	private Integer resourcesID;

	public TroopCostPK() {
	}

	public Integer getTroopTypesID() {
		return troopTypesID;
	}

	public void setTroopTypesID(Integer zomgbbq) {
		this.troopTypesID = zomgbbq;
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

		TroopCostPK other = (TroopCostPK) obj;

		if (!troopTypesID.equals(other.troopTypesID))
			return false;
		if (!resourcesID.equals(other.resourcesID))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((troopTypesID == null) ? 0 : troopTypesID.hashCode());
		result = prime * result
				+ ((resourcesID == null) ? 0 : resourcesID.hashCode());
		return result;
	}

}
