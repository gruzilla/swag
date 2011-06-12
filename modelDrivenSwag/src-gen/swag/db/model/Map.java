package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_map")
public class Map implements Serializable {

	public Map() {
	}

	public Map(Integer id, String name, Integer maxUsers) {
		super();
		this.id = id;
		this.name = name;
		this.maxUsers = maxUsers;
	}

	private static final long serialVersionUID = -5440346325430555463L;

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

	private Integer maxUsers;

	private Set<MapSquare> partOfMapSquareSet;

	private Set<User> playsOnUserSet;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

	@OneToMany(mappedBy = "partOfMap")
	public Set<MapSquare> getPartOfMapSquareSet() {
		return partOfMapSquareSet;
	}

	public void setPartOfMapSquareSet(Set<MapSquare> varMapSquare) {
		partOfMapSquareSet = varMapSquare;
	}

	@ManyToMany(mappedBy = "playsOnMapSet")
	public Set<User> getPlaysOnUserSet() {

		return playsOnUserSet;
	}

	public void setPlaysOnUserSet(Set<User> varUser) {

		playsOnUserSet = varUser;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Map other = (Map) obj;

		if (!partOfMapSquareSet.equals(other.partOfMapSquareSet))
			return false;

		if (!playsOnUserSet.equals(other.playsOnUserSet))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (maxUsers == null) {
			if (other.maxUsers != null)
				return false;
		} else if (!maxUsers.equals(other.maxUsers))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((partOfMapSquareSet == null) ? 0 : partOfMapSquareSet
						.hashCode());

		result = prime * result
				+ ((playsOnUserSet == null) ? 0 : playsOnUserSet.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());

		result = prime * result
				+ ((maxUsers == null) ? 0 : maxUsers.hashCode());

		return result;
	}

}
