package mp.videorental;

public class Rent {
	
	private Rentable item;
	private Integer days;
	private RentPriceStrategy strategy;
	
	public Rent(Rentable item, Integer days, RentPriceStrategy strategy) {
		this.item = item;
		this.days = days;
		this.strategy = strategy;
	}

	private Double getInitialPrice() {
		return item.getDailyPrice() * days;
	}
	
	public Double getPrice() {
		return strategy.getPrice(getInitialPrice());
	}
	
}