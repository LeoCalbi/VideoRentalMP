package mp.videorental;

public class SeniorCard extends CustomerCard {
	
	private static final Integer POINTS_TO_ADD = 3;
	
	@Override
	public Double getDiscount(Double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makePoints() {
		setPoints(getPoints() + POINTS_TO_ADD);
	}

}
