package priv.nordea.csv.regex;

public class RegExTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String pattern ="([0-9]*)\\.([0-9]*),([0-9]*)";
		String expression="-350,00";
		String result = "";
		result=expression.replaceAll(pattern, "$1,$2.$3");
		
		System.out.println(result);

	}

}
