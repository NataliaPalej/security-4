package message_digest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

public class MessageDigestLab {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
	
	/**
	 * Q1.
	 * Write a Java program to find the SHA-256 hash of a String. 
	 * Print out the Base64 encoding of the hash value. 
	 * Show that if the String changes by just 1 character, the digest value changes completely.
	 */
		System.out.println("Q1");
		// String message = "SomeMessageSomeMessageSomeMessageSomeMessage";
		String message = "SomeMessageSomeMessageSomeMessageSomeMessag";
		System.out.println("message :: " + message);
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			// md.reset();
			
			md.update(message.getBytes());
			byte[] messageDigest = md.digest();
			System.out.println("messageDigest :: " + messageDigest.length);
			
			String encodedmsg = Base64.getEncoder().encodeToString(messageDigest);
			System.out.println("Base64 encodedmsg :: " + encodedmsg);
		} catch (Exception e) {
			System.out.println("Error :: " + e);
		}
		
		// message :: SomeMessageSomeMessageSomeMessageSomeMessage
		// messageDigest :: 32
		// Base64 encodedmsg :: 3S/WDLIjMj5qmHOZhMACp70PmD5CDMVKxy4HMRYmSNE=
		
		// message :: SomeMessageSomeMessageSomeMessageSomeMessag
		// messageDigest :: 32
		// Base64 encodedmsg :: gHyjafioIOpQ4cvWu4l9a9/UXma2DvcMoksCZLmpPSY=
		
	 /**
	 * Q2.
	 * Write a program to find the SHA-256 hash of a file. Print out the Base64 encoding of the hash value. 
	 * Use the MessageDigest method
	 * offset - starting at 0, len - amount of bytes
	 * void update(byte[] input, int offset, int len)
	 * and the InputStream method int read(byte[] b)
	 */
		System.out.println("\nQ2");
		// Create MessageDigest instance for SHA-256
        MessageDigest digest2 = MessageDigest.getInstance("SHA-256");

        // Read file and update the digest
        try (InputStream is = new FileInputStream("data/messagedigest/text.txt")) {
        	// Read in 64 byte chunks
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest2.update(buffer, 0, bytesRead);
            }
            is.close();
        } catch (Exception ex) {
        	System.out.println("Q2 error :: " + ex);
        }

        // Digest final hash
        byte[] hashBytes = digest2.digest();
        
        // Encode the hash in Base64
        String encodedHash = Base64.getEncoder().encodeToString(hashBytes);
        System.out.println("Base64 encoded hash: " + encodedHash);
        
        // Q2
     	// Base64 encoded hash: YNVq0fOiwwOh/4cU9L20uJicIGyfzMPkzRsaadscf6g=
        
        /**
    	 * Q3.
    	 * Look up the documentation on Apache Commons Codec API . (http://commons.apache.org/codec/).
    	 * Write a program to hash some text using this library and different hashing algorithms.
    	 */
        System.out.println("\nQ3");
        
        String q3_text = "Some Text To Encode";
        String md5 = DigestUtils.md5Hex(q3_text);
        System.out.println("q3_text :: " + q3_text + "\nmd3 :: " + md5);
        
        // q3_text :: Some Text To Encode
        // md5 :: ff20cfe49139da798af3d039a22febd2
        
        String md2Hex = DigestUtils.md2Hex(q3_text);
        System.out.println("md2 :: " + md2Hex);
        
        String sha1Hex = DigestUtils.sha1Hex(q3_text);
        String sha256Hex = DigestUtils.sha256Hex(q3_text);
        String sha512Hex = DigestUtils.sha512Hex(q3_text);
        
        System.out.println("sha1Hex :: " + sha1Hex);
        System.out.println("sha256Hex :: " + sha256Hex);
        System.out.println("sha512Hex :: " + sha512Hex);
        
        // MD5 hash size: 128 bits (16 bytes)
        // SHA-256 hash size: 256 bits (32 bytes)
        
    	
    	 /**
    	 * Q4.
    	 * Write a program to hash a file using the Apache Commons Codec library
    	 */
  
        System.out.println("\nQ4");
        
        try {
        	InputStream is4 = new FileInputStream("data/messagedigest/text.txt");
        	String sha256Hex4 = DigestUtils.sha256Hex(is4);
        	String sha384Hex = DigestUtils.sha384Hex(is4);
        	
        	System.out.println("sha256Hex4 :: " + sha256Hex4 + "\nsha384Hex :: " + sha384Hex);
        } catch (Exception e4) {
        	System.out.println("Q4 error :: " + e4);
        }
        
        // sha256Hex4 :: 60d56ad1f3a2c303a1ff8714f4bdb4b8989c206c9fccc3e4cd1b1a69db1c7fa8
        // sha384Hex :: 38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b
    }
}
