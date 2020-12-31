import java.util.*;
/*
 * checks to see if a string or phrase has duplicate characters
 */
public class HasDuplicates {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.print("Enter a phrase: ");
		String phrase=in.nextLine();

		if(hasDuplicates2(phrase))
			System.out.println("This phrase has duplicate characters.");
		else
			System.out.println("No duplicate characters. Nice, b.");
	}

	public static boolean hasDuplicates(String phrase){
		for(int i=0;i<phrase.length();i++){
			for(int j=i+1;j<phrase.length();j++)
				if(phrase.charAt(i)==phrase.charAt(j))
					return true;
		}
		return false;
	}
	
	public static boolean hasDuplicates2(String phrase){
		/*
		 * only checking for letters
		 * skips over spaces
		 */
		boolean[] array=new boolean[26];
		phrase=phrase.toUpperCase();
		for(int i=0;i<phrase.length();++i){
			char c=phrase.charAt(i);
			if(c>='A' && c<='Z'){
				if(array[c-'A'])
					return true;
				else
					array[c-'A']=true;
			}
		}
		return false;
	}
}
