package priv.nordea.csv;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyParser {
	
	public static Double parse(String source, Locale locale){
		//-- default new Locale("de", "DE")
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		Double parseResult=null;
		if(!source.isEmpty() && source!=null){
			try {
				 parseResult=nf.parse(source).doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return parseResult;
			
		}
		else
			return null;
		
	}
}
