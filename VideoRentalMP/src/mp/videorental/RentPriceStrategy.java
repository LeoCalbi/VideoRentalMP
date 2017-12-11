package mp.videorental;
import mp.videorental.exception.AddToLeafCompositeException;

public interface RentPriceStrategy {
	
	public void add(RentPriceStrategy r) throws AddToLeafCompositeException;
	public Double getPrice(Double initialPrice);
	
}
