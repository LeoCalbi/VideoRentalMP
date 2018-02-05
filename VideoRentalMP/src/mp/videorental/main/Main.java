package mp.videorental.main;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import mp.videorental.*;
import mp.videorental.exception.*;
import java.time.temporal.ChronoUnit;

public class Main {
	
	private static AdministratorRepository administratorRepository = AdministratorRepository.getInstance();
	private static CustomerRepository customerRepository = CustomerRepository.getInstance();
	private static DirectorRepository directorRepository = DirectorRepository.getInstance();
	private static GenreRepository genreRepository = GenreRepository.getInstance();
	private static MovieBoxRepository movieBoxRepository = MovieBoxRepository.getInstance();
	private static MovieRepository movieRepository = MovieRepository.getInstance();
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		int loginChoice;
		do {
			System.out.println("LOGIN\n1) Admin\n2) Customer\n3) Exit\n");
			loginChoice = in.nextInt();
			switch(loginChoice) {
				case 1:
					adminSide();
					break;
				case 2:
					customerSide();
					break;
				case 3:
					break;
				default:
					System.out.println("Insertion error.");
					break;
			}
		} while(loginChoice != 3);
	}

	private static void customerSide() {
		Credentials customerCredentials = askCredentials();
		int customerChoice = 5;
		try {
			Customer customer = customerRepository.stream().filter((Customer c) -> c.access(customerCredentials)).findFirst().get();
			Cart cart = new Cart(customer);
			do {
				
				System.out.println("1) Rent\n2) Restitution\n3) Cart\n4) Card\n5) Exit\n");
				customerChoice = in.nextInt();
				switch(customerChoice) {
					case 1:
						if(movieRepository.getSize() > 0) {
							Rent rent = newRent();
							System.out.println(rent.toString());
							if(rent != null) cart.add(rent);
						} else System.out.println("No movie available");
						break;
					case 2:
						newRestitution(customer);
						break;
					case 3:
						cartManager(cart);
						break;
					case 4:
						cardManager(customer);
						break;
					case 5:
						break;
					default:
						System.out.println("Insertion error.");
						break;
				}
			} while(customerChoice != 5);
		} catch(NoSuchElementException e) {
			System.out.println("Wrong input. Try again.");
		} catch (AddToLeafCompositeException e) {
			System.out.println(e.getMessage());
		} catch (AlreadyRentedException e) {
			System.out.println("The movie box is not available.");
		} catch(MaximumRentedItemsException e) {
			System.out.println("You reached the maximum number of rented items.");
		} catch(NotRentedException e) {
			System.out.println("The movie box is not rented. You can't return it.");
		}  catch(AbsentRentException e) {
			System.out.println("Wrong serial number. Try again.");
		} catch (EmptyRentListException e) {
			System.out.println("The rented list is empty.");
		} catch (InsufficientFundsException e) {
			System.out.println("Not enough credit. Please deposit on your card.");
		}  catch(NegativeAmountException e) {
			System.out.println("The amount you inserted is a negative number. Try again.");
		}
	}
	
	private static void adminSide() {
		Credentials adminCredentials = askCredentials();
		int adminChoice = 5;
		do {
			try {
				Administrator admin = administratorRepository.stream().filter((Administrator a) -> a.access(adminCredentials)).findFirst().get();
				System.out.println("1) Administrator\n2) Customer\n3) Movie\n4) MovieBox\n5) Exit\n");
				adminChoice = in.nextInt();
				switch(adminChoice) {
					case 1:
						adminManager(admin);
						break;
					case 2:
						customerManager(admin);
						break;
					case 3:
						movieManager(admin);
						break;
					case 4:
						movieBoxManager(admin);
						break;
					case 5:
						break;
					default:
						System.out.println("Insertion error.");
						break;
				}
			} catch(NoSuchElementException e) {
				System.out.println("Wrong input. Try again.");
			} catch (InvalidAdminException e) {
				System.out.println("The admin is not a valid admin.");
			} catch (StorableAlreadyPresentException e) {
				System.out.println(e.getMessage());
			} catch (StorableNotPresentException e) {
				System.out.println(e.getMessage());
			}
		} while(adminChoice != 5);
	}

	private static void cardManager(Customer customer) throws NegativeAmountException {
		System.out.println("Balance: " + customer.getCardBalance() + ", Points: " + customer.getCardPoints());
		int cardChoice;
		do {
			System.out.println("1) Deposit\n2) Exit");
			cardChoice = in.nextInt();
			switch(cardChoice) {
				case 1:
					System.out.println("Insert the amount you want to deposit: ");
					customer.depositOnCard(in.nextDouble());
					System.out.println("Credit succesfully deposited. New balance: " + customer.getCardBalance());
					break;
				case 2:
					break;
				default:
					System.out.println("Insertion error.");
					break;
			}
		} while(cardChoice != 2);
	}

	private static void cartManager(Cart cart) throws AbsentRentException, EmptyRentListException, InsufficientFundsException {
		int cartChoice = 3;
		do {
			if(cart.getCartSize() > 0) {
				System.out.println(cart.toString());
				System.out.println("1) Remove\n2) Pay\n3) Exit");
				cartChoice = in.nextInt();
				switch(cartChoice) {
					case 1:
						System.out.println("Insert the serial number of the movie box: ");
						int movieBoxSerialNumber = in.nextInt();
						Iterator<Rent> it = cart.getIterator();
						Rent r = null;
						while(it.hasNext()) {
							Rent a = it.next();
							if(a.getRentableSerialNumber().equals(movieBoxSerialNumber)) r = a;
						}
						cart.remove(r);
						System.out.println("Item succesfully removed.");
						break;
					case 2:
						cart.pay();
						System.out.println("Items succesfully rented.");
						break;
					case 3:
						break;
					default:
						System.out.println("Insertion error.");
						break;
				}
			} else System.out.println("The cart is empty.");
		} while(cartChoice != 3 && cart.getCartSize() > 0);
	}

	private static void newRestitution(Customer customer) throws NotRentedException {
		if(customer.getRentedSize() > 0) {
			System.out.println(customer.getRentedDescription());
			System.out.println("Insert the serial number of the movie box: ");
			int movieBoxSerialNumber = in.nextInt();
			MovieBox movieBox = movieBoxRepository.stream().filter((MovieBox m) -> m.getSerialNumber().equals(movieBoxSerialNumber)).findFirst().get();
			movieBox.restitution();
			System.out.println("The " + movieBox.getClass().getSimpleName() + " was correctly returned.");
		} else System.out.println("The rented list is empty.");
	}

	private static Rent newRent() throws AddToLeafCompositeException, AlreadyRentedException {
		System.out.println(movieRepository.toString());
		System.out.println("Insert the serial number of the movie: ");
		int movieSerialNumber = in.nextInt();
		Movie movie = movieRepository.stream().filter((Movie m) -> m.getSerialNumber().equals(movieSerialNumber)).findFirst().get();
		long countAvailableMovieBoxes = movieBoxRepository.stream().filter((MovieBox m) -> m.compareMovie(movie) && m.isAvailable()).count();
		System.out.println(countAvailableMovieBoxes);
		if(countAvailableMovieBoxes > 0) {
			Iterator<MovieBox> it = movieBoxRepository.stream().filter((MovieBox m) -> m.compareMovie(movie) && m.isAvailable()).iterator();
			while(it.hasNext()) System.out.println(it.next().toString());
			System.out.println("Insert the serial number of the movie box: ");
			int movieBoxSerialNumber = in.nextInt();
			MovieBox movieBox = movieBoxRepository.stream().filter((MovieBox m) -> m.getSerialNumber().equals(movieBoxSerialNumber)).findFirst().get();
			System.out.println("How many days: ");
			int days = in.nextInt();
			RentPriceStrategy strategy = new RentPriceStrategyCompound();
			if(days > 10) strategy.add(new Over10DaysDiscount());
			if(ChronoUnit.DAYS.between(movie.getReleaseDate(), LocalDate.now()) < 15) strategy.add(new NewReleaseSurcharge());
			return movieBox.rent(days, strategy);
		} else { 
			System.out.println("No movie boxes available for the selected movie.");
		}
		return null;
	}

	private static void movieBoxManager(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		int adminChoice;
		do {
			System.out.println(movieBoxRepository.toString());
			System.out.println("1) Insert\n2) Remove\n3) Exit\n");
			adminChoice = in.nextInt();
			MovieBox tempMovieBox;
			switch(adminChoice) {
				case 1:
					if(movieRepository.getSize() > 0) {
						tempMovieBox = newMovieBox();
						tempMovieBox.add(admin);
					} else System.out.println("No movie available.");
					break;
				case 2:
					System.out.println("Insert the serial number of the movie box you want to remove:");
					int serialNumber = in.nextInt();
					tempMovieBox = movieBoxRepository.stream().filter((MovieBox m) -> m.getSerialNumber().equals(serialNumber)).findFirst().get();
					tempMovieBox.remove(admin);
					System.out.println(tempMovieBox.getClass().getSimpleName() + " succesfully removed.");
					break;
				case 3:
					break;
				default:
					System.out.println("Insertion error.");
					break;
			}
		} while(adminChoice != 3);
	}

	private static void movieManager(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		int adminChoice;
		do {
			System.out.println(movieRepository.toString());
			System.out.println("1) Insert\n2) Remove\n3) Exit\n");
			adminChoice = in.nextInt();
			Movie tempMovie;
			switch(adminChoice) {
				case 1:
					tempMovie = newMovie(admin);
					tempMovie.add(admin);
					break;
				case 2:
					System.out.println("Insert the serial number of the movie you want to remove:");
					int serialNumber = in.nextInt();
					tempMovie = movieRepository.stream().filter((Movie c) -> c.getSerialNumber().equals(serialNumber)).findFirst().get();
					tempMovie.remove(admin);
					System.out.println("Movie succesfully removed.");
					break;
				case 3:
					break;
				default:
					System.out.println("Insertion error.");
					break;
			}
		} while(adminChoice != 3);
	}

	private static void customerManager(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		int adminChoice;
		do {
			System.out.println(customerRepository.toString());
			System.out.println("1) Insert\n2) Remove\n3) Exit\n");
			adminChoice = in.nextInt();
			Customer tempCustomer;
			switch(adminChoice) {
				case 1:
					Credentials newCustomerCredentials = askCredentials();
					tempCustomer = newCustomer(newCustomerCredentials);
					tempCustomer.add(admin);
					break;
				case 2:
					System.out.println("Insert the social security number of the customer you want to remove: ");
					String socialSecurityNumber = in.next();
					tempCustomer = customerRepository.stream().filter((Customer c) -> c.getSocialSecurityNumber().equals(socialSecurityNumber)).findFirst().get();
					tempCustomer.remove(admin);
					System.out.println(tempCustomer.getClass().getSimpleName() + " succesfully removed.");
					break;
				case 3:
					break;
				default:
					System.out.println("Insertion error.");
					break;
			}
		} while(adminChoice != 3);
	}

	private static void adminManager(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, StorableNotPresentException {
		int adminChoice;
		do {
			System.out.println(administratorRepository.toString());
			System.out.println("1) Insert\n2) Remove\n3) Exit\n");
			adminChoice = in.nextInt();
			Administrator tempAdmin;
			switch(adminChoice) {
				case 1:
					Credentials newAdminCredentials = askCredentials();
					tempAdmin = newAdministrator(newAdminCredentials);
					tempAdmin.add(admin);
					break;
				case 2:
					System.out.println("Insert the social security number of the administrator you want to remove: ");
					String socialSecurityNumber = in.next();
					tempAdmin = administratorRepository.stream().filter((Administrator a) -> a.getSocialSecurityNumber().equals(socialSecurityNumber)).findFirst().get();
					tempAdmin.remove(admin);
					System.out.println("Administrator succesfully removed.");
					break;
				case 3:
					break;
				default:
					System.out.println("Insertion error.");
					break;
			}
		} while(adminChoice != 3);
	}
	
	private static MovieBox newMovieBox() throws NoSuchElementException{
		Movie movie;
		System.out.println(movieRepository.toString());
		System.out.println("Insert the serial number of the movie you want to bind to the new movie box: ");
		int serialNumber = in.nextInt();
		movie = movieRepository.stream().filter((Movie c) -> c.getSerialNumber().equals(serialNumber)).findFirst().get();
		System.out.println("Daily price: ");
		double dailyPrice = in.nextDouble();
		System.out.println("DVD or Blu-Ray (0-1): ");
		int supportChoice = in.nextInt(); 
		if (supportChoice == 1) return new BD(movie,dailyPrice);
		else return new DVD(movie,dailyPrice);
	}

	private static Movie newMovie(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		Director director;
		in.nextLine();
		System.out.println("Title: ");
		String title = in.nextLine();
		System.out.println("Director name: ");
		String directorName = in.next();
		System.out.println("Director surname: ");
		String directorSurname = in.next();
		in.nextLine();
		System.out.println("Genre description: ");
		Genre genre = new Genre(in.nextLine());
		System.out.println("Release Date (yyyy-mm-dd): ");
		LocalDate releaseDate = LocalDate.parse(in.next());
		try {
			director = directorRepository.stream().filter((Director d) -> d.getName().equals(directorName) && d.getSurname().equals(directorSurname)).findFirst().get();
		} catch (NoSuchElementException e) {
			System.out.println("Adding new director: ");
			director = new Director(directorName,directorSurname);
			director.add(admin);
		}
		try {
			genreRepository.stream().filter((Genre g) -> g.equals(genre)).findFirst().get();
		} catch (NoSuchElementException e) {
			System.out.println("Adding new genre: ");
			genre.add(admin);
		}
		return new Movie(title,director,genre,releaseDate);
	}
	
	private static Credentials askCredentials() {
		System.out.println("Username: ");
		String username = in.next();
		System.out.println("Password: ");
		String password = in.next();
		return new Credentials(username, password);
	}
	
	private static Customer newCustomer(Credentials credentials) {
		System.out.println("Social security number: ");
		String socialSecurityNumber = in.next();
		System.out.println("Name: ");
		String name = in.next();
		System.out.println("Surname: ");
		String surname = in.next();
		System.out.println("Birthday (yyyy-mm-dd): ");
		LocalDate birthday = LocalDate.parse(in.next());
		in.nextLine();
		System.out.println("Address: ");
		String address = in.nextLine();
		System.out.println("Telephone: ");
		String telephone = in.next();
		int age = LocalDate.now().getYear() - birthday.getYear();
		if(age < 26) return new StudentCustomer(socialSecurityNumber, name, surname, birthday, address, telephone, credentials);
		else if(age > 60) return new SeniorCustomer(socialSecurityNumber, name, surname, birthday, address, telephone, credentials);
		else return new StandardCustomer(socialSecurityNumber, name, surname, birthday, address, telephone, credentials);
	}
	
	private static Administrator newAdministrator(Credentials credentials) {
		System.out.println("Social security number: ");
		String socialSecurityNumber = in.next();
		System.out.println("Name: ");
		String name = in.next();
		System.out.println("Surname: ");
		String surname = in.next();
		System.out.println("Birthday (yyyy-mm-dd): ");
		LocalDate birthday = LocalDate.parse(in.next());
		return new Administrator(socialSecurityNumber, name, surname, birthday, credentials);
	}

}
