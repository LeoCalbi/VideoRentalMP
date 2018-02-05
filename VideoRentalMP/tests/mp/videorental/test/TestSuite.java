package mp.videorental.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  SeniorCustomerTest.class,
  StandardCustomerTest.class,
  StudentCustomerTest.class,
  SeniorCardTest.class,
  StandardCardTest.class,
  StudentCardTest.class,
  CredentialsTest.class,
  NewReleaseSurchargeTest.class,
  Over10DaysDiscountTest.class,
  RentPriceStrategyCompoundTest.class,
  BDTest.class,
  DVDTest.class,
  CartTest.class,
  RentedTest.class,
  ComplexRentTest.class,
  SimpleRentTest.class,
  AdministratorRepositoryTest.class,
  CustomerRepositoryTest.class,
  DirectorRepositoryTest.class,
  GenreRepositoryTest.class,
  MovieBoxRepositoryTest.class,
  MovieRepositoryTest.class
})

public class TestSuite {}
