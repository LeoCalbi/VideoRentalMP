package mp.videorental;

import mp.videorental.exception.InsufficientFundsException;
import mp.videorental.exception.NegativeAmountException;

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
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void deposit(Double amount) throws NegativeAmountException {
		if(amount < 0) throw new NegativeAmountException();
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
