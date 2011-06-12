package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_resourceBuilding")
public class ResourceBuilding extends Building implements Serializable {

	private static final long serialVersionUID = 3238362407460146030L;

	private Resources producesResources;

	public ResourceBuilding() {
		super();
	}

	public ResourceBuilding(Integer id, Integer level, Integer currentHealth,
			Integer maxHealth, Date creationTime, BaseSquare builtOnBaseSquare,
			Resources producesResources) {
		super(id, level, currentHealth, maxHealth, creationTime,
				builtOnBaseSquare);
		this.producesResources = producesResources;
	}

	@ManyToOne(optional = true)
	public Resources getProducesResources() {
		return producesResources;
	}

	public void setProducesResources(Resources varResources) {
		producesResources = varResources;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		ResourceBuilding other = (ResourceBuilding) obj;

		if (!producesResources.equals(other.producesResources))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime
				* result
				+ ((producesResources == null) ? 0 : producesResources
						.hashCode());

		return result;
	}

}
