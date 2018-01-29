package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

public class MovieBoxRepository extends Repository<MovieBox> {
	private static MovieBoxRepository instance = new MovieBoxRepository();
	
	public MovieBoxRepository(MovieBoxRepository instance) {
		MovieBoxRepository.instance=instance;
	}
	
	private MovieBoxRepository() {
		
	}
	
	public static MovieBoxRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null)
			 throw new RepositoryNotInitializedException();
		 return instance;
	}

}
