package mp.videorental.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class ComplexRentTest {
	Rent rent;

	private Rent rentWithOver10DaysDiscount() {
		return new ComplexRent(new DVD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 7.0),20,new Over10DaysDiscount());
	}
	
	private Rent rentWithNewReleaseSurcharge() {
		return new ComplexRent(new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2018, 02, 01)), 7.0),1,new NewReleaseSurcharge());
	}
	
	private Rent rentWithPriceStrategyCompound() throws AddToLeafCompositeException {
		RentPriceStrategy s = new RentPriceStrategyCompound();
		s.add(new Over10DaysDiscount());
		s.add(new NewReleaseSurcharge());
		return new ComplexRent(new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2018, 02, 01)), 7.0),20,s);
	}
	
	@Test
	public void testGetPriceOver10DaysDiscount() {
		rent = rentWithOver10DaysDiscount();
		Double expectedPrice = 126.0;
		assertEquals(expectedPrice, rent.getPrice());
	}
	
	@Test
	public void testGetPriceNewReleaseSurcharge() {
		rent = rentWithNewReleaseSurcharge();
		Double expectedPrice = 7.35;
		assertEquals(expectedPrice, rent.getPrice());
	}
	
	@Test
	public void testGetPriceStrategyCompound() throws AddToLeafCompositeException {
		rent = rentWithPriceStrategyCompound();
		Double expectedPrice = 133.0;
		assertEquals(expectedPrice, rent.getPrice());
	}
	
	@Test
	public void testRestitution() throws NotRentedException, AlreadyRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 7.0);
		rent = a.rent(20,new Over10DaysDiscount());
		rent.restitution();
		assertTrue(a.isAvailable());
	}
	
	@Test(expected = NotRentedException.class)
	public void testRestitutionNotRented() throws NotRentedException, AlreadyRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 7.0);
		rent = a.rent(20,new Over10DaysDiscount());
		a.restitution();
		rent.restitution();
	}
	
	@Test
	public void testCompareRentable() throws AlreadyRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 7.0);
		MovieBox b = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 10.0);
		rent = a.rent(20,new Over10DaysDiscount());
		assertTrue(rent.compareRentable(a));
		assertFalse(rent.compareRentable(b));
	}

	@Test
	public void testEquals() throws AlreadyRentedException, NotRentedException {
		MovieBox a = new DVD(new Movie("TitleY", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 7.0);
		MovieBox b = new BD(new Movie("TitleX", new Director("Test", "Test"), new Genre("Test"), LocalDate.of(2000, 01, 01)), 10.0);
		rent = a.rent(20,new Over10DaysDiscount());
		assertTrue(rent.equals(rent));
		assertFalse(rent.equals(b.rent(10)));
		rent.restitution();
		assertTrue(rent.equals(a.rent(20,new Over10DaysDiscount())));
		
	}

}
