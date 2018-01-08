package mp.videorental.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class Over10DaysDiscountTest {
	
	Over10DaysDiscount strategy;
	
	@Before
	public void initFixture() {
		strategy = new Over10DaysDiscount();
	}
	
	@Test(expected = AddToLeafCompositeException.class)
	public void testAdd() throws AddToLeafCompositeException {
		strategy.add(new NewReleaseSurcharge());
	}
	
	@Test
	public void testGetPrice() {
		Double initialPrice = 100.0;
		Double expectedPrice = 90.0;
		assertEquals(expectedPrice, strategy.getPrice(initialPrice));
	}

}
