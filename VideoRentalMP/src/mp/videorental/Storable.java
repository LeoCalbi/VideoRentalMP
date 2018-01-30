package mp.videorental;

import java.io.Serializable;

public interface Storable extends Serializable {
	
	void add(Administrator admin);
	void remove(Administrator admin);
	
}
