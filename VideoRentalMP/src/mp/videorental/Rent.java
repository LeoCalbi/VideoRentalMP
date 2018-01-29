package mp.videorental;
import java.time.LocalDate;

import mp.videorental.exception.NotRentedException;

public abstract class Rent {
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
}