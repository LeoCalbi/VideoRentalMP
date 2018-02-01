package mp.videorental;

public class DirectorRepository extends Repository<Director> {
	
	private static final long serialVersionUID = -4949479449160956792L;
	private static DirectorRepository instance;
	
	private DirectorRepository() {
		super();
	}
	
	public static DirectorRepository getInstance() {
		if(instance == null) {
			DirectorRepository repo = StorableHandler.getInstance().readDirector();
			if(repo == null) instance = new DirectorRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() {
		StorableHandler.getInstance().writeDirector();
	}


}