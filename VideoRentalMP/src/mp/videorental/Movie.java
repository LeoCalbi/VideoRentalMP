package mp.videorental;
import java.time.LocalDate;

public class Movie {
	
	private String title;
	private Director director;
	private Genre genre;
	private LocalDate release;
	
	public Movie(String title, Director director, Genre genre, LocalDate release) {
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.release = release;
	}
	
}
