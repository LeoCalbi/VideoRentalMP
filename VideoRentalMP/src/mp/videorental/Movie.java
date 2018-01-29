package mp.videorental;
import java.time.LocalDate;

public class Movie implements Storable{
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
	public void add(Administrator admin) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(Administrator admin) {
		// TODO Auto-generated method stub
		
	}
}
