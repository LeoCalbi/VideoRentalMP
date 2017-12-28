package mp.videorental;

public class StudentCard extends CustomerCard {
	
	private static final Integer POINTS_TO_HANDLE = 3;
	
	public StudentCard() {
		super();
	}
	
	@Override
	public Double getDiscount(Double amount) {
		return amount * 0.10;
	}

	@Override
	public Integer pointsToHandle() {
		return POINTS_TO_HANDLE;
	}

}
