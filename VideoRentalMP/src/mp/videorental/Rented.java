package mp.videorental;
import java.util.LinkedList;
import java.util.List;

import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.MaximumRentedItemsException;
import mp.videorental.exception.RentableNotInCartException;

public class Rented {
	
	private List<Rentable> rented;
	private static final Integer MAX_RENTED = 3;
	
	public Rented() {
		rented = new LinkedList<Rentable>();
	}
	
	public void add(Rentable r) throws MaximumRentedItemsException {
		if(rented.size() < MAX_RENTED) rented.add(r);
		throw new MaximumRentedItemsException();
	}
	
	public void remove(Rentable r) throws RentableNotInCartException, EmptyRentListException {
		if(rented.size() <= 0) throw new EmptyRentListException();
		if(!rented.remove(r)) throw new RentableNotInCartException();
	}
	
}
