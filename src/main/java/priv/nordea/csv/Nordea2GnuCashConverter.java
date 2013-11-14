package priv.nordea.csv;

import java.util.List;
import java.util.Locale;

import priv.nordea.db.hib.XAccountShop;
import priv.nordea.db.hib.XAccountShopHome;
import priv.nordea.qif.CategoryParser;

public class Nordea2GnuCashConverter implements INordeaCSVConverter<GnuCashCsvRow,NordeaCSVRow> {
	private XAccountShopHome categoryFinder = new XAccountShopHome();
	public GnuCashCsvRow parseNordeaRow(NordeaCSVRow row) {
		Double amountValue = CurrencyParser.parse(row.getAmount(), new Locale("de", "DE"));
		GnuCashCsvRow gnCashRow = new GnuCashCsvRow();
		gnCashRow.setDate(row.getDate().replace("-", ""));
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
			gnCashRow.setWithdrawAmount(0.0);
			gnCashRow.setDepositAmount(amountValue);
			
		}else{
			gnCashRow.setWithdrawAmount(amountValue);
			gnCashRow.setDepositAmount(0.0);
		}
		String category = CategoryParser.parseDescription(row.getTransaction());
		List<XAccountShop> shopName = categoryFinder.findByShopName(category);
		if (shopName.size()== 0)
			gnCashRow.setCategory("Imbalance-SEK");
		else{
			XAccountShop accountName = shopName.get(0);
			gnCashRow.setCategory(accountName.getAccountName());
		}
		
		return gnCashRow;

	}

}
