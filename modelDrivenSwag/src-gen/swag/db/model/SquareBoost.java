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

	private Resources boostsResources;

	@Column(nullable = false)
	public Double getLevel() {
		return level;
	}

	public void setLevel(Double level) {
		this.level = level;
	}
	
	@ManyToOne(optional = true)
	public Resources getBoostsResources() {
		return boostsResources;
	}

	public void setBoostsResources(Resources varResources) {
		boostsResources = varResources;
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
		SquareBoost other = (SquareBoost) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
