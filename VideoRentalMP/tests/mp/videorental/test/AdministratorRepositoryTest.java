package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class AdministratorRepositoryTest {
	
	private static AdministratorRepository repo;
	private static Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private static Administrator admin = new Administrator("000", "test", "test", LocalDate.of(1970, 4, 10), new Credentials("test", "test"));
	
	@BeforeClass
	public static void init() {
		repo = AdministratorRepository.getInstance();
	}
	
	@Test
	public void testAdministratorRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		admin.add(defaultAdmin);
		assertTrue(repo.contains(admin));
		admin.remove(admin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testAdministratorRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException {
		admin.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testAdministratorRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException {
		defaultAdmin.add(defaultAdmin);
	}
	
	@Test
	public void testAdministratorRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		admin.add(defaultAdmin);
		admin.remove(admin);
		assertFalse(repo.contains(admin));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testAdministratorRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException {
		admin.remove(admin);
	}
	
	@Test(expected = NotRemovableAdminException.class)
	public void testAdministratorRepositoryRemoveNotRemovableAdmin() throws InvalidAdminException, StorableNotPresentException {
		defaultAdmin.remove(defaultAdmin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testAdministratorRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException {
		admin.remove(defaultAdmin);
	}
	
	@AfterClass
	public static void clear() {
		repo.removeIf((Administrator o) -> o.equals(admin));
	}
	
}
