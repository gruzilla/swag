package swag;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Utils {
	public static String md5(String input) {
		String md5 = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			
			md.update(input.getBytes());
			byte[] digest = md.digest();
			for ( int i=0; i < digest.length; i++ ) {
				String s = Integer.toHexString( digest[i]&0xFF );
				md5 += (s.length() == 1 ) ? "0"+s : s;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5;
	}
}
