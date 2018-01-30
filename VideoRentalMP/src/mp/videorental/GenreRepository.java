package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

@SuppressWarnings("serial")
public class GenreRepository extends Repository<Genre> {
	
	private static GenreRepository instance = new GenreRepository();
	
	private GenreRepository() {}
	
	public GenreRepository(GenreRepository instance) {
		GenreRepository.instance=instance;
	}
	
	public static GenreRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null) throw new RepositoryNotInitializedException();
		 return instance;
	}

}