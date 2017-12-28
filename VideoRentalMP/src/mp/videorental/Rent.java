package mp.videorental;
import java.time.LocalDate;

public class Rent {
	
	private Rentable item;
	private Integer days;
	private LocalDate rentDate;
	private RentPriceStrategy strategy;
	
	public Rent(Rentable item, Integer days, RentPriceStrategy strategy) {
		this.item = item;
		this.days = days;
		this.strategy = strategy;
		this.rentDate = LocalDate.now();
	}
	
	public Integer getDays() {
		return days;
	}
	
	public LocalDate getRentDate() {
		return rentDate;
	}

	private Double getInitialPrice() {
		return item.getDailyPrice() * days;
	}
	
	public Double getPrice() {
		return strategy.getPrice(getInitialPrice());
	}
	
	public void restitution() {
		item.restitution();
	}
	
	public boolean compareRentable(Rentable r) {
		return r.equals(item);
	}
	
}