package mp.videorental.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class RentedTest {
	Rented rented;
	@Before
	public void initFixture(){
		rented = new Rented();
	}

	@Test
	public void testAdd() throws AlreadyRentedException {
		Rent a = new BD(new Movie("Title", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5).rent(10);
		rented.add(a);
		Integer expectedSize = 1;
		Iterator<Rent> it = rented.getIterator();
		assertEquals(expectedSize, rented.size());
		assertEquals(a, it.next());
	}

	@Test
	public void testRestitution() throws AlreadyRentedException, AbsentRentException, EmptyRentListException, NotRentedException {
		MovieBox a = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		MovieBox b = new BD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2001, 01, 01)), 7.2);
		Rent c = a.rent(10);
		Rent d = b.rent(14);
		rented.add(c);
		rented.add(d);
		rented.restitution(a);
		Integer expectedSize = 1;
		Iterator<Rent> it = rented.getIterator();
		assertEquals(expectedSize, rented.size());
		assertEquals(d, it.next());
	}
	
	@Test(expected = AbsentRentException.class)
	public void testRestitutionAbsentRent() throws AlreadyRentedException, AbsentRentException, EmptyRentListException, NotRentedException {
		MovieBox a = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		MovieBox b = new BD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2001, 01, 01)), 7.2);
		Rent c = a.rent(10);
		rented.add(c);
		rented.restitution(b);
	}
	
	@Test(expected = EmptyRentListException.class)
	public void testRestitutionEmptyRentList() throws AlreadyRentedException, AbsentRentException, EmptyRentListException, NotRentedException {
		MovieBox a = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		rented.restitution(a);
	}

	@Test(expected = NotRentedException.class)
	public void testRestitutionNotRented() throws AlreadyRentedException, AbsentRentException, EmptyRentListException, NotRentedException {
		MovieBox a = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 5.5);
		MovieBox b = new BD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2001, 01, 01)), 7.2);
		Rent c = a.rent(10);
		Rent d = b.rent(10);
		rented.add(c);
		rented.add(d);
		d.restitution();
		rented.restitution(b);
	}
}
