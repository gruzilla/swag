package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_squad")
public class Squad extends MapObject implements Serializable {

	private static final long serialVersionUID = -8655311362856175857L;

	private Double movementspeed;

	private Set<Troop> partOfTroopSet;

	private SquadMovement movesSquadMovement;

	private User belongsToUser;

	private MapSquare isOnMapSquare;

	@Column(nullable = false)
	public Double getMovementspeed() {
		return movementspeed;
	}

	public void setMovementspeed(Double movementspeed) {
		this.movementspeed = movementspeed;
	}

	@OneToMany(mappedBy = "partOfSquad")
	public Set<Troop> getPartOfTroopSet() {
		return partOfTroopSet;
	}

	public void setPartOfTroopSet(Set<Troop> varTroop) {
		partOfTroopSet = varTroop;
	}

	@OneToOne(optional = true)
	public SquadMovement getMovesSquadMovement() {
		return movesSquadMovement;
	}

	public void setMovesSquadMovement(SquadMovement varSquadMovement) {
		movesSquadMovement = varSquadMovement;
	}

	@ManyToOne(optional = true)
	public User getBelongsToUser() {
		return belongsToUser;
	}

	public void setBelongsToUser(User varUser) {
		belongsToUser = varUser;
	}

	@ManyToOne(optional = true)
	public MapSquare getIsOnMapSquare() {
		return isOnMapSquare;
	}

	public void setIsOnMapSquare(MapSquare varMapSquare) {
		isOnMapSquare = varMapSquare;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Squad other = (Squad) obj;

		if (!partOfTroopSet.equals(other.partOfTroopSet))
			return false;

		if (!movesSquadMovement.equals(other.movesSquadMovement))
			return false;

		if (!belongsToUser.equals(other.belongsToUser))
			return false;

		if (!isOnMapSquare.equals(other.isOnMapSquare))
			return false;

		if (movementspeed == null) {
			if (other.movementspeed != null)
				return false;
		} else if (!movementspeed.equals(other.movementspeed))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((partOfTroopSet == null) ? 0 : partOfTroopSet.hashCode());

		result = prime
				* result
				+ ((movesSquadMovement == null) ? 0 : movesSquadMovement
						.hashCode());

		result = prime * result
				+ ((belongsToUser == null) ? 0 : belongsToUser.hashCode());

		result = prime * result
				+ ((isOnMapSquare == null) ? 0 : isOnMapSquare.hashCode());

		result = prime * result
				+ ((movementspeed == null) ? 0 : movementspeed.hashCode());

		return result;
	}

}
