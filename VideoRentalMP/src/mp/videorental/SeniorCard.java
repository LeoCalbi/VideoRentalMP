package mp.videorental;

public class SeniorCard extends CustomerCard {
	
	private static final Integer POINTS_TO_HANDLE = 2;
	
	public SeniorCard() {
		super();
	}
	
	@Override
	public Double getDiscount(Double amount) {
		return amount * 0.05;
	}

	@Override
	public Integer pointsToHandle() {
		return POINTS_TO_HANDLE;
	}

}
