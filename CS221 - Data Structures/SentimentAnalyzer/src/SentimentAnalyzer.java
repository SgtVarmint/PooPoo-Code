import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentimentAnalyzer {

	public static void main(String[] args) {
		WordTable stopTable = new WordTable(); 
		WordTable reviewTable = new WordTable();
		Scanner scan = new Scanner(System.in);
		String stop;
		
		do{
		System.out.println("Enter a stop word file: ");
		stop = scan.nextLine();
		if(!new File(stop).exists())
			System.out.println("File not found. Enter a valid file name.");
		}while(!new File(stop).exists());
			
		
		String review;
		
		do{
		System.out.println("Enter a review file: ");
		review = scan.nextLine();
		if(!new File(review).exists())
			System.out.println("File not found. Enter a valid file name.");
		}while(!new File(review).exists());
		
		Pattern pattern = Pattern.compile("([a-z]+[a-z'-]*[a-z]+)|[a-z]");
		
		try(Scanner stopFile = new Scanner(new File(stop))) {
			while(stopFile.hasNext()){
				stopTable.add(stopFile.next(),2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Scanner reviewFile = new Scanner(new File(review))) {
			while(reviewFile.hasNextLine()){
				String line = reviewFile.nextLine().toLowerCase();
				int score = line.charAt(0);
				score = score - '0';
				Matcher match = pattern.matcher(line);
				while(match.find()) {
					if(stopTable.contains(line.substring(match.start(),match.end())))
						continue;
					reviewTable.add(line.substring(match.start(),match.end()),score);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String input;
		String phrase;
		double score;
		int amount;
		do{
			score=0;
			amount=0;
			System.out.println("Would you like to analyze a phrase? (yes/no)");
			input = scan.nextLine().toLowerCase();
			switch(input){
			case "yes":
				System.out.println("Enter phrase: ");
				phrase = scan.nextLine();
				Matcher match = pattern.matcher(phrase);
				while(match.find()){					
					if(!stopTable.contains(phrase.substring(match.start(),match.end()))){
						score += reviewTable.getScore(phrase.substring(match.start(),match.end()));
						amount++;
					}
				}
				SentimentAnalyzer.rate(score, amount);
				break;
			case "no":
				System.out.println("Bye-bye, bb! <3 ;-* ");
			default:
				break;
			}
			
		}while(!input.equals("no"));
		PrintWriter write = null;
		try {
			write = new PrintWriter("test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reviewTable.print(write);
		write.flush();
	}
	
	public static void rate(double score,int amount){
		if(score==0 && amount==0)
			System.out.println("Your phrase contained no words that affect sentiment.");
		else if(score/amount<2 || score==0){
			System.out.format("Score: %.3f", score/amount);
			System.out.println();
			System.out.println("Your phrase was negative.");
		}
		else if(score/amount>2){
			System.out.format("Score: %.3f", score/amount);
			System.out.println();
			System.out.println("Your phrase was positive.");
		}
		else{
			System.out.format("Score: %.3f", score/amount);
			System.out.println();
			System.out.println("Your phrase was perfectly neutral.");
		}
	}

}
