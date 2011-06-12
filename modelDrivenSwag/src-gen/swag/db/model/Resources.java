package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_resources")
public class Resources implements Serializable {

	
	
	private static final long serialVersionUID = 7009138202323899272L;
	
	public Resources() {
		super();
	}

	public Resources(Integer id, String name, Boolean defaultResource) {
		super();
		this.id = id;
		this.name = name;
		this.defaultResource = defaultResource;
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

	private String name;

	private Boolean defaultResource;

	private Set<SquareBoost> boostsSquareBoostSet;

	private Set<ResourceBuilding> producesResourceBuildingSet;

	private Set<UserResourceCount> hasUserSet;

	private Set<TroopCost> costsTroopTypesSet;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Boolean getDefaultResource() {
		return defaultResource;
	}

	public void setDefaultResource(Boolean defaultResource) {
		this.defaultResource = defaultResource;
	}

	@OneToMany(mappedBy = "boostsResources")
	public Set<SquareBoost> getBoostsSquareBoostSet() {
		return boostsSquareBoostSet;
	}

	public void setBoostsSquareBoostSet(Set<SquareBoost> varSquareBoost) {
		boostsSquareBoostSet = varSquareBoost;
	}

	@OneToMany(mappedBy = "producesResources")
	public Set<ResourceBuilding> getProducesResourceBuildingSet() {
		return producesResourceBuildingSet;
	}

	public void setProducesResourceBuildingSet(
			Set<ResourceBuilding> varResourceBuilding) {
		producesResourceBuildingSet = varResourceBuilding;
	}

	@OneToMany(mappedBy = "hasResources")
	public Set<UserResourceCount> getHasUserSet() {

		return hasUserSet;
	}

	public void setHasUserSet(Set<UserResourceCount> varUser) {

		hasUserSet = varUser;
	}

	@OneToMany(mappedBy = "costsResources")
	public Set<TroopCost> getCostsTroopTypesSet() {

		return costsTroopTypesSet;
	}

	public void setCostsTroopTypesSet(Set<TroopCost> varTroopTypes) {

		costsTroopTypesSet = varTroopTypes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Resources other = (Resources) obj;

		if (!boostsSquareBoostSet.equals(other.boostsSquareBoostSet))
			return false;

		if (!producesResourceBuildingSet
				.equals(other.producesResourceBuildingSet))
			return false;

		if (!hasUserSet.equals(other.hasUserSet))
			return false;

		if (!costsTroopTypesSet.equals(other.costsTroopTypesSet))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (defaultResource == null) {
			if (other.defaultResource != null)
				return false;
		} else if (!defaultResource.equals(other.defaultResource))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((boostsSquareBoostSet == null) ? 0 : boostsSquareBoostSet
						.hashCode());

		result = prime
				* result
				+ ((producesResourceBuildingSet == null)
						? 0
						: producesResourceBuildingSet.hashCode());

		result = prime * result
				+ ((hasUserSet == null) ? 0 : hasUserSet.hashCode());

		result = prime
				* result
				+ ((costsTroopTypesSet == null) ? 0 : costsTroopTypesSet
						.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());

		result = prime * result
				+ ((defaultResource == null) ? 0 : defaultResource.hashCode());

		return result;
	}

}
