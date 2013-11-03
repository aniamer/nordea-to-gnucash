package priv.nordea.db.hib;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class XAccountShopHomeTest {
	private XAccountShopHome accountShopDao;
	@Before
	public void setUp() throws Exception {
		accountShopDao = new XAccountShopHome();
	}

	@Test
	public void testFindByAccountName() {
		List<XAccountShop> accountShop = accountShopDao.findByAccountName("ComHem");
		assertThat(accountShop.size(), is(1));
		assertThat(accountShop.get(0).getShopName(),is("COMHEM"));

	}

}
