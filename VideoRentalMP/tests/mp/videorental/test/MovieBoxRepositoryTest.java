package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class MovieBoxRepositoryTest {
	
	private static Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private static Administrator admin = new Administrator("000", "test", "test", LocalDate.of(1970, 4, 10), new Credentials("test", "test"));
	private static MovieBox movieBox1 = new BD(new Movie("Title1", new Director("test", "test"), new Genre("test"), LocalDate.of(2000, 01, 01)), 1.0);
	private static MovieBox movieBox2 = new DVD(new Movie("Title2", new Director("test", "test"), new Genre("test"), LocalDate.of(2000, 01, 01)), 1.0);
	private static MovieBoxRepository repo;
	
	@BeforeClass
	public static void init() {
		repo = MovieBoxRepository.getInstance();
	}
	
	@Test
	public void testMovieBoxRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		movieBox1.add(defaultAdmin);
		assertTrue(repo.contains(movieBox1));
		movieBox1.remove(defaultAdmin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testMovieBoxRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException {
		movieBox1.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testMovieBoxRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException {
		movieBox2.add(defaultAdmin);
		movieBox2.add(defaultAdmin);
	}
	
	@Test
	public void testMovieBoxRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		movieBox1.add(defaultAdmin);
		movieBox1.remove(defaultAdmin);
		assertFalse(repo.contains(movieBox1));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testMovieBoxRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException {
		movieBox1.remove(admin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testMovieBoxRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException {
		movieBox1.remove(defaultAdmin);
	}
	
	@AfterClass
	public static void clear() {
		repo.removeIf((MovieBox o) -> o.equals(movieBox1) || o.equals(movieBox2));
	}
	
}
