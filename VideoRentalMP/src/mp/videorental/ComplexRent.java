package mp.videorental;

public class ComplexRent extends Rent {
	private RentPriceStrategy strategy;
	
	public ComplexRent(Rentable item, Integer days,RentPriceStrategy strategy) {
		super(item, days);
		this.strategy=strategy;
	}

	@Override
	public Double getPrice() {
		return strategy.getPrice(super.getPrice());
	}

}
