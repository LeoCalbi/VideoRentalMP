package mp.videorental.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import mp.videorental.*;

public class CredentialsTest {
	
	Credentials credentials;
	
	@Before
	public void initFixture() {
		credentials = new Credentials("user", "pass");
	}
	
	@Test
	public void testEquals() {
		Credentials t = new Credentials("user", "pass");
		Credentials f1 = new Credentials("username", "password");
		Credentials f2 = new Credentials("user", "password");
		Credentials f3 = new Credentials("username", "pass");
		assertTrue(credentials.equals(t));
		assertFalse(credentials.equals(f1));
		assertFalse(credentials.equals(f2));
		assertFalse(credentials.equals(f3));
	}

}
