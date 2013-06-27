package priv.nordea.csv;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Nordea2GnuCashConverter implements INordeaCSVConverter<GnuCashCsvRow,NordeaCSVRow> {

	@Override
	public GnuCashCsvRow parseNordeaRow(NordeaCSVRow row) {
		Double amountValue = CurrencyParser.parse(row.getAmount(), new Locale("de", "DE"));
		GnuCashCsvRow gnCashRow = new GnuCashCsvRow();
		gnCashRow.setDate(row.getDate());
		gnCashRow.setDescription(row.getTransaction());
		
//		if( valueString.startsWith("-")){
//			gnCashRow.setWithdrawAmount(valueString.substring(1));
//			gnCashRow.setDepositAmount("");
//		 } 
//		 else{
//			 gnCashRow.setDepositAmount(valueString);
//			 gnCashRow.setWithdrawAmount("");
//		 }

		if(amountValue>0){
			gnCashRow.setWithdrawAmount(amountValue);
			gnCashRow.setDepositAmount(0.0);
		}else{
			gnCashRow.setWithdrawAmount(0.0);
			gnCashRow.setDepositAmount(amountValue);
		}
		
		return gnCashRow;

	}

}
