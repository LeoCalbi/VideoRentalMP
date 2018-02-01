package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MovieRepository extends Repository<Movie> {
	
	private static final long serialVersionUID = -2384158971984703576L;
	private static MovieRepository instance;
	
	private MovieRepository() {
		super();
	}
	
	public static MovieRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(instance == null) {
			MovieRepository repo = StorableHandler.getInstance().readMovie();
			if(repo == null) instance = new MovieRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() throws FileNotFoundException, ClassNotFoundException, IOException {
		StorableHandler.getInstance().writeMovie();
	}


}