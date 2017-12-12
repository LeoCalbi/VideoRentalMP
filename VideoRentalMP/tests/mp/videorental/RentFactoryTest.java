package mp.videorental;
import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;

public class RentFactoryTest {
	
	private RentFactoryTest r;
	
	@Before
	public void initFixture() {
		r = new RentFactoryTest();
	}
	
	@Test
	public void testMakeRent() {
		Rentable item = new MovieBox(new Movie("Ciao", new Director("Giova", "Panza", new LocalDate(10, 12, 2017)), new Genre("Kids"), new LocalDate()), 10.0);
	}

}
