package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_mapSquare")
public class MapSquare implements Serializable {

	public MapSquare() {
	}

	public MapSquare(Integer id, Integer positionX, Integer positionY) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	private static final long serialVersionUID = 4554818442192066843L;

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer positionX;

	private Integer positionY;

	private Set<Squad> isOnSquadSet;

	private SquareBoost boostedBySquareBoost;

	private Set<MapObject> isOnMapObjectSet;

	private SquadMovement fromSquadMovement;

	private SquadMovement toSquadMovement;

	private Map partOfMap;

	@Column(nullable = false)
	public Integer getPositionX() {
		return positionX;
	}

	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}

	@Column(nullable = false)
	public Integer getPositionY() {
		return positionY;
	}

	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}

	@OneToMany(mappedBy = "isOnMapSquare")
	public Set<Squad> getIsOnSquadSet() {
		return isOnSquadSet;
	}

	public void setIsOnSquadSet(Set<Squad> varSquad) {
		isOnSquadSet = varSquad;
	}

	@OneToOne(optional = true)
	public SquareBoost getBoostedBySquareBoost() {
		return boostedBySquareBoost;
	}

	public void setBoostedBySquareBoost(SquareBoost varSquareBoost) {
		boostedBySquareBoost = varSquareBoost;
	}

	@OneToMany(mappedBy = "isOnMapSquare")
	public Set<MapObject> getIsOnMapObjectSet() {
		return isOnMapObjectSet;
	}

	public void setIsOnMapObjectSet(Set<MapObject> varMapObject) {
		isOnMapObjectSet = varMapObject;
	}

	@OneToOne(mappedBy = "fromMapSquare")
	public SquadMovement getFromSquadMovement() {
		return fromSquadMovement;
	}

	public void setFromSquadMovement(SquadMovement varSquadMovement) {
		fromSquadMovement = varSquadMovement;
	}

	@OneToOne(mappedBy = "toMapSquare")
	public SquadMovement getToSquadMovement() {
		return toSquadMovement;
	}

	public void setToSquadMovement(SquadMovement varSquadMovement) {
		toSquadMovement = varSquadMovement;
	}

	@ManyToOne(optional = true)
	public Map getPartOfMap() {
		return partOfMap;
	}

	public void setPartOfMap(Map varMap) {
		partOfMap = varMap;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		MapSquare other = (MapSquare) obj;

		if (!isOnSquadSet.equals(other.isOnSquadSet))
			return false;

		if (!boostedBySquareBoost.equals(other.boostedBySquareBoost))
			return false;

		if (!isOnMapObjectSet.equals(other.isOnMapObjectSet))
			return false;

		if (!fromSquadMovement.equals(other.fromSquadMovement))
			return false;

		if (!toSquadMovement.equals(other.toSquadMovement))
			return false;

		if (!partOfMap.equals(other.partOfMap))
			return false;

		if (positionX == null) {
			if (other.positionX != null)
				return false;
		} else if (!positionX.equals(other.positionX))
			return false;

		if (positionY == null) {
			if (other.positionY != null)
				return false;
		} else if (!positionY.equals(other.positionY))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((isOnSquadSet == null) ? 0 : isOnSquadSet.hashCode());

		result = prime
				* result
				+ ((boostedBySquareBoost == null) ? 0 : boostedBySquareBoost
						.hashCode());

		result = prime
				* result
				+ ((isOnMapObjectSet == null) ? 0 : isOnMapObjectSet.hashCode());

		result = prime
				* result
				+ ((fromSquadMovement == null) ? 0 : fromSquadMovement
						.hashCode());

		result = prime * result
				+ ((toSquadMovement == null) ? 0 : toSquadMovement.hashCode());

		result = prime * result
				+ ((partOfMap == null) ? 0 : partOfMap.hashCode());

		result = prime * result
				+ ((positionX == null) ? 0 : positionX.hashCode());

		result = prime * result
				+ ((positionY == null) ? 0 : positionY.hashCode());

		return result;
	}

}
