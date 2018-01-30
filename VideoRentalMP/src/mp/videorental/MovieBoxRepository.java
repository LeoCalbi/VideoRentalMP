package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MovieBoxRepository extends Repository<MovieBox> {
	
	private static final long serialVersionUID = -6961856170029339089L;
	private static MovieBoxRepository instance = new MovieBoxRepository();
	
	private MovieBoxRepository() {}
	
	public MovieBoxRepository(MovieBoxRepository instance) {
		MovieBoxRepository.instance = instance;
	}
	
	public static MovieBoxRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		 if(instance == null) StorableHandler.getInstance().read();
		 return instance;
	}

}