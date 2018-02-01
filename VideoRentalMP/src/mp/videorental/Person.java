package mp.videorental;
import java.time.LocalDate;

public abstract class Person implements Storable {
	
	private static final long serialVersionUID = -1814749676914910477L;
	private String socialSecurityNumber;
	private String name;
	private String surname;
	private LocalDate birthday;
	
	public Person(String socialSecurityNumber,String name, String surname, LocalDate birthday) {
		this.socialSecurityNumber = socialSecurityNumber;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}
	
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((socialSecurityNumber == null) ? 0 : socialSecurityNumber.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			Person other = (Person) obj;
			if(socialSecurityNumber.equals(other.socialSecurityNumber)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[socialSecurityNumber=" + socialSecurityNumber + ", name=" + name + ", surname=" + surname
				+ ", birthday=" + birthday.toString() + "]";
	}
	
	
}
