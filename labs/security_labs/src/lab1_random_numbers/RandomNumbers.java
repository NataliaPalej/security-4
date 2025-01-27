package lab1_random_numbers;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;

public class RandomNumbers {

	public static void main(String[] args) {
		
		Random random = new Random();
		// Define seed in constructor 
		Random random_with_seed = new Random(100);
		
		/**
		 * Q1
		 * Write a Java program to print three random numbers beteen 0 and 1 using
		 * Math.random(). Run it twice. Is the output the same?
		 */
		System.out.println("Random without seed");
		System.out.println(random.nextDouble());
		System.out.println(random.nextDouble());
		System.out.println(random.nextDouble() + "\n");
		
		/**
		 * Q2
		 * Write a Java program to print three random numbers beteen 0 and 1 using
		 * java.util.Random. Run it twice. Is the output the same? How do you make the output
		 * the same?
		 */
		// Seed ensures that the generated random number stays the same every time we run it
		// random.setSeed(0);
		
		System.out.println("Random with seed 0");
		System.out.println(random.nextDouble());
		System.out.println(random.nextDouble());
		System.out.println(random.nextDouble() + "\n");
		
		// random.setSeed(100);
		System.out.println("Random with seed 100");
		System.out.println(random.nextDouble());
		System.out.println(random.nextDouble());
		System.out.println(random.nextDouble() + "\n");
		
		System.out.println("Random double with seed");
		System.out.println(random_with_seed.nextDouble() + "\n");
		
		/**
		 * Q3
		 * Write a Java program using java.util.Random to print 
		 * (a) random integer
		 * (b) random double between 0 and 1
		 * (c) random integer between 0 and 100
		 */
		System.out.println("Q3 (a)");
		Random random_int = new Random();
		System.out.println("Random int");
		System.out.println(random_int.nextInt() + "\n");
		
		System.out.println("Q3 (b)");
		int min = 0;
		int max_double = 1;
		System.out.println("Random double between 0 and 1");
		System.out.println(random.nextDouble(max_double-min) + "\n");
		
		System.out.println("Q3 (c)");
		int max_int = 100;
		System.out.println("Random int beteen 0 and 100");
		System.out.println(random.nextInt(max_int - min) + "\n");
		
		/**
		 * Q4
		 * Write a Java program using java.security.SecureRandom to print 
		 * (a) random integer between 0 and 1000
		 * (b) 20 byte seed value obtained from SecureRandom
		 * Use Hex.encodeHexString() in the Apache Commons Codec Library
		 * (http://commons.apache.org/codec/) to print the Hex representation of this seed.
		 */
		System.out.println("Q4 (a)");
		SecureRandom secure_random = new SecureRandom();
		System.out.println("Secure Random");
		System.out.println(secure_random.nextInt(1000) + "\n");
		
		System.out.println("Q4 (b)");
		System.out.println("Hex Encoding - 20 byte seed");
		byte seed[] = secure_random.generateSeed(20);
		System.out.println(Hex.encodeHex(seed));
		
		/**
		 * Q5
		 * Set the the seed on your instance of java.security.SecureRandom. Does this give the
		 * same random numbers each time you run the program? Why? Read the
		 * documentation on setSeed(). 
		 */
		
		System.out.println("\nQ5");
		
		
		/**
		 * Q6
		 * Complete the following Java program to implement a Linear Congruential Generator
		 * which generates and prints out 20 pseudo random numbers. 
		 */
		// parameter values 
		int seed2 = 5;
		int mod = 7;
		int multiplier = 3;
		int inc = 3;
		int prev = seed2 ;
		
		System.out.println("Q6");
		for (int i = 0; i < 20; i++) {
			int random2 = ((prev*multiplier) + inc) % mod;
			System.out.println(random2);
		}
		
		/**
		 * Q7
		 * Write a Java program to print the list of SecureRandom providers on Windows. Also
		 * print out the default algorithm for SecureRandom. 
		 */
		System.out.println("\nQ7");
		final Set<String> algorithms = Security.getAlgorithms("SecureRandom");
		for (String algorithm : algorithms) {
			System.out.println(algorithm);
		}
		
	}
}
