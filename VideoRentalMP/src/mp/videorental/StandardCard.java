package mp.videorental;

public class StandardCard extends CustomerCard {
	
	private static final Integer POINTS_TO_ADD = 1;
	
	public StandardCard() {
		super();
	}
	
	@Override
	public Double getDiscount(Double amount) {
		return 0.0;
	}

	@Override
	public Integer pointsToAdd() {
		return POINTS_TO_ADD;
	}

}
