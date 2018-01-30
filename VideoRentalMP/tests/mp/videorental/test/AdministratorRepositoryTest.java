package mp.videorental.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import org.junit.Test;
import mp.videorental.Administrator;
import mp.videorental.AdministratorRepository;
import mp.videorental.Credentials;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.NotRemovableAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class AdministratorRepositoryTest {
	
	@Test
	public void testAdministratorRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		admin.add(defaultAdmin);
		assertTrue(AdministratorRepository.getInstance().contains(admin));
		admin.remove(admin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testAdministratorRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		admin.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testAdministratorRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
		defaultAdmin.add(defaultAdmin);
	}
	
	@Test
	public void testAdministratorRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		admin.add(defaultAdmin);
		admin.remove(admin);
		assertFalse(AdministratorRepository.getInstance().contains(admin));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testAdministratorRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		admin.remove(admin);
	}
	
	@Test(expected = NotRemovableAdminException.class)
	public void testAdministratorRepositoryRemoveNotRemovableAdmin() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
		defaultAdmin.remove(defaultAdmin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testAdministratorRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		admin.remove(defaultAdmin);
	}

}
