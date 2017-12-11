package mp.videorental;
import mp.videorental.exception.AlreadyRentedException;

public interface Rentable {
	
	public Rent rent(Integer days) throws AlreadyRentedException;
	public void restitution(Rent r);
	public Double getDailyPrice();
	
}
