package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_troopBuildingTypes")
public class TroopBuildingTypes implements Serializable {

	private static final long serialVersionUID = -2348127004839808840L;
	
	public TroopBuildingTypes() {
		super();
	}

	public TroopBuildingTypes(Integer id, String type,
			TroopTypes builtByTroopTypes, TroopBuilding buildsTroopBuilding) {
		super();
		this.id = id;
		this.type = type;
		this.builtByTroopTypes = builtByTroopTypes;
		this.buildsTroopBuilding = buildsTroopBuilding;
	}

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String type;

	private TroopTypes builtByTroopTypes;

	private TroopBuilding buildsTroopBuilding;

	@Column(nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne(optional = true)
	public TroopTypes getBuiltByTroopTypes() {
		return builtByTroopTypes;
	}

	public void setBuiltByTroopTypes(TroopTypes varTroopTypes) {
		builtByTroopTypes = varTroopTypes;
	}

	@ManyToOne(optional = true)
	public TroopBuilding getBuildsTroopBuilding() {
		return buildsTroopBuilding;
	}

	public void setBuildsTroopBuilding(TroopBuilding varTroopBuilding) {
		buildsTroopBuilding = varTroopBuilding;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		TroopBuildingTypes other = (TroopBuildingTypes) obj;

		if (!builtByTroopTypes.equals(other.builtByTroopTypes))
			return false;

		if (!buildsTroopBuilding.equals(other.buildsTroopBuilding))
			return false;

		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((builtByTroopTypes == null) ? 0 : builtByTroopTypes
						.hashCode());

		result = prime
				* result
				+ ((buildsTroopBuilding == null) ? 0 : buildsTroopBuilding
						.hashCode());

		result = prime * result + ((type == null) ? 0 : type.hashCode());

		return result;
	}

}
