package mp.videorental.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import mp.videorental.*;
import mp.videorental.exception.*;

public class BDTest {
	BD moviebox;
	@Before
	public void initFixture(){
		moviebox = new BD(new Movie("Titolo", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2000, 10, 11)), 5.5);
	}

	@Test
	public void testGetDailyPrice() {
		Double expectedPrice = 5.5 + 1.5;
		assertEquals(expectedPrice, moviebox.getDailyPrice());
	}

	@Test
	public void testRent() throws AlreadyRentedException {
		Rent a = moviebox.rent(10);
		assertTrue(a.compareRentable(moviebox));
	}

	@Test(expected = AlreadyRentedException.class)
	public void testRentAlreadyRented() throws AlreadyRentedException {
		Rent a = moviebox.rent(10);
		Rent b = moviebox.rent(11);
	}
	
	@Test
	public void testIsAvailable() {
		assertTrue(moviebox.isAvailable());
	}
	
	@Test
	public void testIsNotAvailable() throws AlreadyRentedException {
		Rent a = moviebox.rent(10);
		assertFalse(moviebox.isAvailable());
	}
	
	@Test
	public void testRestitution() throws AlreadyRentedException, NotRentedException {
		Rent a = moviebox.rent(10);
		a.restitution();
		assertTrue(moviebox.isAvailable());
	}

	@Test(expected = NotRentedException.class)
	public void testRestitutionNotRented() throws AlreadyRentedException, NotRentedException {
		Rent a = moviebox.rent(10);
		a.restitution();
		a.restitution();
	}
	
	@Test
	public void testEquals() {
		BD other = new BD(new Movie("Titolo", new Director("Leonardo", "Calbi", LocalDate.of(1997, 3, 8)), new Genre("Action"), LocalDate.of(2000, 10, 11)), 5.5);
		assertFalse(moviebox.equals(other));
		assertTrue(moviebox.equals(moviebox));
	}

}
