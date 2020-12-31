import java.util.*;

public class main {
//basically a bunch of Caesar Ciphers
	public static void main(String[] args) {
		int cipher;
		int keyIndex=0;//to keep track of index of key so it can start over
		String key = "LUKE";//key w/ which to encrypt the plain text or decrypt the cipher text
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a phrase to encrypt: ");
		String input = in.next();
		
		//both would have to go at the same time
		for(int i=0;i<input.length();i++) {
			if(keyIndex==key.length())
				keyIndex=0;
			int inputThing = input.charAt(i);
			int keyThing = key.charAt(keyIndex);
			
			cipher = inputThing + keyThing;
			while( (cipher < 'A' || cipher > 'Z') ) {
				//cipher - 'A' = x or whatev; cipher = 'A'+x;
				if(cipher > 'Z') {
					int x = cipher%'A';
					cipher = x+'A';
					break;
				}
				else if(cipher < 'A')
					cipher+='A';
			}
			System.out.printf("%c", cipher);
			keyIndex++;
		}
		in.close();
	}
}
