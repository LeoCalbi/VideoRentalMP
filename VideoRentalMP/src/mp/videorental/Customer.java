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
		this.rented = new Rented();
	}
	
	public abstract CustomerCard makeCustomerCard();
	
	public Double getCardDiscount(Double amount) {
		return card.getDiscount(amount);
	}
	
	public Integer getRentedSize() {
		return rented.size();
	}
	
}
