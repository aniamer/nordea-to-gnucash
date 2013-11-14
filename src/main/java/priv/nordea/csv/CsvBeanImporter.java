package priv.nordea.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import priv.nordea.db.hib.Accounts;
import priv.nordea.db.hib.Commodities;
import priv.nordea.db.hib.CommoditiesHome;
import priv.nordea.db.hib.Splits;
import priv.nordea.db.hib.Transactions;
import priv.nordea.db.hib.TransactionsHome;
import priv.nordea.qif.QIFWirter;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

public class CsvBeanImporter {
	private static final String filePath ="/home/mohamed/Downloads/export.csv";
	private static final String outFilePath ="/home/mohamed/Downloads/gnu_cash_export.qif";
	private static final Nordea2GnuCashConverter converter= new Nordea2GnuCashConverter();
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		importNordeaCSV();
	}

	private static void importNordeaCSV() throws Exception {
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		
		ColumnPositionMappingStrategy<NordeaCSVRow> strat = new ColumnPositionMappingStrategy<NordeaCSVRow>();
		final DateComparator dateComparator = new DateComparator();
		final SearchComparator searchComparator = new SearchComparator(); 
		
	    strat.setType(NordeaCSVRow.class);
	    String[] columns = new String[] {"date", "transaction", "category", "amount", "balance"}; // the fields to bind do in your JavaBean
	    strat.setColumnMapping(columns);

	    CsvToNordeaBean csv = new CsvToNordeaBean();
	    reader.readNext();
	    List<NordeaCSVRow> list = csv.parse(strat, reader);
//	    list = findLatestTransactions(dateComparator, searchComparator, list);
	    
	    convertAndWrite(list);
	}

	private static void convertAndWrite(List<NordeaCSVRow> list)
			throws Exception {
		QIFWirter writer = new QIFWirter( new OutputStreamWriter(new FileOutputStream(outFilePath),"UTF-8"));
//		FileWriter writer = new FileWriter(outFilePath);
//		CSVWriter csvConvertedwriter = new CSVWriter(new OutputStreamWriter(System.out),';');
//		
//	    csvConvertedwriter.writeNext(new String[]{"Date","Transaktion","Withdraw","Deposit"});
	    writer.writeHeader();
	    for(NordeaCSVRow row : list){
	    	if(null != row.getAmount()){
	    		GnuCashCsvRow convertedRow= converter.parseNordeaRow(row);
	    		writer.writeRow(convertedRow);
//	    		csvConvertedwriter.writeNext(new String[]{convertedRow.getDate(),
//	    				convertedRow.getDescription(),convertedRow.getWithdrawAmount().toString(),
//	    				convertedRow.getDepositAmount().toString()});
	    	}
	    }
	    writer.close();
//	    csvConvertedwriter.close();
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
