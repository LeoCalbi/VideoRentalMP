package mp.videorental.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class RentPriceStrategyCompoundTest {

	RentPriceStrategyCompound strategy;
	
	@Before
	public void initFixture() {
		strategy = new RentPriceStrategyCompound();
	}
	
	@Test
	public void testAdd() throws AddToLeafCompositeException {
		strategy.add(new NewReleaseSurcharge());
		Iterator<RentPriceStrategy> it = strategy.getIterator();
		Integer n = 0;
		while(it.hasNext()) {
			it.next();
			n++;
		}
		Integer expected = 1;
		assertEquals(n, expected);
	}
	
	@Test
	public void testGetPrice() throws AddToLeafCompositeException {
		RentPriceStrategyCompound c = new RentPriceStrategyCompound();
		c.add(new NewReleaseSurcharge());
		c.add(new Over10DaysDiscount());
		strategy.add(c);
		Double initialPrice = 100.0;
		Double expectedPrice = 95.0;
		assertEquals(expectedPrice, strategy.getPrice(initialPrice));
	}

}
