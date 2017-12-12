package mp.videorental;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import mp.videorental.exception.AddToLeafCompositeException;

public class RentFactory {
	
	public Rent makeRent(Rentable item, Integer days) {
		RentPriceStrategyCompound r = new RentPriceStrategyCompound();
		if(isNewRelease(item)) {
			try {
				r.add(new NewReleaseSurcharge());
			} catch (AddToLeafCompositeException e) {
				e.printStackTrace();
			}
		}
		if(isRentOver10Days(days)) {
			try {
				r.add(new Over10DaysDiscount());
			} catch (AddToLeafCompositeException e) {
				e.printStackTrace();
			}
		}
		return new Rent(item, days, r);
	}
	
	private boolean isNewRelease(Rentable item) {
		LocalDate currentTime = LocalDate.now();
		LocalDate releaseDate = item.getReleaseDate();
		long variance = ChronoUnit.MONTHS.between(releaseDate, currentTime);
		if(variance < 1) return true;
		return false;
	}
	
	private boolean isRentOver10Days(Integer days) {
		if(days >= 10) return true;
		return false;
	}
	
}
