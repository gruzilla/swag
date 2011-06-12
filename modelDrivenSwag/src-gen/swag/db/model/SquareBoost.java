package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_squareBoost")
public class SquareBoost implements Serializable {

	private static final long serialVersionUID = 7621685625143179094L;

	private Integer id;
	
	public SquareBoost() {
		super();
	}

	public SquareBoost(Integer id, Double level,
			BaseSquare boostedByBaseSquare, MapSquare boostedByMapSquare,
			Resources boostsResources) {
		super();
		this.id = id;
		this.level = level;
		this.boostedByBaseSquare = boostedByBaseSquare;
		this.boostedByMapSquare = boostedByMapSquare;
		this.boostsResources = boostsResources;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Double level;

	private BaseSquare boostedByBaseSquare;

	private MapSquare boostedByMapSquare;

	private Resources boostsResources;

	@Column(nullable = false)
	public Double getLevel() {
		return level;
	}

	public void setLevel(Double level) {
		this.level = level;
	}

	@OneToOne(mappedBy = "boostedBySquareBoost")
	public BaseSquare getBoostedByBaseSquare() {
		return boostedByBaseSquare;
	}

	public void setBoostedByBaseSquare(BaseSquare varBaseSquare) {
		boostedByBaseSquare = varBaseSquare;
	}

	@OneToOne(mappedBy = "boostedBySquareBoost")
	public MapSquare getBoostedByMapSquare() {
		return boostedByMapSquare;
	}

	public void setBoostedByMapSquare(MapSquare varMapSquare) {
		boostedByMapSquare = varMapSquare;
	}

	@ManyToOne(optional = true)
	public Resources getBoostsResources() {
		return boostsResources;
	}

	public void setBoostsResources(Resources varResources) {
		boostsResources = varResources;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		SquareBoost other = (SquareBoost) obj;

		if (!boostedByBaseSquare.equals(other.boostedByBaseSquare))
			return false;

		if (!boostedByMapSquare.equals(other.boostedByMapSquare))
			return false;

		if (!boostsResources.equals(other.boostsResources))
			return false;

		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((boostedByBaseSquare == null) ? 0 : boostedByBaseSquare
						.hashCode());

		result = prime
				* result
				+ ((boostedByMapSquare == null) ? 0 : boostedByMapSquare
						.hashCode());

		result = prime * result
				+ ((boostsResources == null) ? 0 : boostsResources.hashCode());

		result = prime * result + ((level == null) ? 0 : level.hashCode());

		return result;
	}

}
