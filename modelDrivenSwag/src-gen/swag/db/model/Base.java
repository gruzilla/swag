package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_base")
public class Base extends MapObject implements Serializable {

	public Base() {
		super();
	}

	public Base(Integer id, String name, MapSquare isOnMapSquare,
			Date creationTime, Boolean isStarterBase,
			Set<BaseSquare> isOnBaseSquareSet, User belongsToUser) {
		super(id, name, isOnMapSquare);
		this.creationTime = creationTime;
		this.isStarterBase = isStarterBase;
		this.isOnBaseSquareSet = isOnBaseSquareSet;
		this.belongsToUser = belongsToUser;
	}

	private static final long serialVersionUID = -6974909866419288843L;

	private Date creationTime;

	private Boolean isStarterBase;

	private Set<BaseSquare> isOnBaseSquareSet;

	private User belongsToUser;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Column(nullable = false)
	public Boolean getIsStarterBase() {
		return isStarterBase;
	}

	public void setIsStarterBase(Boolean isStarterBase) {
		this.isStarterBase = isStarterBase;
	}

	@OneToMany(mappedBy = "isOnBase")
	public Set<BaseSquare> getIsOnBaseSquareSet() {
		return isOnBaseSquareSet;
	}

	public void setIsOnBaseSquareSet(Set<BaseSquare> varBaseSquare) {
		isOnBaseSquareSet = varBaseSquare;
	}

	@ManyToOne(optional = true)
	public User getBelongsToUser() {
		return belongsToUser;
	}

	public void setBelongsToUser(User varUser) {
		belongsToUser = varUser;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		if (belongsToUser == null) {
			if (other.belongsToUser != null)
				return false;
		} else if (!belongsToUser.equals(other.belongsToUser))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (isOnBaseSquareSet == null) {
			if (other.isOnBaseSquareSet != null)
				return false;
		} else if (!isOnBaseSquareSet.equals(other.isOnBaseSquareSet))
			return false;
		if (isStarterBase == null) {
			if (other.isStarterBase != null)
				return false;
		} else if (!isStarterBase.equals(other.isStarterBase))
			return false;
		return true;
	}
}
