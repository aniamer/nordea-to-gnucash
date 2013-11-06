package priv.nordea.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import priv.nordea.db.hib.TransactionsHome;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

public class CsvBeanImporter {
	private static final String filePath ="/home/mohamed/Downloads/export.csv";
	private static final String outFilePath ="/home/mohamed/Downloads/gnu_cash_export.csv";
	private static final Nordea2GnuCashConverter converter= new Nordea2GnuCashConverter();
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		importNordeaCSV();
	}

	private static void importNordeaCSV() throws FileNotFoundException,
			IOException {
		CSVReader reader = new CSVReader(new FileReader(filePath));
		
		ColumnPositionMappingStrategy<NordeaCSVRow> strat = new ColumnPositionMappingStrategy<NordeaCSVRow>();
		final DateComparator dateComparator = new DateComparator();
		final SearchComparator searchComparator = new SearchComparator(); 
		
	    strat.setType(NordeaCSVRow.class);
	    String[] columns = new String[] {"date", "transaction", "category", "amount", "balance"}; // the fields to bind do in your JavaBean
	    strat.setColumnMapping(columns);

	    CsvToNordeaBean csv = new CsvToNordeaBean();
	    reader.readNext();
	    List<NordeaCSVRow> list = csv.parse(strat, reader);
	    list = findLatestTransactions(dateComparator, searchComparator, list);
	    
	    convertAndWrite(list);
	}

	private static void convertAndWrite(List<NordeaCSVRow> list)
			throws IOException {
		FileWriter writer = new FileWriter(outFilePath);
		CSVWriter csvConvertedwriter = new CSVWriter(writer,';');
		
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
	    csvConvertedwriter.close();
	}

	private static List<NordeaCSVRow> findLatestTransactions(
			final DateComparator dateComparator,
			final SearchComparator searchComparator, List<NordeaCSVRow> list) {
		Collections.sort(list, dateComparator);
	    
	    TransactionsHome transHome = new TransactionsHome();
	    String maxDate = transHome.findMaxDate().get(0);
	    NordeaCSVRow maxInstance = new NordeaCSVRow();
	    maxDate = maxDate.substring(0, 8);
	    maxInstance.setDate(maxDate);
	    
	    int foundIndex = Collections.binarySearch(list, maxInstance, searchComparator);
	    
	    if(foundIndex > 0)
	    	list = list.subList(foundIndex, list.size()-1);
	    else
	    	list = Collections.emptyList();
	    
		return list;
	}

}
