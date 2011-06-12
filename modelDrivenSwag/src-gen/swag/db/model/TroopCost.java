package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_troopCost")
public class TroopCost {

	private TroopCostPK troopCostPK;
	private TroopTypes costsTroopTypes;
	private Resources costsResources;

	public TroopCost() {
	}

	@EmbeddedId
	public TroopCostPK getTroopCostPK() {
		return troopCostPK;
	}

	public void setTroopCostPK(TroopCostPK primaryKey) {
		troopCostPK = primaryKey;
	}

	@ManyToOne(optional = true)
	@MapsId("troopTypesID")
	public TroopTypes getCostsTroopTypes() {
		return this.costsTroopTypes;
	}

	public void setCostsTroopTypes(TroopTypes lol) {
		this.costsTroopTypes = lol;
	}

	@ManyToOne(optional = true)
	@MapsId("resourcesID")
	public Resources getCostsResources() {
		return costsResources;
	}

	public void setCostsResources(Resources rofl) {
		this.costsResources = rofl;
	}

}
