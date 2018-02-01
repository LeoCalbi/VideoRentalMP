package mp.videorental;

import mp.videorental.exception.AlreadyRentedException;
import mp.videorental.exception.NotRentedException;

public interface Rentable {
	
	public Rent rent(Integer days) throws AlreadyRentedException;
	public Rent rent(Integer days,RentPriceStrategy strategy) throws AlreadyRentedException;
	public void restitution() throws NotRentedException;
	public Double getDailyPrice();
	public Integer getSerialNumber();
	
}
