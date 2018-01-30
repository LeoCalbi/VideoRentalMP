package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public abstract class Repository<T extends Storable> implements Serializable {
	
	private static final long serialVersionUID = -8993075071738965627L;
	private Set<T> catalogue = new HashSet<T>();
	
	public void add(T item, Administrator admin) throws InvalidAdminException, FileNotFoundException, IOException, ClassNotFoundException {
		if(!AdministratorRepository.getInstance().contains(admin)) throw new InvalidAdminException();
		if(!catalogue.add(item)) throw new StorableAlreadyPresentException();
		StorableHandler.getInstance().write();
	}
	
	public void remove(T item, Administrator admin) throws InvalidAdminException, FileNotFoundException, IOException, ClassNotFoundException {
		if(!AdministratorRepository.getInstance().contains(admin)) throw new InvalidAdminException();
		if (!catalogue.remove(item)) throw new StorableNotPresentException();
		StorableHandler.getInstance().write();
	}
	
	public boolean contains(T item) {
		return catalogue.contains(item);
	}
	
	public String toString() {
		return catalogue.toString();
	}
	
}