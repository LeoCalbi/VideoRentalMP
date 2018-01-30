package mp.videorental;

public class BD extends MovieBox {
	
	private static final long serialVersionUID = -8231653493889623682L;
	private static final Double DAILY_PRICE_SURCHARGE = 1.50;
	
	public BD(Movie movie, Double dailyPrice) {
		super(movie, dailyPrice);
	}
	
	@Override
	public Double getDailyPrice() {
		return super.getDailyPrice() + DAILY_PRICE_SURCHARGE;
	}

}
