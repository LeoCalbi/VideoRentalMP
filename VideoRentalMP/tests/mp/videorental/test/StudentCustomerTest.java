package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.AbsentRentException;
import mp.videorental.exception.AlreadyRentedException;
import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.InsufficientFundsException;

public class StudentCustomerTest {

	StudentCustomer customer;
	
	@Before
	public void initFixture() {
		customer = new StudentCustomer("Alessio", "Falai", LocalDate.of(1997, 10, 12), "Via caduti sul lavoro", "3347703001");
	}
	
	@Test
	public void testMakeCustomerCard() {
		assertTrue(customer.makeCustomerCard() instanceof StudentCard);
	}
	
	@Test
	public void testSetCard() {
		assertTrue(customer.setCard());
		assertFalse(customer.setCard());
	}
	
	@Test
	public void testGetCardDiscount() {
		Double amount = 100.0;
		Double discount = 10.0;
		assertEquals(discount, customer.getCardDiscount(amount));
	}
	
	@Test
	public void testGetCardBalance() {
		Double amount = 0.0;
		assertEquals(amount, customer.getCardBalance());
	}
	
	@Test
	public void testDepositOnCard() {
		Double originalAmount = customer.getCardBalance();
		Double addedAmount = 10.0;
		customer.depositOnCard(addedAmount);
		Double finalAmount = originalAmount + addedAmount;
		assertEquals(finalAmount, customer.getCardBalance());
	}
	
	@Test
	public void testWithdrawFromCard() throws InsufficientFundsException {
		Double originalAmount = customer.getCardBalance();
		Double amount = 10.0;
		customer.depositOnCard(amount);
		customer.withdrawFromCard(amount);
		Double finalAmount = customer.getCardBalance();
		assertEquals(finalAmount, originalAmount);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testWithdrawFromCardInsufficientFunds() throws InsufficientFundsException {
		Double removedAmount = customer.getCardBalance() + 10.0;
		customer.withdrawFromCard(removedAmount);
	}
	
	@Test
	public void testGetCardPoints() {
		Integer points = 0;
		assertEquals(points, customer.getCardPoints());
	}
	
	@Test
	public void testMakeCardPoints() {
		Integer pointsToAdd = 3;
		Integer originalPoints = customer.getCardPoints();
		customer.makeCardPoints();
		Integer finalPoints = originalPoints + pointsToAdd;
		assertEquals(finalPoints, customer.getCardPoints());
	}
	
	@Test
	public void testGetRentedSize() {
		Integer size = 0;
		assertEquals(size, customer.getRentedSize());
	}
	
	@Test
	public void testAddRentedItem() throws AlreadyRentedException {
		DVD a = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Bellissimo"), LocalDate.of(2000, 10, 11)), 5.5);
		Rent b = a.rent(10);
		Integer originalSize = customer.getRentedSize();
		customer.addRentedItem(b);
		Integer finalSize = originalSize + 1;
		assertEquals(finalSize, customer.getRentedSize());
	}

	@Test
	public void testRestituition() throws EmptyRentListException, AbsentRentException, AlreadyRentedException {
		DVD a = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Bellissimo"), LocalDate.of(2000, 10, 11)), 5.5);
		Rent b = a.rent(10);
		Integer originalSize = customer.getRentedSize();
		customer.addRentedItem(b);
		customer.restitution(a);
		assertEquals(originalSize, customer.getRentedSize());
	}

	@Test(expected = EmptyRentListException.class)
	public void testRestituitionEmptyList() throws EmptyRentListException, AbsentRentException {
		customer.restitution(new DVD(new Movie("Titolo", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Bello"), LocalDate.of(2010, 5, 10)), 3.0));
	}
	
	@Test(expected = AbsentRentException.class)
	public void testRestituitionAbsentRent() throws EmptyRentListException, AbsentRentException, AlreadyRentedException {
		Rent a = new DVD(new Movie("TitoloX", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Bello"), LocalDate.of(2010, 5, 10)), 3.0).rent(10);
		DVD b = new DVD(new Movie("TitoloY", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Bellissimo"), LocalDate.of(2000, 10, 11)), 5.5);
		customer.addRentedItem(a);
		customer.restitution(b);
	}

}
