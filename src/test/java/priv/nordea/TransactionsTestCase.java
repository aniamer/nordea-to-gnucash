package priv.nordea;

import java.util.List;

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
		List<String> result = transHome.findMaxDate();
		for (String maxDate : result) {
			System.out.println(maxDate);
		}
	}
}
