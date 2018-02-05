package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class DirectorRepositoryTest {
	
	private static Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private static Administrator admin = new Administrator("000", "test", "test", LocalDate.of(1970, 4, 10), new Credentials("test", "test"));
	private static Director director1 = new Director("test1", "test1");
	private static Director director2 = new Director("test2", "test2");
	private static DirectorRepository repo;
	
	@BeforeClass
	public static void init() {
		repo = DirectorRepository.getInstance();
	}
	
	@Test
	public void testDirectorRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		director1.add(defaultAdmin);
		assertTrue(repo.contains(director1));
		director1.remove(defaultAdmin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testDirectorRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException {
		director1.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testDirectorRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException {
		director2.add(defaultAdmin);
		director2.add(defaultAdmin);
	}
	
	@Test
	public void testDirectorRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		director1.add(defaultAdmin);
		director1.remove(defaultAdmin);
		assertFalse(repo.contains(director1));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testDirectorRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException {
		director1.remove(admin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testDirectorRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException {
		director1.remove(defaultAdmin);
	}
	
	@AfterClass
	public static void clear() {
		repo.removeIf((Director o) -> o.equals(director1) || o.equals(director2));
	}
	
}
