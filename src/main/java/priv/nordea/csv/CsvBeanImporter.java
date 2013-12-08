package priv.nordea.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import priv.nordea.db.hib.TransactionsHome;
import priv.nordea.qif.QIFWirter;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

public class CsvBeanImporter {
//	private static final String filePath ="/home/mohamed/Downloads/bank/";
//	private static final String outFilePath ="/home/mohamed/Downloads/bank/";
	private static final Nordea2GnuCashConverter converter= new Nordea2GnuCashConverter();
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		if(!new File(args[0]).exists())
			throw new RuntimeException("file path doesn't exists !");
		try {
			importNordeaCSV(args[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void importNordeaCSV(String filePath) throws Exception {
		
		
		ColumnPositionMappingStrategy<NordeaCSVRow> strat = new ColumnPositionMappingStrategy<NordeaCSVRow>();
		final DateComparator dateComparator = new DateComparator();
		final SearchComparator searchComparator = new SearchComparator(); 
		
		
	    strat.setType(NordeaCSVRow.class);
	    String[] columns = new String[] {"date", "transaction", "category", "amount", "balance"}; // the fields to bind do in your JavaBean
	    strat.setColumnMapping(columns);
	    File file = new File(filePath);
	    if (file.isDirectory()) {
			for (File oneFile : file.listFiles()) {
				CSVReader reader = new CSVReader(new InputStreamReader(
						new FileInputStream(oneFile), "UTF-8"));
				CsvToNordeaBean csv = new CsvToNordeaBean();
				reader.readNext();
				List<NordeaCSVRow> list = csv.parse(strat, reader);
				//		    list = findLatestTransactions(dateComparator, searchComparator, list);

				convertAndWrite(list, oneFile.getName(), oneFile.getParent());
			}
		}
	    else{
			CSVReader reader = new CSVReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			CsvToNordeaBean csv = new CsvToNordeaBean();
			reader.readNext();
			List<NordeaCSVRow> list = csv.parse(strat, reader);
			//		    list = findLatestTransactions(dateComparator, searchComparator, list);

			convertAndWrite(list, file.getName(), file.getParent());
	    }
	}

	private static void convertAndWrite(List<NordeaCSVRow> list,String fileName, String filePath)
			throws Exception {
		QIFWirter writer = new QIFWirter( new OutputStreamWriter(new FileOutputStream(filePath+"/"+fileName.split("\\.")[0]+".qif"),"UTF-8"));
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
