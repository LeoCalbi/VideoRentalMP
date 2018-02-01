package mp.videorental;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorableHandler {
	
	private static StorableHandler instance;
	
	private StorableHandler() {}

	public static StorableHandler getInstance() {
		if(instance == null) instance = new StorableHandler();
		return instance;
	}

	public DirectorRepository readDirector() {
		DirectorRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Director"))){
			repo = (DirectorRepository) in.readObject();
		} catch(IOException | ClassNotFoundException e) { repo = null; }
		return repo;
	}

	public GenreRepository readGenre() {
		GenreRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Genre"))){
			repo = (GenreRepository) in.readObject();
		} catch(IOException | ClassNotFoundException e) { repo = null; }
		return repo;
	}

	public AdministratorRepository readAdministrator() {
		AdministratorRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Administrator"))){
			repo = (AdministratorRepository) in.readObject();
		} catch(IOException | ClassNotFoundException e) { repo = null; }
		return repo;
	}

	public CustomerRepository readCustomer() {
		CustomerRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Customer"))){
			repo = (CustomerRepository) in.readObject();
		} catch(IOException | ClassNotFoundException e) { repo = null; }
		return repo;
	}

	public MovieRepository readMovie() {
		MovieRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/Movie"))){
			repo = (MovieRepository) in.readObject();
		} catch(IOException | ClassNotFoundException e) { repo = null; }
		return repo;
	}

	public MovieBoxRepository readMovieBox() {
		MovieBoxRepository repo;
		try (ObjectInputStream in = new ObjectInputStream (new FileInputStream("data/MovieBox"))){
			repo = (MovieBoxRepository) in.readObject();
		} catch(IOException | ClassNotFoundException e) { repo = null; }
		return repo;
	}

	public void writeDirector() {
		try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/Director", false))) {
			out.writeObject(DirectorRepository.getInstance());
			out.flush();
			out.close();
		} catch (IOException e) {}
	}

	public void writeGenre() {
		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/Genre", false))) {
			out.writeObject(GenreRepository.getInstance());
			out.flush();
			out.close();
		} catch (IOException e) {}
	}

	public void writeAdministrator() {
		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/Administrator", false))) {
			out.writeObject(AdministratorRepository.getInstance());
			out.flush();
			out.close();
		} catch (IOException e) {}
	}

	public void writeCustomer() {
		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/Customer", false))) {
			out.writeObject(CustomerRepository.getInstance());
			out.flush();
			out.close();
		} catch (IOException e) {}
	}

	public void writeMovie() {
		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/Movie", false))) {
			out.writeObject(MovieRepository.getInstance());
			out.flush();
			out.close();
		} catch (IOException e) {}
	}

	public void writeMovieBox() {
		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/MovieBox", false))) {
			out.writeObject(MovieBoxRepository.getInstance());
			out.flush();
			out.close();
		} catch (IOException e) {}
	}
	
}
