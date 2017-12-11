package mp.videorental;
import mp.videorental.exception.InsufficientFundsException;

public abstract class CustomerCard implements Card {
	
	private Integer serialNumber;
	private Double balance;
	private Integer points;
	
	public void deposit(Double amount) {
		balance += amount;
	}
	
	public void withdraw(Double amount) throws InsufficientFundsException {
		if(balance - amount >= 0) balance -= amount;
		throw new InsufficientFundsException();
	}

	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}

}
