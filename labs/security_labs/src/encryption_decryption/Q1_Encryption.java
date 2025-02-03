package encryption_decryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Q1_Encryption {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		/**
		 * Q1. (notes) Write a Java program (notes) to
		 * encrypt some text with AES (TwoFish, Serpent) then decrypt it
		 */
		
		// Declare encoding algorithm
		String ALGORITHM = "AES";
		
		// Generate keygen instance
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		
		// Generate secret key
		SecretKey secret_key = keygen.generateKey();
		
		// Cipher - encoding type
		Cipher encoding_cipher = Cipher.getInstance(ALGORITHM);
		
		// Initialise cipher for encryption
		encoding_cipher.init(Cipher.ENCRYPT_MODE, secret_key);
		
		String s = "Hello World";
		System.out.println("Plain text: " + s);
		
		byte[] plain_text = s.getBytes();
		System.out.println("byte[] plain_text :: " + plain_text);
		
		// Encrypt plain text
		byte[] encrypted_message = encoding_cipher.doFinal(plain_text);
		System.out.println("Encrypted Message: " + encrypted_message);
		
		/**
		 * DECRYPTION
		 */
		Cipher decryption_cipher = Cipher.getInstance(ALGORITHM);
		decryption_cipher.init(Cipher.DECRYPT_MODE, secret_key);
		
		// Decrypt encrypted message
		byte[] cleartext = decryption_cipher.doFinal(encrypted_message);
		
		String decrypted_message = new String(cleartext);
		System.out.println("\ndecryption_cipher :: " + decryption_cipher + "\ncleartext :: " + cleartext);
		System.out.println("Decrypted Message: " + decrypted_message);
	}

}
