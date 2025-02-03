package encryption_decryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Q3_CipherKey implements Serializable {

	public static void main(String[] args) {
		
		// Q3a. Write a Java program to
		/** 
		 * - instantiate an Employee object
		 */
		Q2_Employee employee = new Q2_Employee("some name", "some address", "some phone");
		
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecretKey secretKey = keygen.generateKey();
			
			System.out.println("keygen :: " + keygen + "\nsecretKey :: " + secretKey);
			
			Cipher encryptCipher = Cipher.getInstance("AES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			/**
			 * - create a SealedObject containing an Employee object, encrypted with some Cipher
			 */
			
			SealedObject sealedObject = new SealedObject(employee, encryptCipher);
			System.out.println("Object encrypted :: " + sealedObject);
			
			
			/**
			 * - save the SealedObject to a file (“data/sealedObject.dat”)
			 * - save the Cipher key to a file (“data/secretKey”)
			 */
			writeToFile("data/sealedObject.dat", sealedObject);
			writeToFile("data/secretKey", secretKey);
			
			
			// Q3b. Write a second Java program to
			
			/**
			 * - read the key from file
			 * - read the SealedObject from file 
			 */
			SecretKey secretKey_read = (SecretKey) readFromFile("data/secretKey");
			SealedObject sealedObject_read = (SealedObject) readFromFile("data/sealedObject.dat");
			
			Cipher decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKey_read);
			
			/**
			 * - extract the Employee object from the SealedObject.
			 */
			Q2_Employee decryptedEmployee = (Q2_Employee) sealedObject_read.getObject(decryptCipher);
            System.out.println("\n\nDecrypted Employee object retrieved :: " + decryptedEmployee);
			
			
			/**
			 * - print out the text stored in the Employee object
			 */
            System.out.println("Employee Details\nName: " + decryptedEmployee.getName() + "\tAddress: " + decryptedEmployee.getAddress() + "\tPhone: " + decryptedEmployee.getPhone());
			
		} catch (NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void writeToFile(String filename, Object obj) throws Exception {
		FileOutputStream fout = new FileOutputStream(new File(filename));
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		
		oout.writeObject(obj);
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
