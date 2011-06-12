package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_troop")
public class Troop implements Serializable {

	private static final long serialVersionUID = 437044817028777422L;

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer level;

	private Date creationTime;

	private Squad partOfSquad;

	private TroopTypes isOfTroopTypes;

	@Column(nullable = false)
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@ManyToOne(optional = true)
	public Squad getPartOfSquad() {
		return partOfSquad;
	}

	public void setPartOfSquad(Squad varSquad) {
		partOfSquad = varSquad;
	}

	@ManyToOne(optional = true)
	public TroopTypes getIsOfTroopTypes() {
		return isOfTroopTypes;
	}

	public void setIsOfTroopTypes(TroopTypes varTroopTypes) {
		isOfTroopTypes = varTroopTypes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Troop other = (Troop) obj;

		if (!partOfSquad.equals(other.partOfSquad))
			return false;

		if (!isOfTroopTypes.equals(other.isOfTroopTypes))
			return false;

		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;

		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((partOfSquad == null) ? 0 : partOfSquad.hashCode());

		result = prime * result
				+ ((isOfTroopTypes == null) ? 0 : isOfTroopTypes.hashCode());

		result = prime * result + ((level == null) ? 0 : level.hashCode());

		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());

		return result;
	}

}
