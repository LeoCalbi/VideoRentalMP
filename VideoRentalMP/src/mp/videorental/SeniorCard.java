package mp.videorental;

public class SeniorCard extends CustomerCard {
	
	private static final Integer POINTS_TO_ADD = 2;
	
	public SeniorCard() {
		super();
	}
	
	@Override
	public Double getDiscount(Double amount) {
		return amount * 0.05;
	}

	@Override
	public Integer pointsToAdd() {
		return POINTS_TO_ADD;
	}

}
