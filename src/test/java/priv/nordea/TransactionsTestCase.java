package priv.nordea;

import java.util.List;

import priv.nordea.db.hib.Transactions;
import priv.nordea.db.hib.TransactionsHome;
import junit.framework.TestCase;

public class TransactionsTestCase extends TestCase {
	private TransactionsHome transHome;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		transHome = new TransactionsHome();
	}

	public void testFindAfterDate(){
		List<String> result = transHome.findMaxDate();
		for (String maxDate : result) {
			System.out.println(maxDate);
		}
	}
}
