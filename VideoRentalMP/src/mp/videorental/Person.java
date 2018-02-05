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
	public String toString() {
		return getClass().getSimpleName() + ": SocialSecurityNumber = " + socialSecurityNumber + ", Name = " + name + ", Surname = " + surname
				+ ", Birthday = " + birthday.toString();
	}
	
	
}
