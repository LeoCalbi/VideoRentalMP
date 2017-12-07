package mp.videorental;

public class Rent {
	
	private Rentable item;
	private Integer days;
	
	public Rent(Rentable item, Integer days) {
		this.item = item;
		this.days = days;
	}

	public Double getPrice() {
		return item.getDailyPrice() * days;
	}
	
	public Double getPrice(RentPriceStrategy strategy) {
		return strategy.getPrice(this);
	}
	
}
