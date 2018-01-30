package mp.videorental;

import mp.videorental.exception.InsufficientFundsException;

public abstract class CustomerCard implements Card {
	
	private static final long serialVersionUID = -6512048992281602710L;
	private static Integer lastSerialNumber = 0;
	private Integer serialNumber;
	private Double balance;
	private Integer points;
	
	public CustomerCard() {
		balance = 0.0;
		points = 0;
		serialNumber = lastSerialNumber++;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void deposit(Double amount) {
		balance += amount;
	}
	
	public void withdraw(Double amount) throws InsufficientFundsException {
		if(balance - amount >= 0) balance -= amount;
		else throw new InsufficientFundsException();
	}

	@Override
	public void makePoints() {
		points += pointsToHandle();
	}
	
	public void removePoints() {
		points -= pointsToHandle();	
	}
	
	public abstract Integer pointsToHandle();

}
