package mp.videorental;

public class MovieRepository extends Repository<Movie> {
	
	private static final long serialVersionUID = -2384158971984703576L;
	private static MovieRepository instance;
	
	private MovieRepository() {
		super();
	}
	
	public static MovieRepository getInstance() {
		if(instance == null) {
			MovieRepository repo = StorableHandler.getInstance().readMovie();
			if(repo == null) instance = new MovieRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() {
		StorableHandler.getInstance().writeMovie();
	}

}