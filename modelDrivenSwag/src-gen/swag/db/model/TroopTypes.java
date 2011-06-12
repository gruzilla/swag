package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_troopTypes")
public class TroopTypes implements Serializable {

	private static final long serialVersionUID = 3538718501787568785L;

	private Integer id;
	
	public TroopTypes() {
		super();
	}

	public TroopTypes(Integer id, String name, Double baseMovementSpeed,
			Double baseAttackStrength, Integer baseMaxHitpoints,
			Integer baseBuildingTime) {
		super();
		this.id = id;
		this.name = name;
		this.baseMovementSpeed = baseMovementSpeed;
		this.baseAttackStrength = baseAttackStrength;
		this.baseMaxHitpoints = baseMaxHitpoints;
		this.baseBuildingTime = baseBuildingTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;

	private Double baseMovementSpeed;

	private Double baseAttackStrength;

	private Integer baseMaxHitpoints;

	private Integer baseBuildingTime;

	private Set<Troop> isOfTroopSet;

	private Set<TroopBuildingTypes> builtByTroopBuildingTypesSet;

	private Set<TroopCost> costsResourcesSet;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Double getBaseMovementSpeed() {
		return baseMovementSpeed;
	}

	public void setBaseMovementSpeed(Double baseMovementSpeed) {
		this.baseMovementSpeed = baseMovementSpeed;
	}

	@Column(nullable = false)
	public Double getBaseAttackStrength() {
		return baseAttackStrength;
	}

	public void setBaseAttackStrength(Double baseAttackStrength) {
		this.baseAttackStrength = baseAttackStrength;
	}

	@Column(nullable = false)
	public Integer getBaseMaxHitpoints() {
		return baseMaxHitpoints;
	}

	public void setBaseMaxHitpoints(Integer baseMaxHitpoints) {
		this.baseMaxHitpoints = baseMaxHitpoints;
	}

	@Column(nullable = false)
	public Integer getBaseBuildingTime() {
		return baseBuildingTime;
	}

	public void setBaseBuildingTime(Integer baseBuildingTime) {
		this.baseBuildingTime = baseBuildingTime;
	}

	@OneToMany(mappedBy = "isOfTroopTypes")
	public Set<Troop> getIsOfTroopSet() {
		return isOfTroopSet;
	}

	public void setIsOfTroopSet(Set<Troop> varTroop) {
		isOfTroopSet = varTroop;
	}

	@OneToMany(mappedBy = "builtByTroopTypes")
	public Set<TroopBuildingTypes> getBuiltByTroopBuildingTypesSet() {
		return builtByTroopBuildingTypesSet;
	}

	public void setBuiltByTroopBuildingTypesSet(
			Set<TroopBuildingTypes> varTroopBuildingTypes) {
		builtByTroopBuildingTypesSet = varTroopBuildingTypes;
	}

	@OneToMany(mappedBy = "costsTroopTypes")
	public Set<TroopCost> getCostsResourcesSet() {

		return costsResourcesSet;
	}

	public void setCostsResourcesSet(Set<TroopCost> varResources) {

		costsResourcesSet = varResources;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		TroopTypes other = (TroopTypes) obj;

		if (!isOfTroopSet.equals(other.isOfTroopSet))
			return false;

		if (!builtByTroopBuildingTypesSet
				.equals(other.builtByTroopBuildingTypesSet))
			return false;

		if (!costsResourcesSet.equals(other.costsResourcesSet))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (baseMovementSpeed == null) {
			if (other.baseMovementSpeed != null)
				return false;
		} else if (!baseMovementSpeed.equals(other.baseMovementSpeed))
			return false;

		if (baseAttackStrength == null) {
			if (other.baseAttackStrength != null)
				return false;
		} else if (!baseAttackStrength.equals(other.baseAttackStrength))
			return false;

		if (baseMaxHitpoints == null) {
			if (other.baseMaxHitpoints != null)
				return false;
		} else if (!baseMaxHitpoints.equals(other.baseMaxHitpoints))
			return false;

		if (baseBuildingTime == null) {
			if (other.baseBuildingTime != null)
				return false;
		} else if (!baseBuildingTime.equals(other.baseBuildingTime))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((isOfTroopSet == null) ? 0 : isOfTroopSet.hashCode());

		result = prime
				* result
				+ ((builtByTroopBuildingTypesSet == null)
						? 0
						: builtByTroopBuildingTypesSet.hashCode());

		result = prime
				* result
				+ ((costsResourcesSet == null) ? 0 : costsResourcesSet
						.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());

		result = prime
				* result
				+ ((baseMovementSpeed == null) ? 0 : baseMovementSpeed
						.hashCode());

		result = prime
				* result
				+ ((baseAttackStrength == null) ? 0 : baseAttackStrength
						.hashCode());

		result = prime
				* result
				+ ((baseMaxHitpoints == null) ? 0 : baseMaxHitpoints.hashCode());

		result = prime
				* result
				+ ((baseBuildingTime == null) ? 0 : baseBuildingTime.hashCode());

		return result;
	}

}
