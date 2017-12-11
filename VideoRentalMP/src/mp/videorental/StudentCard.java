package mp.videorental;

public class StudentCard extends CustomerCard {
	
	private static final Integer POINTS_TO_ADD = 3;
	
	public StudentCard() {
		super();
	}
	
	@Override
	public Double getDiscount(Double amount) {
		return amount * 0.10;
	}

	@Override
	public Integer pointsToAdd() {
		return POINTS_TO_ADD;
	}

}
