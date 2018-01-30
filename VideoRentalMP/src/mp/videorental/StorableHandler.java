package mp.videorental;

import java.io.EOFException;
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
	
	private StorableHandler() {}

	public static StorableHandler getInstance() {
		if(instance == null) instance = new StorableHandler();
		return instance;
	}
	
	public void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			in = new ObjectInputStream (new FileInputStream("data/MovieBox"));
			new MovieBoxRepository((MovieBoxRepository) in.readObject());
			in.close();
		} catch(EOFException e) {}
		try {
			in = new ObjectInputStream (new FileInputStream("data/Movie"));
			new MovieRepository((MovieRepository) in.readObject());
			in.close();
		} catch(EOFException e) {}
		try {
			in = new ObjectInputStream (new FileInputStream("data/Customer"));
			new CustomerRepository((CustomerRepository) in.readObject());
			in.close();
		} catch(EOFException e) {}
		try {
			in = new ObjectInputStream (new FileInputStream("data/Administrator"));
			new AdministratorRepository((AdministratorRepository) in.readObject());
			in.close();
		} catch(EOFException e) {}
		try {
			in = new ObjectInputStream (new FileInputStream("data/Genre"));
			new GenreRepository((GenreRepository) in.readObject());
			in.close();
		} catch(EOFException e) {}
		try {
			in = new ObjectInputStream (new FileInputStream("data/Director"));
			new DirectorRepository((DirectorRepository) in.readObject());
			in.close();
		} catch(EOFException e) {}
	}
	
	public void write() throws FileNotFoundException, IOException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/MovieBox",false));
		out.writeObject(MovieBoxRepository.getInstance());
		out.close();
		out = new ObjectOutputStream (new FileOutputStream("data/Movie",false));
		out.writeObject(MovieRepository.getInstance());
		out.close();
		out = new ObjectOutputStream (new FileOutputStream("data/Customer",false));
		out.writeObject(CustomerRepository.getInstance());
		out.close();
		out = new ObjectOutputStream (new FileOutputStream("data/Administrator",false));
		out.writeObject(AdministratorRepository.getInstance());
		out.close();
		out = new ObjectOutputStream (new FileOutputStream("data/Genre",false));
		out.writeObject(GenreRepository.getInstance());
		out.close();
		out = new ObjectOutputStream (new FileOutputStream("data/Director",false));
		out.writeObject(DirectorRepository.getInstance());
		out.close();
	}
	
}
