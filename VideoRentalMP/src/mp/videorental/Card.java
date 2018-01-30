package mp.videorental;

import java.io.Serializable;

public interface Card extends Serializable {
	
	public Double getDiscount(Double amount);
	public void makePoints();
	
}
