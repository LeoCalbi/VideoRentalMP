package mp.videorental;

import java.io.Serializable;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public interface Storable extends Serializable {
	
	void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException;
	void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException;
	
}
