package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_squadMovement")
public class SquadMovement implements Serializable {

	private static final long serialVersionUID = -1926173816650849803L;

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Date startTime;

	private MapSquare fromMapSquare;

	private MapSquare toMapSquare;

	private Squad movesSquad;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@OneToOne(optional = true)
	public MapSquare getFromMapSquare() {
		return fromMapSquare;
	}

	public void setFromMapSquare(MapSquare varMapSquare) {
		fromMapSquare = varMapSquare;
	}

	@OneToOne(optional = true)
	public MapSquare getToMapSquare() {
		return toMapSquare;
	}

	public void setToMapSquare(MapSquare varMapSquare) {
		toMapSquare = varMapSquare;
	}

	@OneToOne(mappedBy = "movesSquadMovement")
	public Squad getMovesSquad() {
		return movesSquad;
	}

	public void setMovesSquad(Squad varSquad) {
		movesSquad = varSquad;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		SquadMovement other = (SquadMovement) obj;

		if (!fromMapSquare.equals(other.fromMapSquare))
			return false;

		if (!toMapSquare.equals(other.toMapSquare))
			return false;

		if (!movesSquad.equals(other.movesSquad))
			return false;

		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((fromMapSquare == null) ? 0 : fromMapSquare.hashCode());

		result = prime * result
				+ ((toMapSquare == null) ? 0 : toMapSquare.hashCode());

		result = prime * result
				+ ((movesSquad == null) ? 0 : movesSquad.hashCode());

		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());

		return result;
	}

}
