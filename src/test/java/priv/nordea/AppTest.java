package priv.nordea;

import static org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    @org.junit.Test
	public void testSplit() throws Exception {
    	String path = "app.csv";
    	String[] split = path.split("\\.");
    	for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
    	System.out.println("DONE");
		
	}
}
