package mp.videorental;

public abstract class MovieBox implements Rentable {
	
	private Movie movie;
	private Integer serialNumber;
	private Double dailyPrice;
	
	public MovieBox(Movie movie, Integer serialNumber, Double dailyPrice) {
		this.movie = movie;
		this.serialNumber = serialNumber;
		this.dailyPrice = dailyPrice;
	}

	public Double getDailyPrice() {
		return dailyPrice;
	}
	
}
