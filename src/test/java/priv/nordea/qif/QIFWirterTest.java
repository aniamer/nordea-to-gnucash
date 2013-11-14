package priv.nordea.qif;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

public class QIFWirterTest {
	QIFWirter qifWriter;
	@Before
	public void setUp() throws Exception {
		qifWriter = new QIFWirter(new OutputStreamWriter(System.out));
	}

	@Test
	public void testWriteDate() throws Exception {
		assertThat(qifWriter.writeDate("20130810"), is("D20130810\n"));
	}
	@Test
	public void testWritePayee() throws Exception {
		assertThat(qifWriter.writePayee("ICA"), is("PICA\n"));
	}
	@Test
	public void testWriteAmount() throws Exception {
		assertThat(qifWriter.writeAmount(21789d), is("T21789.0\n"));
	}
}
