package mp.videorental;

public class MovieBoxRepository extends Repository<MovieBox> {
	
	private static final long serialVersionUID = -6961856170029339089L;
	private static MovieBoxRepository instance;
	
	private MovieBoxRepository() {
		super();
	}
	
	public static MovieBoxRepository getInstance() {
		if(instance == null) {
			MovieBoxRepository repo = StorableHandler.getInstance().readMovieBox();
			if(repo == null) instance = new MovieBoxRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() {
		StorableHandler.getInstance().writeMovieBox();
	}

}