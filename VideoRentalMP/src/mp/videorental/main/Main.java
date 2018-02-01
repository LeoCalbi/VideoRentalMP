package mp.videorental.main;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
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
		Customer customer;
		Credentials customerCredentials;
		System.out.println("LOGIN\n1) Admin\n2) Customer\n");
		int loginChoice = in.nextInt();
		switch(loginChoice) {
			case 1:
				Administrator admin, tempAdmin;
				Credentials adminCredentials = askCredentials();
				try {
					admin = administratorRepository.stream().filter((Administrator a) -> a.access(adminCredentials)).findFirst().get();
					System.out.println("1) Administrator\n2) Customer\n3) Movie\n4) MovieBox");
					int adminChoice = in.nextInt();
					switch(adminChoice) {
						case 1:
							System.out.println(administratorRepository.toString());
							System.out.println("1) Insert\n2) Remove");
							adminChoice = in.nextInt();
							switch(adminChoice) {
								case 1:
									Credentials newAdminCredentials = askCredentials();
									tempAdmin = newAdministrator(newAdminCredentials);
									tempAdmin.add(admin);
									break;
								case 2:
									try {
										System.out.println("Insert the social security number of the administrator you want to remove: ");
										String socialSecurityNumber = in.next();
										tempAdmin = administratorRepository.stream().filter((Administrator a) -> a.getSocialSecurityNumber().equals(socialSecurityNumber)).findFirst().get();
										tempAdmin.remove(admin);
									} catch(NoSuchElementException e) {
										System.out.println("Wrong social security number. Try again.");
									}
									break;
								default:
									System.out.println("Insertion error.");
									break;
							}
							break;
						case 2:
							
							customerCredentials = askCredentials();
							customer = newCustomer(customerCredentials);
							customer.add(admin);
							
							break;
						case 3:
							break;
						case 4:
							break;
						default:
							System.out.println("Insertion error.");
							break;
					}
				} catch(NoSuchElementException e) {
					System.out.println("Wrong credentials. Try again.");
				} catch (InvalidAdminException e) {
					System.out.println("The admin is not a valid admin.");
				} catch (StorableAlreadyPresentException e) {
				} catch (StorableNotPresentException e) {}
				break;
			case 2:
				customerCredentials = askCredentials();
				try {
					customer = customerRepository.stream().filter((Customer c) -> c.access(customerCredentials)).findFirst().get();
					Cart cart = new Cart(customer);
					System.out.println("1) Rent\n2) Restitution\n3) Cart\n4) Card\n");
					int customerChoice = in.nextInt();
					switch(customerChoice) {
						case 1:
							System.out.println(movieRepository.toString());
							System.out.println("Insert the serial number of the movie: ");
							int movieSerialNumber = in.nextInt();
							Movie movie;
							MovieBox movieBox;
							try {
								movie = movieRepository.stream().filter((Movie m) -> m.getSerialNumber().equals(movieSerialNumber)).findFirst().get();
								movieBoxRepository.stream().filter((MovieBox m) -> m.compareMovie(movie) && m.isAvailable()).forEach(MovieBox::toString);
								System.out.println("Insert the serial number of the movie box: ");
								int movieBoxSerialNumber = in.nextInt();
								movieBox = movieBoxRepository.stream().filter((MovieBox m) -> m.getSerialNumber().equals(movieBoxSerialNumber)).findFirst().get();
								System.out.println("How many days: ");
								int days = in.nextInt();
								RentPriceStrategy strategy = new RentPriceStrategyCompound();
								if(days > 10) strategy.add(new Over10DaysDiscount());
								if(ChronoUnit.DAYS.between(movie.getReleaseDate(), LocalDate.now()) < 15) strategy.add(new NewReleaseSurcharge());
								Rent rent = movieBox.rent(days, strategy);
								cart.add(rent);
							} catch(NoSuchElementException e) {
								System.out.println("Wrong serial number. Try again.");
							} catch (AddToLeafCompositeException e) {
							} catch (AlreadyRentedException e) {
								System.out.println("The movie box is not available.");
							} catch(MaximumRentedItemsException e) {
								System.out.println("You reached the maximum number of rented items.");
							}
							break;
						case 2:
							if(customer.getRentedSize() > 0) {
								try {
									System.out.println(customer.getRentedDescription());
									System.out.println("Insert the serial number of the movie box: ");
									int movieBoxSerialNumber = in.nextInt();
									movieBox = movieBoxRepository.stream().filter((MovieBox m) -> m.getSerialNumber().equals(movieBoxSerialNumber)).findFirst().get();
									movieBox.restitution();
									System.out.println("The " + movieBox.getClass().getSimpleName() + " was correctly returned.");
								} catch(NoSuchElementException e) {
									System.out.println("Wrong serial number. Try again.");
								} catch(NotRentedException e) {
									System.out.println("The movie box is not rented. You can't return it.");
								}
							} else System.out.println("The rented list is empty.");
							break;
						case 3:
							if(cart.getCartSize() > 0) {
								System.out.println(cart.toString());
								System.out.println("1) Remove\n2) Pay");
								int cartChoice = in.nextInt();
								switch(cartChoice) {
									case 1:
										try {
											System.out.println("Insert the serial number of the movie box: ");
											int movieBoxSerialNumber = in.nextInt();
											Iterator<Rent> it = cart.getIterator();
											Rent r = null;
											while(it.hasNext()) {
												Rent a = it.next();
												if(a.getRentableSerialNumber().equals(movieBoxSerialNumber)) r = a;
											}
											cart.remove(r);
										} catch(AbsentRentException e) {
											System.out.println("Wrong serial number. Try again.");
										} catch (EmptyRentListException e) {}
										break;
									case 2:
										try {
											cart.pay();
											System.out.println("Items rented succesfully.");
										} catch (InsufficientFundsException e) {
											System.out.println("Not enough credit. Please deposit on your card.");
										}
										break;
									default:
										System.out.println("Insertion error.");
										break;
								}
							} else System.out.println("The cart is empty.");
							break;
						case 4:
							System.out.println("Balance: " + customer.getCardBalance() + ", Points: " + customer.getCardPoints());
							System.out.println("1) Deposit");
							int cardChoice = in.nextInt();
							switch(cardChoice) {
								case 1:
									try {
										System.out.println("Insert the amount you want to deposit: ");
										customer.depositOnCard(in.nextDouble());
										System.out.println("Credit succesfully deposited. New balance: " + customer.getCardBalance());
									} catch(NegativeAmountException e) {
										System.out.println("The amount you inserted is a negative number. Try again.");
									}
									break;
								default:
									System.out.println("Insertion error.");
									break;
							}
							break;
						default:
							System.out.println("Insertion error.");
							break;
					} 
				} catch(NoSuchElementException e) {
					System.out.println("Wrong credentials. Try again.");
				}
				break;
			default:
				System.out.println("Insertion error.");
				break;
		}
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
		System.out.println("Address: ");
		String address = in.next();
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
