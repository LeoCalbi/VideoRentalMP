package mp.videorental;
import java.time.LocalDate;

import mp.videorental.exception.NotRentedException;

public class Rent {
	//TODO aggiungere interfaccia per astrazione
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
	
	public Rent(Rentable item, Integer days) {
		this.item = item;
		this.days = days;
		this.strategy = new RentPriceStrategyCompound();
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