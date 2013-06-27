package priv.nordea.db.hib.util;

import java.util.List;

import priv.nordea.db.hib.*;

public class HibernateTrial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub;
		
		List<Transactions> trans= new TransactionsHome().findByPostDate("20121224230000");
		StringBuffer sb = new StringBuffer();
		for(Transactions tran : trans){
			sb.append(tran.getDescription()+"\n");
			for(Splits split: tran.getSplits()){
				sb.append(split.getQuantityNum()+"\t");
				sb.append(split.getAccount().getName()+"\n");
			}
		}
		System.out.println(sb);
	}

}
