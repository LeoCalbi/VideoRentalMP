package mp.videorental;

import mp.videorental.exception.AlreadyRentedException;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.NotRentedException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public abstract class MovieBox implements Rentable, Storable {

	private static final long serialVersionUID = 3360319400998117600L;
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
	
	@Override
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	@Override
	public Double getDailyPrice() {
		return dailyPrice;
	}

	@Override
	public Rent rent(Integer days,RentPriceStrategy strategy) throws AlreadyRentedException {
		if (isAvailable) {
			isAvailable = false;
			return new ComplexRent(this, days, strategy);
		} else throw new AlreadyRentedException();
	}
	
	@Override
	public Rent rent(Integer days) throws AlreadyRentedException {
		if (isAvailable) {
			isAvailable = false;
			return new SimpleRent(this, days);
		} else throw new AlreadyRentedException();
	}

	@Override
	public void restitution() throws NotRentedException {
		if (!isAvailable) isAvailable = true;
		else throw new NotRentedException();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MovieBox) {
			MovieBox m = (MovieBox) obj;
			return m.serialNumber.equals(serialNumber);
		}
		return false;
	}
	
	public boolean compareMovie(Movie m) {
		return movie.equals(m);
	}
	
	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		MovieBoxRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		MovieBoxRepository.getInstance().remove(this, admin);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " -> Serial Number: " + serialNumber + ", Movie Title: " + movie.getTitle() + ", Daily Price: " + getDailyPrice() + "\n";
	}
	
}
