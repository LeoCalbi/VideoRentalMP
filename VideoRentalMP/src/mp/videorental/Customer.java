package mp.videorental;

import java.time.LocalDate;

public abstract class Customer extends User {

	private String address;
	private String telephone;
	private CustomerCard card;
	private Rented rented;
	
	public Customer(String name, String surname, LocalDate birthday, String address, String telephone) {
		super(name, surname, birthday);
		this.address = address;
		this.telephone = telephone;
		this.card = makeCustomerCard();
	}
	
	public abstract CustomerCard makeCustomerCard();
	
	public Double getCardDiscount(Double amount) {
		return card.getDiscount(amount);
	}
	
}
