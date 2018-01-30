package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DirectorRepository extends Repository<Director> {
	
	private static final long serialVersionUID = -4949479449160956792L;
	private static DirectorRepository instance = new DirectorRepository();
	
	private DirectorRepository() {}
	
	public DirectorRepository(DirectorRepository instance) {
		DirectorRepository.instance=instance;
	}
	
	public static DirectorRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		 if(instance == null) StorableHandler.getInstance().read();
		 return instance;
	}

}