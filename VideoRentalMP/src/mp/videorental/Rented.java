package mp.videorental;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.NotRentedException;
import mp.videorental.exception.AbsentRentException;

public class Rented implements Serializable {

	private static final long serialVersionUID = -5236882462605277312L;
	private List<Rent> rented;
	public static final Integer MAX_RENTED = 3;
	
	public Rented() {
		rented = new LinkedList<Rent>();
	}
	
	public Iterator<Rent> getIterator() {
		return rented.iterator();
	}
	
	public void add(Rent r) {
		rented.add(r);
	}
	
	public boolean restitution(Rentable r) throws AbsentRentException, EmptyRentListException, NotRentedException {
		boolean found = false, expired = false;
		if(rented.size() <= 0) throw new EmptyRentListException();
		Iterator<Rent> it = getIterator();
		while(it.hasNext()) {
			Rent current = it.next();
			if(current.compareRentable(r)) {
				found = true;
				expired = isRentExpired(current);
				current.restitution();
				it.remove();
				break;
			}
		}
		if(!found) throw new AbsentRentException();
		return expired;
	}
	
	private boolean isRentExpired(Rent r) {
		LocalDate currentTime = LocalDate.now();
		LocalDate rentDate = r.getRentDate();
		long variance = ChronoUnit.DAYS.between(rentDate, currentTime);
		if(variance > r.getDays()) return true;
		return false;
	}
	
	public Integer size() {
		return rented.size();
	}
	
	@Override
	public String toString() {
		return rented.toString();
	}
	
}
