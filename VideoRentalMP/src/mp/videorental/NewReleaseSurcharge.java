package mp.videorental;

import mp.videorental.exception.AddToLeafCompositeException;

public class NewReleaseSurcharge implements RentPriceStrategy {
	
	private static final Double SURCHARGE_PERCENT = 0.05;
	
	@Override
	public void add(RentPriceStrategy r) throws AddToLeafCompositeException {
		throw new AddToLeafCompositeException("Cannot add a RentPriceStrategy to a NewMovieSurcharge.");
	}
	
	@Override
	public Double getPrice(Double initialPrice) {
		return initialPrice + initialPrice * SURCHARGE_PERCENT;
	}

}
