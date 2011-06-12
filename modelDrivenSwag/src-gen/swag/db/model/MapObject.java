package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_mapObject")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn
public class MapObject implements Serializable {

	private static final long serialVersionUID = 1817033457265745831L;

	private Integer id;

	public MapObject(Integer id, String name, MapSquare isOnMapSquare) {
		super();
		this.id = id;
		this.name = name;
		this.isOnMapSquare = isOnMapSquare;
	}

	public MapObject() {
		super();
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

	private MapSquare isOnMapSquare;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

		MapObject other = (MapObject) obj;

		if (!isOnMapSquare.equals(other.isOnMapSquare))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((isOnMapSquare == null) ? 0 : isOnMapSquare.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());

		return result;
	}

}
