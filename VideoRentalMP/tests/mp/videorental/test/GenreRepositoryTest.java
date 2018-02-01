package mp.videorental.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class GenreRepositoryTest {
	
	private static Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private static Administrator admin = new Administrator("000", "test", "test", LocalDate.of(1970, 4, 10), new Credentials("test", "test"));
	private static Genre genre1 = new Genre("test1");
	private static Genre genre2 = new Genre("test2");
	private static GenreRepository repo;
	
	@BeforeClass
	public static void init() throws FileNotFoundException, ClassNotFoundException, IOException {
		repo = GenreRepository.getInstance();
	}
	
	@Test
	public void testGenreRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		genre1.add(defaultAdmin);
		assertTrue(repo.contains(genre1));
		genre1.remove(defaultAdmin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testGenreRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		genre1.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testGenreRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		genre2.add(defaultAdmin);
		genre2.add(defaultAdmin);
	}
	
	@Test
	public void testGenreRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		genre1.add(defaultAdmin);
		genre1.remove(defaultAdmin);
		assertFalse(repo.contains(genre1));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testGenreRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		genre1.remove(admin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testGenreRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		genre1.remove(defaultAdmin);
	}
	
	@AfterClass
	public static void clear() throws FileNotFoundException, ClassNotFoundException, IOException{
		repo.removeIf((Genre o)->o.equals(genre1) || o.equals(genre2));
	}
}
