package mp.videorental;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class Repository<T extends Storable> implements Serializable {
	
	private List<T> catalogue = new LinkedList<T>();
	
	public void add(T item, Administrator admin) {
		catalogue.add(item);
	}
	
	public void remove(T item, Administrator admin) {
		catalogue.remove(item);
	}
	
}