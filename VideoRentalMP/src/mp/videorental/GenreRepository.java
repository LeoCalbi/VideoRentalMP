package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GenreRepository extends Repository<Genre> {
	
	private static final long serialVersionUID = 6271621469646463436L;
	private static GenreRepository instance;
	
	private GenreRepository() {
		super();
	}
	
	public static GenreRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(instance == null) {
			GenreRepository repo = StorableHandler.getInstance().readGenre();
			if(repo == null) instance = new GenreRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() throws FileNotFoundException, ClassNotFoundException, IOException {
		StorableHandler.getInstance().writeGenre();
	}


}