package swag.db.model;

import javax.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity(name = "swa_resources")
public class Resources implements Serializable {
	private static final long serialVersionUID = 7009138202323899272L;
	
	public Resources() {
		super();
	}

	public Resources(Integer id, String name, Boolean defaultResource) {
		super();
		this.id = id;
		this.name = name;
		this.defaultResource = defaultResource;
	}

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

	private Boolean defaultResource;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Boolean getDefaultResource() {
		return defaultResource;
	}

	public void setDefaultResource(Boolean defaultResource) {
		this.defaultResource = defaultResource;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Resources other = (Resources) obj;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (defaultResource == null) {
			if (other.defaultResource != null)
				return false;
		} else if (!defaultResource.equals(other.defaultResource))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());

		result = prime * result
				+ ((defaultResource == null) ? 0 : defaultResource.hashCode());

		return result;
	}

}
