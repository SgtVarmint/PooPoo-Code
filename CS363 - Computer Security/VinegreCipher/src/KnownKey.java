import java.util.*;

public class KnownKey {

	/*
	 * Prompts the user to encrypt or decrypt, for a phrase to encrypt/decrypt, and if they known they key for the message
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Do you want to encrypt or decrypt?: ");
		String userChoice = in.next().toLowerCase();

		System.out.print("Do you know the key? (y/n): ");
		String knowsKey = in.next().toLowerCase();

		if(knowsKey.equals("y")) {
			if(userChoice.equals("encrypt"))
				vigenereEncrypt();
			else if(userChoice.equals("decrypt"))
				vigenereDecrypt();
		}
		else if(knowsKey.equals("n")) {
			UnknownKey unknown = new UnknownKey();
			if(userChoice.equals("encrypt"))
				unknown.encrypt();
			else if(userChoice.equals("decrypt"))
				unknown.decrypt();
		}
		in.close();
	}

	/*
	 * Encryption method which takes 
	 */
	public static void vigenereEncrypt() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a phrase to encrypt: ");
		String plainText = in.next();

		System.out.print("Enter the key: ");
		String key = in.next();

		int cipher;
		int keyIndex=0;//to keep track of index of key so it can start over

		for(int i=0;i<plainText.length();i++) {
			if(keyIndex==key.length())
				keyIndex=0;

			cipher = ((plainText.charAt(i) + key.charAt(keyIndex))%26)+'A';
			System.out.printf("%c", cipher);
			keyIndex++;
		}
		in.close();
	}
	/*
	 * 
	 */
	public static void vigenereDecrypt() {
		int plain;
		int keyIndex=0;//to keep track of index of key so it can start over
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a phrase to decrypt: ");
		String cipherText = in.next();

		System.out.print("Enter the key: ");
		String key = in.next();

		for(int i=0;i<cipherText.length();i++) {
			if(keyIndex == key.length())
				keyIndex=0;

			int x = (cipherText.charAt(i) - key.charAt(keyIndex))%26;
			if(x < 0)//checks to see if the calculated character is < 0, and adds length of alphabet to that value 
				x+=26;
			plain = x+'A';

			System.out.printf("%c", plain);
			keyIndex++;
		}

		in.close();
	}
}
