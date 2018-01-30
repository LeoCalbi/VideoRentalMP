package mp.videorental.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import org.junit.Test;
import mp.videorental.Customer;
import mp.videorental.CustomerRepository;
import mp.videorental.StandardCustomer;
import mp.videorental.Administrator;
import mp.videorental.Credentials;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class CustomerRepositoryTest {
	
	Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	
	@Test
	public void testCustomerRepositoryAdd() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Customer customer = new StandardCustomer("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), "Address", "3333333", new Credentials("prova", "adminadmin"));
		customer.add(defaultAdmin);
		assertTrue(CustomerRepository.getInstance().contains(customer));
		customer.remove(defaultAdmin);
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testCustomerRepositoryAddInvalidAdmin() throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		Customer customer = new StandardCustomer("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), "Address", "3333333", new Credentials("prova", "adminadmin"));
		customer.add(admin);
	}
	
	@Test(expected = StorableAlreadyPresentException.class)
	public void testCustomerRepositoryAddStorableAlreadyPresent() throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Customer customer = new StandardCustomer("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), "Address", "3333333", new Credentials("prova", "adminadmin"));
		customer.add(defaultAdmin);
		customer.add(defaultAdmin);
	}
	
	@Test
	public void testCustomerRepositoryRemove() throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Customer customer = new StandardCustomer("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), "Address", "3333333", new Credentials("prova12", "adminadmin121"));
		customer.add(defaultAdmin);
		customer.remove(defaultAdmin);
		assertFalse(CustomerRepository.getInstance().contains(customer));
	}
	
	@Test(expected = InvalidAdminException.class)
	public void testCustomerRepositoryRemoveInvalidAdmin() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Administrator admin = new Administrator("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), new Credentials("prova", "adminadmin"));
		Customer customer = new StandardCustomer("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), "Address", "3333333", new Credentials("prova", "adminadmin"));
		customer.remove(admin);
	}
	
	@Test(expected = StorableNotPresentException.class)
	public void testCustomerRepositoryRemoveStorableNotPresent() throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, ClassNotFoundException, IOException {
		Customer customer = new StandardCustomer("567", "prova", "adminadmin", LocalDate.of(1970, 4, 10), "Address", "3333333", new Credentials("prova123", "adminadmin6783"));
		customer.remove(defaultAdmin);
	}

}
