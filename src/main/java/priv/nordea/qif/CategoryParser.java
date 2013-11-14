package priv.nordea.qif;

public class CategoryParser {
	
	enum TransactionCategory{
		ONLINE_PAYMENT("Betalning") {
			@Override
			public String parsecategory(String transDescription) {
				String[] splits = transDescription.split(" ");
				return splits[3];
			}
		},
		EXTRA_SAVING("Xtraspar") {
			@Override
			public String parsecategory(String transDescription) {
				return "Xtraspar";
			}
		},
		CARD_PAYMENT("Kortköp"){
			@Override
			public String parsecategory(String transDescription) {
				String[] splits = transDescription.split(" ");
				return splits[2];
			}
		},
		SALARY("Lön"){
			@Override
			public String parsecategory(String transDescription) {
				return "LÖN";
			}
		},
		ATM("Bankomat"){
			@Override
			public String parsecategory(String transDescription) {
				return "Bankomat";
			}
		},
		AUTO("Autogiro"){
			@Override
			public String parsecategory(String transDescription) {
				String[] splits = transDescription.split(" ");
				return splits[1];
			}
		},
		TRANSFERS("Överföring"){
			@Override
			public String parsecategory(String transDescription) {
				String[] splits = transDescription.split(" ");
				return splits[3];
			}
		};
		private TransactionCategory(String transactionType){
			this.transactionType =transactionType;
		}
		private String transactionType;
		
		public static TransactionCategory findByType(String abbr){
		    for(TransactionCategory v : values()){
		        if( v.transactionType.equals(abbr)){
		            return v;
		        }
		    }
		    return null;
		}
		abstract public String parsecategory(String transDescription);
	}	
	public static String parseDescription(String description) {
		String[] split = description.split(" ");
		TransactionCategory parser=null;
		if(description.startsWith("Reservation"))
			parser = TransactionCategory.findByType(split[1]);
		
		else
			parser = TransactionCategory.findByType(split[0]);
		
		String category=null;
		
		if(parser!= null)
			category = parser.parsecategory(description);
		else
			category ="others";
		
		return category;
	}
	
}
