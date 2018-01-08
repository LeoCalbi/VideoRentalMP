package mp.videorental;

import java.time.LocalDate;
import mp.videorental.exception.AlreadyRentedException;

public abstract class MovieBox implements Rentable {
	
	private Movie movie;
	private static Integer lastSerialNumber = 0;
	private Integer serialNumber;
	private Double dailyPrice;
	private Boolean isAvailable;
	private RentFactory rentFactory;
	
	public MovieBox(Movie movie, Double dailyPrice) {
		this.movie = movie;
		this.dailyPrice = dailyPrice;
		this.serialNumber = lastSerialNumber++;
		this.isAvailable = true;
		this.rentFactory = new RentFactory();
	}

	public boolean isAvailable() {
		return isAvailable;
	}
	
	@Override
	public Double getDailyPrice() {
		return dailyPrice;
	}
	
	@Override
	public LocalDate getReleaseDate() {
		return movie.getReleaseDate();
	}
	
	@Override
	public Rent rent(Integer days) throws AlreadyRentedException {
		if(isAvailable) {
			isAvailable = false;
			return rentFactory.makeRent(this, days);
		}
		else throw new AlreadyRentedException();
	}

	@Override
	public void restitution() {
		isAvailable = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MovieBox) {
			MovieBox m = (MovieBox) obj;
			return m.serialNumber.equals(serialNumber);
		}
		return false;
	}
	
}
