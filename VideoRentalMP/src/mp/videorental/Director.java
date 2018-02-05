package mp.videorental;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class Director implements Storable {
	
	private static final long serialVersionUID = 3199925935959912562L;
	private String name;
	private String surname;

	public Director(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Director) {
			Director other = (Director) obj;
			return other.name.equals(name) && other.surname.equals(surname);
		}
		return false;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		DirectorRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		DirectorRepository.getInstance().remove(this, admin);
	}

	@Override
	public String toString() {
		return "Director: Name = " + name + ", Surname = " + surname;
	}
	
	
}
