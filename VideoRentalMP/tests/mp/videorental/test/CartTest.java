package mp.videorental.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import mp.videorental.*;
import mp.videorental.exception.*;

public class CartTest {
	Cart cart;
	Customer customer;
	@Before
	public void initFixture(){
		customer = new StandardCustomer("123","Alessio", "Falai", LocalDate.of(1997, 10, 12), "Via caduti sul lavoro", "3347703001",new Credentials("falaiAlessio", "test"));
		customer.depositOnCard(100.0);
		cart = new Cart(customer);
	}

	@Test
	public void testAdd() throws AlreadyRentedException, MaximumRentedItemsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		cart.add(a);
		Iterator<Rent> it = cart.getIterator();
		Integer n = 0;
		while(it.hasNext()) {
			it.next();
			n++;
		}
		Integer expected = 1;
		assertEquals(n, expected);
	}
	
	@Test(expected = MaximumRentedItemsException.class)
	public void testAddMaximumRentedItems() throws AlreadyRentedException, MaximumRentedItemsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13);
		Rent c = new DVD(new Movie("TitoloW", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Drama"), LocalDate.of(2003, 4, 24)), 5.0).rent(4);
		Rent d = new DVD(new Movie("TitoloZ", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Crime"), LocalDate.of(2017, 7, 8)), 7.0).rent(5);
		cart.add(a);
		cart.add(b);
		cart.add(c);
		cart.add(d);
	}
	
	@Test(expected = AlreadyRentedException.class)
	public void testAddAlreadyRented() throws AlreadyRentedException, MaximumRentedItemsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		cart.add(a);
		cart.add(a);
	}
	
	@Test
	public void testRemove() throws AlreadyRentedException, MaximumRentedItemsException, AbsentRentException, EmptyRentListException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		cart.add(a);
		cart.remove(a);
		Iterator<Rent> it = cart.getIterator();
		Integer n = 0;
		while(it.hasNext()) {
			it.next();
			n++;
		}
		Integer expected = 0;
		assertEquals(n, expected);
	}

	@Test(expected = EmptyRentListException.class)
	public void testRemoveEmptyRentList() throws AlreadyRentedException, MaximumRentedItemsException, AbsentRentException, EmptyRentListException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		cart.remove(a);
	}
	
	@Test(expected = AbsentRentException.class)
	public void testRemoveAbsentRent() throws AlreadyRentedException, MaximumRentedItemsException, AbsentRentException, EmptyRentListException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13);
		cart.add(a);
		cart.remove(b);
	}
	
	@Test
	public void testGetPrice() throws AlreadyRentedException, MaximumRentedItemsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13, new Over10DaysDiscount());
		cart.add(a);
		cart.add(b);
		Double expectedPrice = (3*10)+((4*13)-(4*13*0.10));
		assertEquals(expectedPrice, cart.getPrice());
	}

	@Test
	public void testPayCardBalance() throws AlreadyRentedException, MaximumRentedItemsException, InsufficientFundsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13);
		cart.add(a);
		cart.add(b);
		Double expectedPrice = customer.getCardBalance()-cart.getPrice();
		cart.pay();
		assertEquals(expectedPrice, customer.getCardBalance());
	}

	@Test
	public void testPayCardPoints() throws AlreadyRentedException, MaximumRentedItemsException, InsufficientFundsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13);
		cart.add(a);
		cart.add(b);
		cart.pay();
		Integer expectedPoints = 2;
		assertEquals(expectedPoints, customer.getCardPoints());
	}
	
	@Test
	public void testPayRentedItems() throws AlreadyRentedException, MaximumRentedItemsException, InsufficientFundsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13);
		cart.add(a);
		cart.add(b);
		cart.pay();
		Integer expectedRented = 2;
		assertEquals(expectedRented, customer.getRentedSize());
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testPayInsufficientFunds() throws AlreadyRentedException, MaximumRentedItemsException, InsufficientFundsException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		Rent b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Comedy"), LocalDate.of(2011, 6, 11)), 4.0).rent(13);
		Rent c = new DVD(new Movie("TitoloZ", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Drama"), LocalDate.of(2018, 1, 1)), 10.0).rent(15);
		cart.add(a);
		cart.add(b);
		cart.add(c);
		cart.pay();
	}
}
