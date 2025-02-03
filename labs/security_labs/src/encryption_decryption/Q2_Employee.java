package encryption_decryption;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Q2_Employee implements Serializable {

	/**
	 * Q2. (notes) Write a Java class Employee with fields name, address telNo.
	 * Write a Java program to
	 * instantiate an Employee object
	 */
		
	private String name, address, phone;
	
	public Q2_Employee(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	public Q2_Employee() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * create a SealedObject containing the Employee object, encrypted with some Cipher
	 * Extract the object from the SealedObject using another cipher (initialized for decryption)
	 * Print out the contents of the Employee object.
	 */
	public static void main(String[] args) {
		Q2_Employee employee = new Q2_Employee("name", "address", "phone");
		
		// Steps:
		// 1. Use keygen (KeyGenerator) in "AES" to generate secretkey (SecretKey)
		// 2. Initialise Cipher in Encryption Mode
		// 3. Encrypt Object into SealedObject
		// 4. Initialise Cipher in Decryption Mode
		// 5. Retrieve Object from SealedObject	
		
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			//keygen.init(128); // 128-bit AES key
			SecretKey secretkey = keygen.generateKey();
			
			Cipher encryptCipher = Cipher.getInstance("AES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretkey);
			
			SealedObject sealedObj = new SealedObject(employee, encryptCipher);
			System.out.println("Object employee encrypted successfully. \nsealedObj :: " + sealedObj);
			
			Cipher decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE, secretkey);
			
			Q2_Employee decryptedEmployee = (Q2_Employee) sealedObj.getObject(decryptCipher);
			System.out.println("\nEmployee object decrypted successfully. \ndecryptedEmployee :: " + decryptedEmployee);
			System.out.println("Name: " + decryptedEmployee.getName() + "\tAddress: " + decryptedEmployee.getAddress() + "\tPhone: " + decryptedEmployee.getPhone());
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IOException | ClassNotFoundException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		
	}
	
		
		
}
