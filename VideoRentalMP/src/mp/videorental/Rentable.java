package mp.videorental;
import java.time.LocalDate;
import mp.videorental.exception.AlreadyRentedException;
import mp.videorental.exception.NotRentedException;

public interface Rentable {
	
	public Rent rent(Integer days) throws AlreadyRentedException;
	public void restitution() throws NotRentedException;
	public Double getDailyPrice();
	public LocalDate getReleaseDate();
	
}
