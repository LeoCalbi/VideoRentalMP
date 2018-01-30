package mp.videorental;
import java.io.Serializable;

import mp.videorental.exception.AddToLeafCompositeException;

public interface RentPriceStrategy extends Serializable {
	
	public void add(RentPriceStrategy r) throws AddToLeafCompositeException;
	public Double getPrice(Double initialPrice);
	
}
