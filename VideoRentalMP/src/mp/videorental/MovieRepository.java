package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

@SuppressWarnings("serial")
public class MovieRepository extends Repository<Movie> {
	
	private static MovieRepository instance = new MovieRepository();
	
	private MovieRepository() {}
	
	public MovieRepository(MovieRepository instance) {
		MovieRepository.instance=instance;
	}
	
	public static MovieRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null) throw new RepositoryNotInitializedException();
		 return instance;
	}

}