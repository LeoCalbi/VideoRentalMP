package mp.videorental;
import java.time.LocalDate;

public abstract class Person implements Storable{
	
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
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			Person other = (Person) obj;
			if(socialSecurityNumber.equals(other.socialSecurityNumber)) return true;
		}
		return false;
	}
	
	@Override
	public void add(Administrator admin) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(Administrator admin) {
		// TODO Auto-generated method stub
		
	}
}
