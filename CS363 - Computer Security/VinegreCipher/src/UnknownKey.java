import java.util.*;
public class UnknownKey {
	void encrypt() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a phrase to encrypt: ");
		String plainText = in.next();

		int[] alphabet=new int[26];

		//stores frequency of letters in a given text
		for(int i=0;i<plainText.length();i++){
			int index = (plainText.charAt(i)-'A')%26;
			alphabet[index]++;
		}

		indexOfCoincidence(plainText.length(), alphabet);
		in.close();
	}

	void decrypt() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a phrase to decrypt: ");
		String cipherText = in.next();

		int[] alphabet=new int[26];

		//stores frequency of letters in a given text
		for(int i=0;i<cipherText.length();i++){
			int index = (cipherText.charAt(i)-'A')%26;
			alphabet[index]++;
		}

		indexOfCoincidence(cipherText.length(), alphabet);

		String[] array = new String[cipherText.length()/9];
		
		for(int i=0;i<cipherText.length();i++){
			String subsequence="";
			for(int j=0;j<9;j++){
				subsequence+=cipherText.charAt(j+i);
			}
			array[i] = subsequence;
		}

		in.close();
	}

	double indexOfCoincidence(int length, int[] alphabet){
		//.041 = length of 9?
		double n=length;
		double IOC = 0;
		for(int i=0;i<alphabet.length;i++){
			IOC+=alphabet[i]*(alphabet[i]-1);
		}

		double something = IOC/(length*(length-1));

		System.out.println(something);
		return n;
	}
}
