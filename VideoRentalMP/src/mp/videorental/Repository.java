package mp.videorental;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public abstract class Repository<T extends Storable> implements Serializable{
	
	private static final long serialVersionUID = -8993075071738965627L;
	private static final Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private Set<T> catalogue;
	
	public Repository() {
		this.catalogue = new HashSet<T>();
	}
	
	public void add(T item, Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		if(!AdministratorRepository.getInstance().contains(admin) && !admin.equals(defaultAdmin)) throw new InvalidAdminException();
		if(!catalogue.add(item)) throw new StorableAlreadyPresentException();
		write();
	}
	
	public void remove(T item, Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		if(!AdministratorRepository.getInstance().contains(admin)) throw new InvalidAdminException();
		if (!catalogue.remove(item)) throw new StorableNotPresentException();
		write();
	}
	
	public boolean contains(T item) {
		return catalogue.contains(item);
	}
	
	public String toString() {
		return catalogue.toString();
	}
	
	public void removeIf(Predicate<? super T> filter) {
		catalogue.removeIf(filter);
		write();
	}
	
	public Stream<T> stream() {
		return catalogue.stream();
	}
	
	public Integer getSize() {
		return catalogue.size();
	}
	
	public abstract void write();
	
}