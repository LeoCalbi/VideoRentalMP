package mp.videorental.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class NewReleaseSurchargeTest {
	
	NewReleaseSurcharge strategy;
	
	@Before
	public void initFixture() {
		strategy = new NewReleaseSurcharge();
	}
	
	@Test(expected = AddToLeafCompositeException.class)
	public void testAdd() throws AddToLeafCompositeException {
		strategy.add(new NewReleaseSurcharge());
	}
	
	@Test
	public void testGetPrice() {
		Double initialPrice = 100.0;
		Double expectedPrice = 105.0;
		assertEquals(expectedPrice, strategy.getPrice(initialPrice));
	}

}
