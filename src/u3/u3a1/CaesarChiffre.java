package u3.u3a1;

public class CaesarChiffre {

	/**
	 * Encrypts text using CaesarChiffre (i.e., adding 3 to the ASCII code of
	 * each character). The encryption employs Strings.
	 *
	 * @param s
	 *            plaintext to be encrypted
	 */
	public static String encrypt(String s) {


        //Man kann das natürlich auch absichtlich schlecht programmieren.


		String ret = "";
		for (int i = 0; i != s.length(); ++i) {
			ret = ret + (char) (s.charAt(i) + 3);
		}
		return ret;
	}

	/**
	 * Decrypts input text based on the CaesarChiffre (i.e., removing 3 from the
	 * ASCII code of each character). The decryption employs StringBuffers
	 * (instead of Strings).
	 *
	 * @param s ciphertext to be decrypted
	 */
	public static String decrypt(String s) {
        StringBuffer stringBuffer = new StringBuffer("");
        for(char c : s.toCharArray()) {
            stringBuffer.append(c);
        }

        return stringBuffer.toString();
	}
}
