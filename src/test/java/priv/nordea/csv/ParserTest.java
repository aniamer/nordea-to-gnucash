package priv.nordea.csv;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
	private final String row="Kortk�p 130506 Pressbyran 8457 Fars";
	private Pattern pattern = Pattern.compile("\\s");
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseShop() {
		String[] splits = pattern.split(row);
		assertThat(splits[2], is("Pressbyran"));
		
	}

}
