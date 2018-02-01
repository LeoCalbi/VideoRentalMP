package mp.videorental.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import mp.videorental.*;
import mp.videorental.exception.*;

public class CustomerRepositoryTest {
	
	private static Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	private static Administrator admin = new Administrator("000", "test", "test", LocalDate.of(1970, 4, 10), new Credentials("test", "test"));
	private static Customer customer1 = new StandardCustomer("000", "test1", "test1", LocalDate.of(1970, 4, 10), "testAddress1", "testNumber1", new Credentials("test1", "test1"));
	private static Customer customer2 = new StandardCustomer("001", "test2", "test2", LocalDate.of(1970, 4, 10), "testAddress2", "testNumber2", new Credentials("test2", "test2"));
	private static CustomerRepository repo;
	
	@BeforeClass
	public static void init() {
		repo = CustomerRepository.getInstance();
	}
	
	@Test
	public void testCustomerRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		customer1.add(defaultAdmin);
		assertTrue(repo.contains(customer1));
		customer1.remove(defaultAdmin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testCustomerRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException {
		customer1.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testCustomerRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException {
		customer2.add(defaultAdmin);
		customer2.add(defaultAdmin);
	}
	
	@Test
	public void testCustomerRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		customer1.add(defaultAdmin);
		customer1.remove(defaultAdmin);
		assertFalse(repo.contains(customer1));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testCustomerRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException {
		customer1.remove(admin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testCustomerRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException {
		customer1.remove(defaultAdmin);
	}
	
	@AfterClass
	public static void clear() {
		repo.removeIf((Customer o) -> o.equals(customer1) || o.equals(customer2));
	}
	
}
