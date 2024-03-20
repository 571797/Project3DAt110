package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	
	public static BigInteger hashOf(String entity) {	
		
		BigInteger hashint = null;
		MessageDigest md5;
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		// we use MD5 with 128 bits digest
        try {
          md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // compute the hash of the input 'entity'
		byte[] digest = md5.digest(entity.getBytes());
		// convert the hash into hex format
		String hex = toHex(digest);
		// convert the hex into BigInteger
		hashint =new BigInteger(hex,16);

		// return the BigInteger
		
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5

        // compute the number of bits = bitSize()
		int numberOfBits = bitSize();


        // compute the address size = 2 ^ number of bits
		BigDecimal bigDecimal = new BigDecimal(2);
		BigDecimal adressSizeBigDecimal = bigDecimal.pow(numberOfBits);
		BigInteger adressSizeBigInteger = adressSizeBigDecimal.toBigInteger();
		// return the address size

		return adressSizeBigInteger;
	}
	
	public static int bitSize()  {


        // find the digest length

        int digestlen = 0;
        try {
            digestlen = MessageDigest.getInstance("MD5").getDigestLength();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
