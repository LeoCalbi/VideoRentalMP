package mp.videorental;

public class BD extends MovieBox {

	public BD(Movie movie, Integer serialNumber, Double dailyPrice) {
		super(movie, dailyPrice);
	}
	
	@Override
	public Double getDailyPrice() {
		return super.getDailyPrice() + 1.50;
	}

}
