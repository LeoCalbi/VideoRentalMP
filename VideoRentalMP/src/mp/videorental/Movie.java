package mp.videorental;
import java.time.LocalDate;

public class Movie {
	
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
	
}
