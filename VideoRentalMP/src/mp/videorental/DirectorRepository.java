package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

@SuppressWarnings("serial")
public class DirectorRepository extends Repository<Director> {
	
	private static DirectorRepository instance = new DirectorRepository();
	
	private DirectorRepository() {}
	
	public DirectorRepository(DirectorRepository instance) {
		DirectorRepository.instance=instance;
	}
	
	public static DirectorRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null) throw new RepositoryNotInitializedException();
		 return instance;
	}

}