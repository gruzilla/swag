import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;

	private Integer age;

	private Set<Address> addressSet;

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@OneToMany(mappedBy = "person")
	public Set<Address> getAddressSet() {
		return addressSet;
	}

	public void setAddress(Set<Address> varAddress) {
		addressSet = varAddress;
	}

}
