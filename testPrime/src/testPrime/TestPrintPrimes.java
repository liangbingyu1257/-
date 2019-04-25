package testPrime;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;
import prime.PrintPrimes;

public class TestPrintPrimes {

	 PrintStream console = null;          
	 ByteArrayOutputStream bytes = null;  

	  @Before
	  public void setUp() throws Exception {
	    bytes = new ByteArrayOutputStream();   
	    console = System.out;                 
	    System.setOut(new PrintStream(bytes)); 
		}
		
		@Test
	    public void testResult() throws Exception {
	        PrintPrimes.printPrimes(3);
	        String s = new String("Prime: 2\nPrime: 3\nPrime: 5\n");
	        assertEquals(s, bytes.toString());          
		}
		
	    @After
	    public void tearDown() throws Exception {
	        
	        System.setOut(console);
	    }

	    

}

