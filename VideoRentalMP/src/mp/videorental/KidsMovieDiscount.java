package mp.videorental;
import mp.videorental.exception.AddToLeafCompositeException;

public class KidsMovieDiscount implements RentPriceStrategy {
	
	private static final Double DISCOUNT_PERCENT = 0.03;
	
	@Override
	public void add(RentPriceStrategy r) throws AddToLeafCompositeException {
		throw new AddToLeafCompositeException("Cannot add a RentPriceStrategy to a KidsMovieDiscount.");
	}
	
	@Override
	public Double getPrice(Double initialPrice) {
		return initialPrice - initialPrice * DISCOUNT_PERCENT;
	}

}
