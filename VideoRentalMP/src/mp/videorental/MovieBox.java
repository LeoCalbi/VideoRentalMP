package mp.videorental;

import mp.videorental.exception.AlreadyRentedException;

public abstract class MovieBox implements Rentable {
	
	private Movie movie;
	private static Integer lastSerialNumber = 0;
	private Integer serialNumber;
	private Double dailyPrice;
	private Boolean isAvailable;
	
	public MovieBox(Movie movie, Double dailyPrice) {
		this.movie = movie;
		this.dailyPrice = dailyPrice;
		this.serialNumber = lastSerialNumber++;
		this.isAvailable = true;
	}

	public Double getDailyPrice() {
		return dailyPrice;
	}
	
	@Override
	public Rent rent(Integer days) throws AlreadyRentedException {
		if(isAvailable) {
			isAvailable = false;
			
		}
		else throw new AlreadyRentedException();
	}

	@Override
	public void restitution(Rent r) {
		isAvailable = true;
	}
	
}
