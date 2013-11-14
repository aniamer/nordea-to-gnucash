package priv.nordea.qif;

import java.io.IOException;
import java.io.Writer;

import priv.nordea.csv.GnuCashCsvRow;

public class QIFWirter {
	private Writer outWriter;

	public QIFWirter(Writer outWriter) {
		super();
		this.outWriter = outWriter;
	}
	
	public void writeHeader() throws IOException{
		outWriter.write("!Account\n"+"NChecking Account\n"+"TBank\n^\n");
	}
	public String writeBankHeader(){
		return "!Type:Bank\n";
	}
	public String writeDate(String date){
		return "D"+date+"\n";
	}
	public String writeAmount(Double amount){
		return "T"+amount.toString()+"\n";
	}
	public String writePayee(String payee){
		return "P"+payee+"\n";
	}
	public String writeCategory(String category){
		return "L"+category+"\n";
	}
	
	public void writeRow(GnuCashCsvRow row) throws IOException{
		outWriter.write(writeBankHeader());
		outWriter.write(writeDate(row.getDate()));
		Double amount=null;
		amount = row.getWithdrawAmount();
		
		if(amount == 0.0)
			amount = row.getDepositAmount();
		
		outWriter.write(writeAmount(amount));
		outWriter.write(writePayee(row.getDescription()));
		outWriter.write(writeCategory(row.getCategory()));
		outWriter.write("^\n");
		outWriter.flush();
	}
	
	public void close() throws Exception{
		outWriter.close();
	}
}
