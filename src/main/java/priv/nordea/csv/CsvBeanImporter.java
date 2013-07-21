package priv.nordea.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import priv.nordea.db.hib.TransactionsHome;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

public class CsvBeanImporter {
	private static final String filePath ="/home/mohamed/Downloads/export.csv";
	private static final String outFilePath ="/home/mohamed/Downloads/gnu_cash_export.csv";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Nordea2GnuCashConverter converter= new Nordea2GnuCashConverter();
		CSVReader reader = new CSVReader(new FileReader(filePath));
		FileWriter writer = new FileWriter(outFilePath);
		CSVWriter csvConvertedwriter = new CSVWriter(writer,';');
		
		ColumnPositionMappingStrategy<NordeaCSVRow> strat = new ColumnPositionMappingStrategy<NordeaCSVRow>();
		final DateComparator dateComparator = new DateComparator();
		final SearchComparator searchComparator = new SearchComparator(); 
	    strat.setType(NordeaCSVRow.class);
	    String[] columns = new String[] {"date", "transaction", "category", "amount", "balance"}; // the fields to bind do in your JavaBean
	    strat.setColumnMapping(columns);

	    CsvToNordeaBean csv = new CsvToNordeaBean();
	    reader.readNext();
	    List<NordeaCSVRow> list = csv.parse(strat, reader);
	    Collections.sort(list, dateComparator);
	    
	    //TransactionsHome transHome = new TransactionsHome();
	    String maxDate = "20130328";//transHome.findMaxDate().get(0);
	    NordeaCSVRow maxInstance = new NordeaCSVRow();
	    maxInstance.setDate(maxDate);
	    maxDate = maxDate.substring(0, 7);
	    int foundIndex = Collections.binarySearch(list, maxInstance, searchComparator);
	    list = list.subList(foundIndex, list.size()-1);
	    csvConvertedwriter.writeNext(new String[]{"Date","Transaktion","Withdraw","Deposit"});
	    for(NordeaCSVRow row : list){
	    	if(null != row.getAmount()){
	    		GnuCashCsvRow convertedRow= converter.parseNordeaRow(row);
	    		csvConvertedwriter.writeNext(new String[]{convertedRow.getDate(),
	    				convertedRow.getDescription(),convertedRow.getWithdrawAmount().toString(),
	    				convertedRow.getDepositAmount().toString()});
		    	System.out.println(convertedRow.getDate()+"\t"
		    			+convertedRow.getDepositAmount()+"\t"+convertedRow.getWithdrawAmount()+"\t"
		    			+ convertedRow.getDescription());
	    	}
	    }
	    
	    System.out.println("Done !");
	}

}
