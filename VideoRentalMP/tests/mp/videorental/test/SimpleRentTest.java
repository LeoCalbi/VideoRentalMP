package mp.videorental.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class SimpleRentTest {
	private Rent rent;
	
	@Test
	public void testRestitution() throws NotRentedException, AlreadyRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 7.0);
		rent = a.rent(20);
		rent.restitution();
		assertTrue(a.isAvailable());
	}
	
	@Test(expected = NotRentedException.class)
	public void testRestitutionNotRented() throws NotRentedException, AlreadyRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 7.0);
		rent = a.rent(20);
		a.restitution();
		rent.restitution();
	}
	
	@Test
	public void testCompareRentable() throws AlreadyRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 7.0);
		MovieBox b = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 10.0);
		rent = a.rent(20);
		assertTrue(rent.compareRentable(a));
		assertFalse(rent.compareRentable(b));
	}

	@Test
	public void testEquals() throws AlreadyRentedException, NotRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 7.0);
		MovieBox b = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 10, 11)), 10.0);
		rent = a.rent(20);
		assertTrue(rent.equals(rent));
		assertFalse(rent.equals(b.rent(10)));
		rent.restitution();
		assertTrue(rent.equals(a.rent(20)));
	}

}
