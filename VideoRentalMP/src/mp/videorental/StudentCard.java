package mp.videorental;

public class StudentCard extends CustomerCard {
	
	private static final Integer POINTS_TO_ADD = 2;
	
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
