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
	
}
