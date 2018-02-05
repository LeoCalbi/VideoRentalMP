package mp.videorental;

public class GenreRepository extends Repository<Genre> {
	
	private static final long serialVersionUID = 6271621469646463436L;
	private static GenreRepository instance;
	
	private GenreRepository() {
		super();
	}
	
	public static GenreRepository getInstance() {
		if(instance == null) {
			GenreRepository repo = StorableHandler.getInstance().readGenre();
			if(repo == null) instance = new GenreRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() {
		StorableHandler.getInstance().writeGenre();
	}

}