package priv.nordea;

import org.junit.Before;
import org.junit.Test;

import priv.nordea.db.hib.TransactionsHome;

public class TransactionsTestCase {
	private TransactionsHome transHome;
	@Before
	public void setUp() throws Exception {
		
		transHome = new TransactionsHome();
	}

	@Test
	public void testFindAfterDate(){
		String result = transHome.findMaxDate().get(0);
		System.out.println(result.substring(0, 8));
	}
}
