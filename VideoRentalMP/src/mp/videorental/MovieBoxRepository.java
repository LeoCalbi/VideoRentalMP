package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

@SuppressWarnings("serial")
public class MovieBoxRepository extends Repository<MovieBox> {
	
	private static MovieBoxRepository instance = new MovieBoxRepository();
	
	private MovieBoxRepository() {}
	
	public MovieBoxRepository(MovieBoxRepository instance) {
		MovieBoxRepository.instance=instance;
	}
	
	public static MovieBoxRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null) throw new RepositoryNotInitializedException();
		 return instance;
	}

}