package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class StandardCustomerTest {
	
	StandardCustomer customer;
	
	@Before
	public void initFixture() {
		customer = new StandardCustomer("000","Test", "Test", LocalDate.of(1997, 10, 12), "TestAddress", "0000000000",new Credentials("test", "test"));
	}
	
	@Test
	public void testMakeCustomerCard() {
		assertTrue(customer.makeCustomerCard() instanceof StandardCard);
	}
	
	@Test
	public void testSetCard() {
		assertTrue(customer.setCard());
		assertFalse(customer.setCard());
	}
	
	@Test
	public void testGetCardDiscount() {
		Double amount = 100.0;
		Double expectedAmount = amount - customer.getCardDiscount(amount);
		assertEquals(amount, expectedAmount);
	}
	
	@Test
	public void testGetCardBalance() {
		Double amount = 0.0;
		assertEquals(amount, customer.getCardBalance());
	}
	
	@Test
	public void testDepositOnCard() throws NegativeAmountException {
		Double originalAmount = customer.getCardBalance();
		Double addedAmount = 10.0;
		customer.depositOnCard(addedAmount);
		Double finalAmount = originalAmount + addedAmount;
		assertEquals(finalAmount, customer.getCardBalance());
	}
	
	@Test(expected = NegativeAmountException.class)
	public void testDepositOnCardNegativeAmount() throws NegativeAmountException {
		Double amount = -10.0;
		customer.depositOnCard(amount);
	}
	
	@Test
	public void testWithdrawFromCard() throws InsufficientFundsException, NegativeAmountException {
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
		Integer pointsToAdd = 1;
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
		DVD a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		Rent b = a.rent(10);
		Integer originalSize = customer.getRentedSize();
		customer.addRentedItem(b);
		Integer finalSize = originalSize + 1;
		assertEquals(finalSize, customer.getRentedSize());
	}

	@Test
	public void testRestituition() throws EmptyRentListException, AbsentRentException, AlreadyRentedException, NotRentedException {
		DVD a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		Rent b = a.rent(10);
		Integer originalSize = customer.getRentedSize();
		customer.addRentedItem(b);
		customer.restitution(a);
		assertEquals(originalSize, customer.getRentedSize());
	}
	
	@Test
	public void testRestituitionExpired() throws EmptyRentListException, AbsentRentException, AlreadyRentedException, NotRentedException {
		DVD a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 5.5);
		Rent b = a.rent(-1);
		customer.addRentedItem(b);
		customer.restitution(a);
		Integer expectedPoints = -1;
		assertEquals(expectedPoints, customer.getCardPoints());
	}
	
	@Test(expected = EmptyRentListException.class)
	public void testRestituitionEmptyList() throws EmptyRentListException, AbsentRentException, NotRentedException {
		customer.restitution(new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 3.0));
	}
	
	@Test(expected = AbsentRentException.class)
	public void testRestituitionAbsentRent() throws EmptyRentListException, AbsentRentException, AlreadyRentedException, NotRentedException {
		Rent a = new DVD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 3.0).rent(10);
		DVD b = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 5.5);
		customer.addRentedItem(a);
		customer.restitution(b);
	}
	
	@Test(expected = NotRentedException.class)
	public void testRestitutionNotRented() throws AlreadyRentedException, NotRentedException {
		DVD a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		Rent b = a.rent(10);
		b.restitution();
		b.restitution();
	}
	
}
