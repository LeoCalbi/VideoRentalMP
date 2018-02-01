package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DirectorRepository extends Repository<Director> {
	
	private static final long serialVersionUID = -4949479449160956792L;
	private static DirectorRepository instance;
	
	private DirectorRepository() {
		super();
	}
	
	public static DirectorRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(instance == null) {
			DirectorRepository repo = StorableHandler.getInstance().readDirector();
			if(repo == null) instance = new DirectorRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() throws FileNotFoundException, ClassNotFoundException, IOException {
		StorableHandler.getInstance().writeDirector();
	}


}