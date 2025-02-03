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
		System.out.println("Q1: Math.random()");
        System.out.println(Math.random());
        System.out.println(Math.random());
        System.out.println(Math.random() + "\n");

		/**
		 * Q2
		 * Write a Java program to print three random numbers beteen 0 and 1 using
		 * java.util.Random. Run it twice. Is the output the same? How do you make the output
		 * the same?
		 */
		// Seed ensures that the generated random number stays the same every time we run it
		// random.setSeed(0);
		
        System.out.println("Q2: java.util.Random without seed");
        Random randomWithoutSeed = new Random();
        System.out.println(randomWithoutSeed.nextDouble());
        System.out.println(randomWithoutSeed.nextDouble());
        System.out.println(randomWithoutSeed.nextDouble() + "\n");

        System.out.println("Q2: java.util.Random with fixed seed");
        Random randomWithFixedSeed = new Random(100);
        System.out.println(randomWithFixedSeed.nextDouble());
        System.out.println(randomWithFixedSeed.nextDouble());
        System.out.println(randomWithFixedSeed.nextDouble() + "\n");

        // Running the seeded version again to confirm reproducibility
        System.out.println("Q2: java.util.Random with fixed seed (Running again)");
        Random randomWithFixedSeedAgain = new Random(100);
        System.out.println(randomWithFixedSeedAgain.nextDouble());
        System.out.println(randomWithFixedSeedAgain.nextDouble());
        System.out.println(randomWithFixedSeedAgain.nextDouble() + "\n");
		
		/**
		 * Q3
		 * Write a Java program using java.util.Random to print 
		 * (a) random integer
		 * (b) random double between 0 and 1
		 * (c) random integer between 0 and 100
		 */
        System.out.println("Q3 (a): Random integer");
        System.out.println(random.nextInt() + "\n");
		
        System.out.println("Q3 (b): Random double between 0 and 1");
        System.out.println(random.nextDouble() + "\n");
		
        System.out.println("Q3 (c): Random integer between 0 and 100");
        System.out.println(random.nextInt(101) + "\n");
        
		/**
		 * Q4
		 * Write a Java program using java.security.SecureRandom to print 
		 * (a) random integer between 0 and 1000
		 * (b) 20 byte seed value obtained from SecureRandom
		 * Use Hex.encodeHexString() in the Apache Commons Codec Library
		 * (http://commons.apache.org/codec/) to print the Hex representation of this seed.
		 */
        System.out.println("Q4 (a): SecureRandom integer between 0 and 1000");
        SecureRandom secureRandom = new SecureRandom();
        System.out.println(secureRandom.nextInt(1001) + "\n");  // Fixed to include 1000

        System.out.println("Q4 (b): Hex Encoding - 20 byte seed");
        byte[] seed = secureRandom.generateSeed(20);
        System.out.println(Hex.encodeHexString(seed));
		
		/**
		 * Q5
		 * Set the the seed on your instance of java.security.SecureRandom. Does this give the
		 * same random numbers each time you run the program? Why? Read the
		 * documentation on setSeed(). 
		 */
		System.out.println("\nQ5: SecureRandom with a fixed seed");

		SecureRandom secureRandomWithSeed = new SecureRandom();
		secureRandomWithSeed.setSeed(12345L); // Setting a specific seed

		System.out.println(secureRandomWithSeed.nextInt(1000));
		System.out.println(secureRandomWithSeed.nextInt(1000));
		System.out.println(secureRandomWithSeed.nextInt(1000));

		// Running the same again
		secureRandomWithSeed.setSeed(12345L);
		System.out.println(secureRandomWithSeed.nextInt(1000));
		System.out.println(secureRandomWithSeed.nextInt(1000));
		System.out.println(secureRandomWithSeed.nextInt(1000));
		
		
		/**
		 * Q6
		 * Complete the following Java program to implement a Linear Congruential Generator
		 * which generates and prints out 20 pseudo random numbers. 
		 */
		// parameter values 
		System.out.println("\nQ6: Linear Congruential Generator");

		int seed2 = 5;
		int mod = 7;
		int multiplier = 3;
		int inc = 3;
		int prev = seed2;

		for (int i = 0; i < 20; i++) {
		    prev = ((prev * multiplier) + inc) % mod; // Update prev in each iteration
		    System.out.println(prev);
		}

		
		/**
		 * Q7
		 * Write a Java program to print the list of SecureRandom providers on Windows. Also
		 * print out the default algorithm for SecureRandom. 
		 */
		System.out.println("\nQ7: SecureRandom Providers & Default Algorithm");

		final Set<String> algorithms = Security.getAlgorithms("SecureRandom");
		System.out.println("Available SecureRandom Providers:");
		for (String algorithm : algorithms) {
		    System.out.println(algorithm);
		}

		// Printing the default algorithm
		SecureRandom defaultSecureRandom = new SecureRandom();
		System.out.println("\nDefault SecureRandom Algorithm: " + defaultSecureRandom.getAlgorithm());

		
	}
}
