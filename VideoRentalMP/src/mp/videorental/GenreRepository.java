package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GenreRepository extends Repository<Genre> {
	
	private static final long serialVersionUID = 6271621469646463436L;
	private static GenreRepository instance = new GenreRepository();
	
	private GenreRepository() {}
	
	public GenreRepository(GenreRepository instance) {
		GenreRepository.instance=instance;
	}
	
	public static GenreRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		 if(instance == null) StorableHandler.getInstance().read();
		 return instance;
	}

}