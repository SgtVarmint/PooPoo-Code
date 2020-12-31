import java.util.*;
import java.io.*;

public class Cipher {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the shift: ");
		int shift = in.nextInt();
		
		System.out.print("Would you like to encrypt or decrypt? ");
		String choice = in.next().toLowerCase();
		
		System.out.print("Enter a file name: ");
		String file = in.next();
		
		Scanner input = null;
		
		try {
			input = new Scanner(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(choice.equals("encrypt"))
			encrypt(input, shift);
			
		
		else if(choice.equals("decrypt"))
			decrypt(input, shift);
	}

	public static void encrypt(Scanner input, int shift) {
		PrintWriter out = null;
		
	}
	
	public static void decrypt(Scanner input, int shift) {
		PrintWriter out = null;

	}
}
