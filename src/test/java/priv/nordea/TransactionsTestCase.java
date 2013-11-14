package priv.nordea;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import priv.nordea.db.hib.Accounts;
import priv.nordea.db.hib.Commodities;
import priv.nordea.db.hib.CommoditiesHome;
import priv.nordea.db.hib.Splits;
import priv.nordea.db.hib.SplitsHome;
import priv.nordea.db.hib.Transactions;
import priv.nordea.db.hib.TransactionsHome;
import priv.nordea.db.hib.util.GNUCashGUID;

public class TransactionsTestCase {
	private TransactionsHome transHome;
	
	@Before
	public void setUp() throws Exception {
		
//		transHome = new TransactionsHome();
	}

	@Test
	public void testFindAfterDate(){
		String result = transHome.findMaxDate().get(0);
		System.out.println(result.substring(0, 8));
	}
	@Test
	public void testTransactionInsert() throws Exception {
		Commodities sekCurr = new Commodities();
		sekCurr.setMnemonic("SEK");
		sekCurr.setQuoteFlag(1);
		sekCurr.setFraction(100);
		List<Commodities> comodietis = new CommoditiesHome().findByExample(sekCurr);
		String currGuid = comodietis.get(0).getGuid();
		
		Transactions transaction = new Transactions();
		transaction.setCurrencyGuid(currGuid);
		transaction.setDescription("test transaction");
		transaction.setEnterDate("20131106113200");

		transaction.setGuid(GNUCashGUID.randomGUId());
		transaction.setNum("");
		transaction.setPostDate("20131106113200");
		
//		transaction.setSplits(splits);
		transHome.persist(transaction);
		transHome.insertTransction(new Accounts(),transaction);
	}
	@Test
	public void testRandomGUID() throws Exception {
		GNUCashGUID.randomGUId();
		System.out.println();
		GNUCashGUID.randomGUId();
		System.out.println();
		GNUCashGUID.randomGUId();
	}

}
