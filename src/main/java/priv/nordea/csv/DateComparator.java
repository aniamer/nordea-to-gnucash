package priv.nordea.csv;

import java.util.Comparator;



public class DateComparator implements Comparator<NordeaCSVRow> {
public int compare(NordeaCSVRow o1, NordeaCSVRow o2) {
	// TODO Auto-generated method stub
	if(!o1.getDate().isEmpty() && !o2.getDate().isEmpty()){
		String dateString1 = o1.getDate().replace("-", "");
		String dateString2 = o2.getDate().replace("-", "");
		Long date1 = Long.parseLong(dateString1);
		Long date2 = Long.parseLong(dateString2);
		return date1.compareTo(date2);
	}else{
		return -1;
	}
}
}
