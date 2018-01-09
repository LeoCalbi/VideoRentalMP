package mp.videorental;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.InsufficientFundsException;
import mp.videorental.exception.MaximumRentedItemsException;
import mp.videorental.exception.AbsentRentException;
import mp.videorental.exception.AlreadyRentedException;

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
	
	public void add(Rent r) throws MaximumRentedItemsException, AlreadyRentedException {
		if(Rented.MAX_RENTED - customer.getRentedSize() - toRent.size() > 0) {
			if(!toRent.contains(r))toRent.add(r);
			else throw new AlreadyRentedException();
		}
		else throw new MaximumRentedItemsException();
	}
	
	public void remove(Rent r) throws AbsentRentException, EmptyRentListException {
		if(toRent.size() <= 0) throw new EmptyRentListException();
		if(!toRent.remove(r)) throw new AbsentRentException();
	}
	
	public Double getPrice() {
		Iterator<Rent> it = getIterator();
		Double price = 0.0;
		while(it.hasNext()) price += it.next().getPrice();
		return price - customer.getCardDiscount(price);
	}
	
	public void pay() throws InsufficientFundsException {
		Double price = getPrice();
		customer.withdrawFromCard(price);
		Iterator<Rent> it = getIterator();
		while(it.hasNext()) {
			customer.makeCardPoints();
			customer.addRentedItem(it.next());
		}
	}
	
}
