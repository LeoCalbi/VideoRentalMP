package mp.videorental;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorableHandler {
	private static StorableHandler instance;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private StorableHandler() {
		
	}

	public static StorableHandler getInstance() {
		if(instance == null)
			instance = new StorableHandler();
		return instance;
	}
	
	public void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		 in = new ObjectInputStream (new FileInputStream("data/MovieBox"));
		 new MovieBoxRepository((MovieBoxRepository) in.readObject());
		 in.close();
	}
	
	public void write() throws FileNotFoundException, IOException {
		out = new ObjectOutputStream (new FileOutputStream("data/MovieBox",false));
		out.writeObject(MovieBoxRepository.getInstance());
		out.close();
	}
}
