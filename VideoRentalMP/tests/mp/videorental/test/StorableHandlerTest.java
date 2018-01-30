package mp.videorental.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import mp.videorental.StorableHandler;

public class StorableHandlerTest {
	
	private StorableHandler storableHandler = StorableHandler.getInstance();
	
	@Test
	public void testRead() throws FileNotFoundException, IOException, ClassNotFoundException {
		storableHandler.read();
	}
	
	@Test
	public void testWrite() throws FileNotFoundException, IOException {
		storableHandler.write();
	}

}
