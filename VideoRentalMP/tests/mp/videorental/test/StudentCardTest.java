package mp.videorental.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class StudentCardTest {

	StudentCard card;
	
	@Before
	public void initFixture() {
		card = new StudentCard();
	}
	
	@Test
	public void testGetDiscount() {
		Double amount = 100.0;
		Double discount = 10.0;
		assertEquals(discount, card.getDiscount(amount));
	}
	
	@Test
	public void testPointsToHandle() {
		Integer pointsToHandle = 3;
		assertEquals(card.pointsToHandle(), pointsToHandle);
	}
	
	@Test
	public void testDeposit() {
		Double amount = 10.0;
		Double originalAmount = card.getBalance();
		card.deposit(amount);
		Double finalAmount = originalAmount + amount;
		assertEquals(card.getBalance(), finalAmount);
	}
	
	@Test
	public void testWithdraw() throws InsufficientFundsException {
		Double amount = 10.0;
		Double originalAmount = card.getBalance();
		card.deposit(amount);
		card.withdraw(amount);
		assertEquals(originalAmount, card.getBalance());
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testWithdrawInsufficientFunds() throws InsufficientFundsException {
		Double amount = card.getBalance() + 10;
		card.withdraw(amount);
	}
	
	@Test
	public void testMakePoints() {
		Integer originalPoints = card.getPoints();
		Integer expectedPoints = originalPoints + 3;
		card.makePoints();
		assertEquals(card.getPoints(), expectedPoints);
	}
	
	@Test
	public void testRemovePoints() {
		Integer originalPoints = card.getPoints();
		Integer expectedPoints = originalPoints - 3;
		card.removePoints();
		assertEquals(card.getPoints(), expectedPoints);
	}

}
