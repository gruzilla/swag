package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_building")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn
public class Building implements Serializable {

	private static final long serialVersionUID = 5998470248547705792L;

	private Integer id;
	
	public Building() {
		super();
	}

	public Building(Integer id, Integer level, Integer currentHealth,
			Integer maxHealth, Date creationTime, BaseSquare builtOnBaseSquare) {
		super();
		this.id = id;
		this.level = level;
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.creationTime = creationTime;
		this.builtOnBaseSquare = builtOnBaseSquare;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer level;

	private Integer currentHealth;

	private Integer maxHealth;

	private Date creationTime;

	private BaseSquare builtOnBaseSquare;

	@Column(nullable = false)
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(nullable = false)
	public Integer getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(Integer currentHealth) {
		this.currentHealth = currentHealth;
	}

	@Column(nullable = false)
	public Integer getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(Integer maxHealth) {
		this.maxHealth = maxHealth;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@OneToOne(optional = true)
	public BaseSquare getBuiltOnBaseSquare() {
		return builtOnBaseSquare;
	}

	public void setBuiltOnBaseSquare(BaseSquare varBaseSquare) {
		builtOnBaseSquare = varBaseSquare;
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
		Building other = (Building) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
