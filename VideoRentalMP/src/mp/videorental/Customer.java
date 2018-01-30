package mp.videorental;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import mp.videorental.exception.AbsentRentException;
import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.InsufficientFundsException;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.NotRentedException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public abstract class Customer extends User {

	private static final long serialVersionUID = 6794331337964004743L;
	private String address;
	private String telephone;
	private CustomerCard card;
	private Rented rented;
	
	public Customer(String socialSecurityNumber,String name, String surname, LocalDate birthday, String address, String telephone, Credentials credentials) {
		super(socialSecurityNumber,name, surname, birthday, credentials);
		this.address = address;
		this.telephone = telephone;
		this.rented = new Rented();
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public abstract CustomerCard makeCustomerCard();
	
	public final boolean setCard() {
		if(card == null) {
			card = makeCustomerCard();
			return true;
		}
		return false;
	}
	
	public Double getCardDiscount(Double amount) {
		setCard();
		return card.getDiscount(amount);
	}
	
	public Double getCardBalance() {
		setCard();
		return card.getBalance();
	}
	
	public void depositOnCard(Double amount) {
		setCard();
		card.deposit(amount);
	}
	
	public void withdrawFromCard(Double amount) throws InsufficientFundsException {
		setCard();
		card.withdraw(amount);
	}
	
	public Integer getCardPoints() {
		setCard();
		return card.getPoints();
	}
	
	public void makeCardPoints() {
		setCard();
		card.makePoints();
	}
	
	public Integer getRentedSize() {
		return rented.size();
	}
	
	public void addRentedItem(Rent r) {
		setCard();
		rented.add(r);
	}
	
	public void restitution(Rentable r) throws AbsentRentException, EmptyRentListException, NotRentedException {
		if(rented.restitution(r)) card.removePoints();
	}
	
	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		CustomerRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		CustomerRepository.getInstance().remove(this, admin);
	}
	
}
