package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_mapSquare")
public class MapSquare implements Serializable {

	private static final long serialVersionUID = 4554818442192066843L;

	private Integer id;
	
	public MapSquare() {
	}

	public MapSquare(Integer id, Integer positionX, Integer positionY) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
	}

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
	@JoinColumn(name="BOOST_ID") 
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapSquare other = (MapSquare) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
