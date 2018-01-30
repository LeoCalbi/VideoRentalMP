package mp.videorental;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class Movie implements Storable {

	private static final long serialVersionUID = 4415245065621432304L;
	//TODO controllare get di classi Director e Genre
	private String title;
	private Director director;
	private Genre genre;
	private LocalDate releaseDate;
	
	public Movie(String title, Director director, Genre genre, LocalDate releaseDate) {
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public Director getDirector() {
		return director;
	}

	public Genre getGenre() {
		return genre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Movie) {
			Movie other = (Movie) obj;
			if(title.equals(other.title) && director.equals(other.director) && genre.equals(other.genre) && releaseDate.equals(other.releaseDate)) return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		return result;
	}

	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		MovieRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		MovieRepository.getInstance().remove(this, admin);
	}
	
}
