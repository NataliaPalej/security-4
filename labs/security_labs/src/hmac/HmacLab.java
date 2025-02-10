package hmac;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class HmacLab {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
		
		/**
		 * Q1
		Write a Java program (notes) to
		 Get an instance of HmacSHA256
		 Use it, along with a secret key, to create a HMAC signature for a message.
		 Hash it again and check the equality of the two signatures.
		 */
		KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
		SecretKey secretkey = keygen.generateKey();
		
		System.out.println("keygen :: " + keygen + "\nsecretkey :: " + secretkey);
		
		String message = "Some Message";
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretkey);
		byte[] result = mac.doFinal(message.getBytes());
		
		// Hash it again and check the equality of the two signatures. 
		Mac mac2 = Mac.getInstance("HmacSHA256");
		mac2.init(secretkey);
		byte[] result2 = mac2.doFinal(message.getBytes());
		
		System.out.println("\nlength \tresult :: " + result.length + "\tresult2 :: " + result2.length);
		System.out.println("hashCode \tresult :: " +result.hashCode() + "\tresult2 :: " + result2.hashCode());
		System.out.println("toString \tresult :: " +result.toString() + "\tresult2 :: " + result2.toString());
		
		System.out.println("\nresult result2 :: " + Arrays.equals(result, result2));
		
		
		/**
		 * Q2
		Write a Java program to
		 Generate a HmacSHA256 secret key.
		 Store it to file called “data/secretKey”
		 Write a String message to “data/message.txt”
		 Calculate the HMAC for the message and write it to the file “data/hmac”
		 */
		
		try {
			System.out.println("SecretKey :: " + secretkey);
			System.out.println("Message :: " + message);
			
			// write to file
			writeToFile("data/hmaclab/secretKey", secretkey);
			writeToFile("data/hmaclab/message", message);
			
			byte[] hmac = mac.doFinal(message.getBytes());
			
			writeToFile("data/hmaclab/hmac", hmac);
			
		} catch (Exception e) {
			System.out.println("error :: " + e);
		}
		
		
		/**
		 * Q3
		 * Write a second Java program to
		 Read the secret key from the file “data/secretKey”
		 Read the text from the file “data/message.txt”
		 Read the HMAC from the file “data/hmac”
		 Calculate the HMAC for the message.
		 Compare it with the value received. 
		 */

	}

	static void writeToFile(String filename, Object object) throws Exception {
		FileOutputStream fout = new FileOutputStream(filename);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(object);
		oout.close();
	}
	
	static Object readFromFile(String filename) throws Exception {
		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream oin = new ObjectInputStream(fin);
		Object object = oin.readObject();
		oin.close();
		return object;
	}
}
