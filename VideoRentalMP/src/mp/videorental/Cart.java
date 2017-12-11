package mp.videorental;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.RentableNotInCartException;

public class Cart {
	
	private List<Rent> toRent;
	private Customer customer;
	
	public Cart(Customer customer) {
		toRent = new LinkedList<Rent>();
		this.customer = customer;
	}
	
	public Iterator<Rent> getIterator() {
		return toRent.iterator();
	}
	
	public void add(Rent r) {
		toRent.add(r);
	}
	
	public void remove(Rent r) throws RentableNotInCartException, EmptyRentListException {
		if(toRent.size() <= 0) throw new EmptyRentListException();
		if(!toRent.remove(r)) throw new RentableNotInCartException();
	}
	
	public Double getPrice() {
		Iterator<Rent> it = getIterator();
		Double price = 0.0;
		while(it.hasNext()) price += it.next().getPrice();
		return price - customer.getCardDiscount(price);
	}
	
}
