package mp.videorental;

public class Genre {
	
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
	
	
	
}
