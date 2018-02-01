package mp.videorental;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class Genre implements Storable {
	
	private static final long serialVersionUID = -913687112060192852L;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}
	
	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		GenreRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		GenreRepository.getInstance().remove(this, admin);
	}
	
}
