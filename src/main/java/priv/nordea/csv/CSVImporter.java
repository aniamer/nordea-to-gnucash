package priv.nordea.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVImporter {
	private static final String filePath ="/home/mohamed/Downloads/export.csv";
	private static final String outFilePath ="/home/mohamed/Downloads/gnu_cash_export.csv";
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		 CSVReader reader = new CSVReader(new FileReader(filePath));
		 List<String[]> fileContent= reader.readAll();
		 
		 StringWriter writer = new StringWriter();
		 FileWriter convertedwriter = new FileWriter(outFilePath);
		 
		 CSVWriter csvConvertedwriter = new CSVWriter(writer);
		 csvConvertedwriter.writeNext(new String[]{"Date","Transaktion","Withdraw","Deposit"});
		 
		 
		 NumberFormat nf = NumberFormat.getInstance(new Locale("sv", "SE"));
		 Double value;
		 for(String[] row :fileContent){
			 
			 if(row.length !=1){
				 String[] convertedRow = new String[]{"","","",""};
				 convertedRow[0]=row[0];
				 convertedRow[1] = row[1];
				 try {
					 String valueString = row[3];
					 
					 
					 if( valueString.startsWith("-")){
						 //value = nf.parse().doubleValue();
						 convertedRow[2]=valueString.substring(1);
						 convertedRow[3]="";
					 } 
					 else{
						 //value = nf.parse(valueString).doubleValue();
						 convertedRow[3]= valueString;
						 convertedRow[2]="";
					 }
						 
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				 csvConvertedwriter.writeNext(convertedRow);
			 }
		 }
		 //csvWriter.writeAll(fileContent);
		 System.out.println("\n \n Generated file contents \n \n");
		 System.out.println(writer);
	}

}
