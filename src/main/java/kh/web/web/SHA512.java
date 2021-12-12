package kh.web.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512 {

	public String generate(String pw) throws NoSuchAlgorithmException{
	    MessageDigest md = MessageDigest.getInstance("SHA-512");
	    md.update(pw.getBytes());
	    byte[] digest = md.digest();
	    String result = new BigInteger(1, digest).toString(16).toUpperCase();
	    return result;
	}
	
	 public static String getSHA512(String pw){

	      String toReturn = null;
	      try {
	         MessageDigest digest = MessageDigest.getInstance("SHA-512");
	         digest.reset();
	         digest.update(pw.getBytes("utf8"));
	         toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return toReturn;
	   }
}
