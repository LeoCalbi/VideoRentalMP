package mp.videorental.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Random;
import mp.videorental.*;
import mp.videorental.exception.*;

public class FastMain {
	
	private static final Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	
	public static void main(String[] args) {
		Customer customer = CustomerRepository.getInstance().stream().filter((Customer c) -> c.getSocialSecurityNumber().equals("AF")).findFirst().get();
		Cart cart = new Cart(customer);
		try {
			customer.depositOnCard(225.0);
		} catch (NegativeAmountException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Current customer -> " + customer.toString());
		System.out.println("Current customer card balance: " + customer.getCardBalance() + ", Current customer card points: " + customer.getCardPoints());
		MovieBox movieBox;
		try {
			movieBox = MovieBoxRepository.getInstance().stream().filter((MovieBox m) -> m.isAvailable()).findFirst().get();
		} catch(NoSuchElementException e) {
			Movie movie = MovieRepository.getInstance().stream().findFirst().get();
			movieBox = new DVD(movie, new Random().nextDouble() * 10);
			try {
				movieBox.add(defaultAdmin);
			} catch (IOException e1) {
				System.out.println(e.getMessage());
			}
		}
		Rent r;
		try {
			r = movieBox.rent(15, new Over10DaysDiscount());
			System.out.println("Adding this rent to the cart -> " + r.toString());
			cart.add(r);
			System.out.println("About to pay -> " + cart.toString());
			cart.pay();
			System.out.println("Items rented succesfully. New balance: " + customer.getCardBalance());
			System.out.println("Current rented items -> " + customer.getRentedDescription());
			System.out.println("About to return this rent -> " + r.toString());
			r.restitution();
			System.out.println("Item returned correctly. Current points: " + customer.getCardPoints());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
	}

}
