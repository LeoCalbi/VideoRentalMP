package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class MovieRepositoryTest {
	
	private static Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private static Administrator admin = new Administrator("000", "test", "test", LocalDate.of(1970, 4, 10), new Credentials("test", "test"));
	private static Movie movie1 = new Movie("test1", new Director("test", "test", LocalDate.of(1970, 4, 10)), new Genre("test"), LocalDate.of(2000, 10, 11));
	private static Movie movie2 = new Movie("test2", new Director("test", "test", LocalDate.of(1970, 4, 10)), new Genre("test"), LocalDate.of(2000, 10, 11));
	private static MovieRepository repo;
	
	@BeforeClass
	public static void init() {
		repo = MovieRepository.getInstance();
	}
	
	@Test
	public void testMovieRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		movie1.add(defaultAdmin);
		assertTrue(repo.contains(movie1));
		movie1.remove(defaultAdmin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testMovieRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException {
		movie1.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testMovieRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException {
		movie2.add(defaultAdmin);
		movie2.add(defaultAdmin);
	}
	
	@Test
	public void testMovieRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		movie1.add(defaultAdmin);
		movie1.remove(defaultAdmin);
		assertFalse(repo.contains(movie1));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testMovieRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException {
		movie1.remove(admin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testMovieRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException {
		movie1.remove(defaultAdmin);
	}
	
	@AfterClass
	public static void clear() {
		repo.removeIf((Movie o) -> o.equals(movie1) || o.equals(movie2));
	}
	
}
