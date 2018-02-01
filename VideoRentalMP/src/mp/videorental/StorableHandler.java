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
	private ObjectOutputStream out;
	
	private StorableHandler() {}

	public static StorableHandler getInstance() {
		if(instance == null) instance = new StorableHandler();
		return instance;
	}
	
	public void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		readMovieBox();
		readMovie();
		readCustomer();
		readAdministrator();
		readGenre();
		readDirector();
	}

	public DirectorRepository readDirector() throws IOException, FileNotFoundException, ClassNotFoundException {
		DirectorRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Director"))){
			repo = (DirectorRepository) in.readObject();
			System.out.println(repo.toString());
		} catch(EOFException e) {repo = null;}
		return repo;
	}

	public GenreRepository readGenre() throws IOException, FileNotFoundException, ClassNotFoundException {
		GenreRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Genre"))){
			repo = (GenreRepository) in.readObject();
			System.out.println(repo.toString());
		} catch(EOFException e) {repo = null;}
		return repo;
	}

	public AdministratorRepository readAdministrator() throws IOException, FileNotFoundException, ClassNotFoundException {
		AdministratorRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Administrator"))){
			repo = (AdministratorRepository) in.readObject();
			System.out.println(repo.toString());
		} catch(EOFException e) {repo = null;}
		return repo;
	}

	public CustomerRepository readCustomer() throws IOException, FileNotFoundException, ClassNotFoundException {
		CustomerRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Customer"))){
			repo = (CustomerRepository) in.readObject();
			System.out.println(repo.toString());
		} catch(EOFException e) { repo = null;}
		return repo;
	}

	public MovieRepository readMovie() throws IOException, FileNotFoundException, ClassNotFoundException {
		MovieRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Movie"))){
			repo = (MovieRepository) in.readObject();
			System.out.println(repo.toString());
		} catch(EOFException e) { repo = null;}
		return repo;
	}

	public MovieBoxRepository readMovieBox() throws IOException, FileNotFoundException, ClassNotFoundException {
		MovieBoxRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/MovieBox"))){
			repo = (MovieBoxRepository) in.readObject();
			System.out.println(repo.toString());
		} catch(EOFException e) { repo = null;}
		return repo;
	}
	
	public void write() throws FileNotFoundException, IOException, ClassNotFoundException {
		writeMovieBox();
		writeMovie();
		writeCustomer();
		writeAdministrator();
		writeGenre();
		writeDirector();
	}

	public void writeDirector() throws IOException, FileNotFoundException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/Director",false));
		out.writeObject(DirectorRepository.getInstance());
		out.close();
	}

	public void writeGenre() throws IOException, FileNotFoundException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/Genre",false));
		out.writeObject(GenreRepository.getInstance());
		out.close();
	}

	public void writeAdministrator() throws IOException, FileNotFoundException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/Administrator",false));
		out.writeObject(AdministratorRepository.getInstance());
		out.close();
	}

	public void writeCustomer() throws IOException, FileNotFoundException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/Customer",false));
		out.writeObject(CustomerRepository.getInstance());
		out.close();
	}

	public void writeMovie() throws IOException, FileNotFoundException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/Movie",false));
		out.writeObject(MovieRepository.getInstance());
		out.close();
	}

	public void writeMovieBox() throws IOException, FileNotFoundException, ClassNotFoundException {
		out = new ObjectOutputStream (new FileOutputStream("data/MovieBox",false));
		out.writeObject(MovieBoxRepository.getInstance());
		out.close();
	}
	
}
