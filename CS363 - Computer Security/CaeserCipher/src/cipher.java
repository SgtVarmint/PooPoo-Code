import java.util.*;

public class cipher {
	/**
	 * Make this take custom inputs to decrypt and input as well
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cipherText = "Zyvsmo: Grobo ny e vsfo? Wo: Gsdr wi zkboxdc. Zyvsmo: Grobo "
				+"ny iyeb zkboxdc vsfo? Wo: gsdr wo. Zyvsmi: Grobo ny iye kvv "
				+"vsfo? Wo: Dyqodrob. Zyvsmo: Grobo sc iyeb ryeco? Wo: Xohd "
				+"dy wi xosrlybc ryeco. Zyvsmo: Grobo sc iyeb xosqrlybc ryeco? "
				+"Wo: Iye gyx'd lovsofo wo sp S dovv iye. Zyvsmo: Xohd dy wi " 
				+"ryeco.";
		
		for(int i=0;i<25;i++){//goes through each shift in the alphabet
			int shift = i;//sets the shift to 'i' to find the correct shift
			decrypt(cipherText.toLowerCase(),shift);//calls the decrypt method
			System.out.println("\n");
		}
		in.close();
	}
	
	/**
	 * 
	 * @param money; shit name for the encrypted String
	 * @param shift; shift variable
	 */
	public static void decrypt(String money, int shift){
		//goes through entire string char by char
		for(int i=0;i<money.length();i++){
			
			//if the character is any of these, print it w/o change
			if(money.charAt(i) == '.' || money.charAt(i) == ',' || money.charAt(i) == ':' || money.charAt(i) == '?' || money.charAt(i) == ' '){
				System.out.print(money.charAt(i)+" ");
			}
			//if the char + shift is in between the ASCII vals of 'a' and 'z'
			else if(money.charAt(i)+shift >= 'a' && money.charAt(i)+shift <= 'z'){
				int ascii = (int) money.charAt(i);//
				System.out.print((char)(ascii+shift)+" ");
			}
			
			else if(money.charAt(i)+shift < 'a' || money.charAt(i)+shift > 'z'){
				int ascii = (int) money.charAt(i)-'a' + 71;
				System.out.print((char)(ascii+shift)+" ");
			}
		}
	}
}
