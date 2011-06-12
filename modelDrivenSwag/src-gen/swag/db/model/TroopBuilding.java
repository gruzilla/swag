package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_troopBuilding")
public class TroopBuilding extends Building implements Serializable {

	private static final long serialVersionUID = -5219105928195520360L;

	public TroopBuilding() {
		super();
	}

	public TroopBuilding(Integer id, Integer level, Integer currentHealth,
			Integer maxHealth, Date creationTime, BaseSquare builtOnBaseSquare,
			String buildingType,
			Set<TroopBuildingTypes> buildsTroopBuildingTypesSet) {
		super(id, level, currentHealth, maxHealth, creationTime,
				builtOnBaseSquare);
		this.buildingType = buildingType;
		this.buildsTroopBuildingTypesSet = buildsTroopBuildingTypesSet;
	}

	private String buildingType;

	private Set<TroopBuildingTypes> buildsTroopBuildingTypesSet;

	@Column(nullable = false)
	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	@OneToMany(mappedBy = "buildsTroopBuilding")
	public Set<TroopBuildingTypes> getBuildsTroopBuildingTypesSet() {
		return buildsTroopBuildingTypesSet;
	}

	public void setBuildsTroopBuildingTypesSet(
			Set<TroopBuildingTypes> varTroopBuildingTypes) {
		buildsTroopBuildingTypesSet = varTroopBuildingTypes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		TroopBuilding other = (TroopBuilding) obj;

		if (!buildsTroopBuildingTypesSet
				.equals(other.buildsTroopBuildingTypesSet))
			return false;

		if (buildingType == null) {
			if (other.buildingType != null)
				return false;
		} else if (!buildingType.equals(other.buildingType))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((buildsTroopBuildingTypesSet == null)
						? 0
						: buildsTroopBuildingTypesSet.hashCode());

		result = prime * result
				+ ((buildingType == null) ? 0 : buildingType.hashCode());

		return result;
	}

}
