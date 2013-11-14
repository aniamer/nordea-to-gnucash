package priv.nordea.db.hib.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GNUCashGUID {
public static String randomGUId() throws NoSuchAlgorithmException {
	byte[] byteSize = new byte[8];
	
//	long currentTimeMillis = System.currentTimeMillis();
	SecureRandom srRandom = SecureRandom.getInstance("SHA1PRNG");
//	StringBuffer randomBuff= new StringBuffer();
//	for (int i = 0; i < 10; i++) {
//		randomBuff.append(srRandom.nextInt(10));	
//	}
	MessageDigest md = MessageDigest.getInstance("MD5");
//	byte[] dateBytes = new Long(currentTimeMillis).toString().getBytes();
//	byteSize[0] = dateBytes[srRandom.nextInt(dateBytes.length)];
	srRandom.setSeed(System.currentTimeMillis());
	srRandom.nextBytes(byteSize);
	
	byte[] digest = md.digest(byteSize);
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < digest.length; i++) {
		System.out.print(Integer.toHexString(0xFF & digest[i]));
		sb.append(Integer.toHexString(0xFF & digest[i]));
	}
	return sb.toString();

}
}
