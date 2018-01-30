package mp.videorental;

public class StandardCard extends CustomerCard {
	
	private static final long serialVersionUID = 4066435799836592675L;
	private static final Integer POINTS_TO_HANDLE = 1;
	
	public StandardCard() {
		super();
	}
	
	@Override
	public Double getDiscount(Double amount) {
		return 0.0;
	}

	@Override
	public Integer pointsToHandle() {
		return POINTS_TO_HANDLE;
	}

}
