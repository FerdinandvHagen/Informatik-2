package u3.u3a1;

import u3.u3a1.util.RandomString;

public class Main {

	public static void main(String[] args) {

		/**
		 * Specify number and size of chunks to encrypt
		 */
		int numberOfChunks = 10000;
		int chunkSize = 1000;

		String[] plaintext = new String[numberOfChunks];
		String[] ciphertext = new String[numberOfChunks];
		String[] decryptedText = new String[numberOfChunks];
		RandomString rs = new RandomString(chunkSize);

		/**
		 * Initialize plaintext with random chunks
		 */
		for (int i = 0; i != numberOfChunks; ++i) {
			String randomChunk = new String(rs.nextString());
			plaintext[i] = randomChunk;
		}

		/**
		 * Encrypt plaintext using CaesarChiffre and measure the time. Note that
		 * the implementation of the method encrypt relies on Strings.
		 */
		System.out.println("Starting encryption (using Strings)");
		long start = System.currentTimeMillis();
		for (int i = 0; i != numberOfChunks; ++i) {
			ciphertext[i] = CaesarChiffre.encrypt(plaintext[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("Done - Duration: " + Long.toString(end - start)
				+ " ms.\n");

		/**
		 * Decrypt ciphertext using CaesarChiffre and measure the time. Note
		 * that the implementation of the methode decrypt employs StringBuffers.
		 */
		System.out.println("Starting decryption (using StringBuffers)");
		start = System.currentTimeMillis();
		for (int i = 0; i != numberOfChunks; ++i) {
			decryptedText[i] = CaesarChiffre.decrypt(ciphertext[i]);
		}
		end = System.currentTimeMillis();
		System.out.println("Done - Duration: " + Long.toString(end - start)
				+ " ms.\n");

		/**
		 * Check if decrypted text equals plaintext (from the beginning).
		 */
		boolean correct = true;
		for (int i = 0; i != numberOfChunks; ++i) {
			if (decryptedText[i].compareTo(plaintext[i]) != 0) {
				correct = false;
			}
		}
		if (correct == true) {
			System.out.println("Decryption successful :-)");
		} else {
			System.out.println("Decryption not successful :-(");
		}
	}
}
