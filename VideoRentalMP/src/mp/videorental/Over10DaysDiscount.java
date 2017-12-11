package mp.videorental;
import mp.videorental.exception.AddToLeafCompositeException;

public class Over10DaysDiscount implements RentPriceStrategy {
	
	private static final Double DISCOUNT_PERCENT = 0.10;
	
	@Override
	public void add(RentPriceStrategy r) throws AddToLeafCompositeException {
		throw new AddToLeafCompositeException("Cannot add a RentPriceStrategy to a Over10DaysDiscount.");
	}
	
	@Override
	public Double getPrice(Double initialPrice) {
		return initialPrice - initialPrice * DISCOUNT_PERCENT;
	}
	
}
