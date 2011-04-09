import javax.persistence.*;
@Entity
public class Address {

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String street;
	private String zip;
	private String city;

	@Column
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private Person person;
	@OneToOne(mappedBy = "person")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person varPerson) {
		person = varPerson;
	}

}
