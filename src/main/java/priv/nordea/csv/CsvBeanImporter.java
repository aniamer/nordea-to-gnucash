package priv.nordea.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	    strat.setType(NordeaCSVRow.class);
	    String[] columns = new String[] {"date", "transaction", "category", "amount", "balance"}; // the fields to bind do in your JavaBean
	    strat.setColumnMapping(columns);

	    CsvToNordeaBean csv = new CsvToNordeaBean();
	    reader.readNext();
	    List<NordeaCSVRow> list = csv.parse(strat, reader);
	    
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
