package priv.nordea.csv;

public class GnuCashCsvRow {
	private String date;
	private String description;
	private Double withdrawAmount;
	private Double depositAmount;
	
	public GnuCashCsvRow(){
		
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public Double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

}
