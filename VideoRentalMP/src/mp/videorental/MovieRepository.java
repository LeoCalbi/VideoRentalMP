package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MovieRepository extends Repository<Movie> {
	
	private static final long serialVersionUID = -2384158971984703576L;
	private static MovieRepository instance = new MovieRepository();
	
	private MovieRepository() {}
	
	public MovieRepository(MovieRepository instance) {
		MovieRepository.instance=instance;
	}
	
	public static MovieRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		 if(instance == null) StorableHandler.getInstance().read();
		 return instance;
	}

}