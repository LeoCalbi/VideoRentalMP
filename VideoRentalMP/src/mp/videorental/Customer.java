package mp.videorental;
import java.time.LocalDate;

import mp.videorental.exception.AbsentRentException;
import mp.videorental.exception.EmptyRentListException;
import mp.videorental.exception.InsufficientFundsException;

public abstract class Customer extends User {

	private String address;
	private String telephone;
	private CustomerCard card;
	private Rented rented;
	
	public Customer(String name, String surname, LocalDate birthday, String address, String telephone) {
		super(name, surname, birthday);
		this.address = address;
		this.telephone = telephone;
		this.rented = new Rented();
	}
	
	public abstract CustomerCard makeCustomerCard();
	
	public final void setCard() {
		if(card == null) card = makeCustomerCard();
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
	
	public void makeCardPoints() {
		setCard();
		card.makePoints();
	}
	
	public Integer getRentedSize() {
		return rented.size();
	}
	
	public void addRentedItem(Rent r) {
		rented.add(r);
	}
	
	public void restitution(Rentable r) throws AbsentRentException, EmptyRentListException {
		if(rented.restitution(r)) card.removePoints();
	}
	
}
