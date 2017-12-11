package mp.videorental;
import java.time.LocalDate;

public abstract class Person {
	
	private String name;
	private String surname;
	private LocalDate birthday;
	
	public Person(String name, String surname, LocalDate birthday) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}

}
