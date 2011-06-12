package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_baseSquare")
public class BaseSquare implements Serializable {

	
	public BaseSquare() {
		super();
	}

	public BaseSquare(Integer id, Integer positionX, Integer positionY,
			SquareBoost boostedBySquareBoost, Building builtOnBuilding,
			Base isOnBase) {
		super();
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.boostedBySquareBoost = boostedBySquareBoost;
		this.builtOnBuilding = builtOnBuilding;
		this.isOnBase = isOnBase;
	}


	private static final long serialVersionUID = 6189193686682220456L;

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

	private SquareBoost boostedBySquareBoost;

	private Building builtOnBuilding;

	private Base isOnBase;

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

	@OneToOne(optional = true)
	public SquareBoost getBoostedBySquareBoost() {
		return boostedBySquareBoost;
	}

	public void setBoostedBySquareBoost(SquareBoost varSquareBoost) {
		boostedBySquareBoost = varSquareBoost;
	}

	@OneToOne(mappedBy = "builtOnBaseSquare")
	public Building getBuiltOnBuilding() {
		return builtOnBuilding;
	}

	public void setBuiltOnBuilding(Building varBuilding) {
		builtOnBuilding = varBuilding;
	}

	@ManyToOne(optional = true)
	public Base getIsOnBase() {
		return isOnBase;
	}

	public void setIsOnBase(Base varBase) {
		isOnBase = varBase;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		BaseSquare other = (BaseSquare) obj;

		if (!boostedBySquareBoost.equals(other.boostedBySquareBoost))
			return false;

		if (!builtOnBuilding.equals(other.builtOnBuilding))
			return false;

		if (!isOnBase.equals(other.isOnBase))
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
		return super.hashCode();
	}
}
