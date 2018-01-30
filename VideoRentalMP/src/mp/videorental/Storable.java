package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public interface Storable extends Serializable {
	
	void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, IOException, ClassNotFoundException;
	void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, IOException, ClassNotFoundException;
	
}
