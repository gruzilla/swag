import javax.persistence.*;
@Entity
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

	private Address address;

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

	@OneToOne
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address varAddress) {
		address = varAddress;
	}

}
