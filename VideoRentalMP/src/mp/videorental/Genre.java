package mp.videorental;

public class Genre implements Storable{
	
	private String description;
	
	public Genre(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Genre) {
			Genre other = (Genre) obj;
			return description.equals(other.description);
		}
		return false;
	}
	
	@Override
	public void add(Administrator admin) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(Administrator admin) {
		// TODO Auto-generated method stub
		
	}
	
}
