package mp.videorental.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import mp.videorental.NewReleaseSurcharge;
import mp.videorental.Over10DaysDiscount;

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
  RentPriceStrategyCompoundTest.class
})

public class TestSuite {}
