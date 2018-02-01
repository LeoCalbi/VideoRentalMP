package mp.videorental;
import java.io.Serializable;
import java.time.LocalDate;

import mp.videorental.exception.NotRentedException;

public abstract class Rent implements Serializable {
	
	private static final long serialVersionUID = -6264812059960295609L;
	private Rentable item;
	private Integer days;
	private LocalDate rentDate;
	
	public Rent(Rentable item, Integer days) {
		this.item = item;
		this.days = days;
		this.rentDate = LocalDate.now();
	}
	
	public Integer getDays() {
		return days;
	}
	
	public LocalDate getRentDate() {
		return rentDate;
	}
	
	public Integer getRentableSerialNumber() {
		return item.getSerialNumber();
	}
	
	public Double getPrice() {
		return item.getDailyPrice() * days;
	}
	
	public void restitution() throws NotRentedException {
		item.restitution();
	}
	
	public boolean compareRentable(Rentable r) {
		return item.equals(r);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rent) {
			Rent other = (Rent) obj;
			return compareRentable(other.item) && rentDate.equals(other.rentDate) && days.equals(other.days);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Rentable: [" + item.toString() + "], Price: " + getPrice() + ", Days: " + days + "\n";
	}
	
}