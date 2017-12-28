package mp.videorental;
import java.time.LocalDate;
import mp.videorental.exception.AlreadyRentedException;

public interface Rentable {
	
	public Rent rent(Integer days) throws AlreadyRentedException;
	public void restitution();
	public Double getDailyPrice();
	public LocalDate getReleaseDate();
	
}
