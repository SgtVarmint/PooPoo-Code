/**
 * This project is a Sentiment Analyzer used for evaluating expressions submitted by the user to see if it is posotive negative or neutral
 * Course:	CS499
 * Assignment:	Project 3
 *
 * @author	Eric Nawrocki
 * @author  Joe Divinagracia
 * @version 	1.0, 11/08/2017 
 */
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class SentimentAnalyzer {

	public static void main(String[] args) {
		WordTable stopTable = new WordTable();//WordTable for storing stop words
		WordTable reviewTable = new WordTable();//WordTable for storing review words
		Scanner scan = new Scanner(System.in);//for reading user input
		String stop;//stop file location
		do{
		System.out.println("Enter a stop word file: ");
		stop = scan.nextLine();
		if(!new File(stop).exists())
			System.out.println("File not found, please try again.");
		}while(!new File(stop).exists());
		String review;//review file location
		do{
		System.out.println("Enter a review file: ");
		review = scan.nextLine();
		if(!new File(review).exists())
			System.out.println("File not found, please try again.");
		}while(!new File(review).exists());
		Pattern pattern = Pattern.compile("([a-z]+[a-z'-]*[a-z]+)|[a-z]");//pattern to match against review file for reading words
		
		try(Scanner stopFile = new Scanner(new File(stop))) {//scanner for reading stop file
			while(stopFile.hasNext())
				stopTable.add(stopFile.next(),2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Scanner reviewFile = new Scanner(new File(review))) {//scanner for reading review file
			while(reviewFile.hasNextLine()){
				String line = reviewFile.nextLine().toLowerCase();//reads the line and turns it to all lowercase
				int score = line.charAt(0);//the score of each review
				score = score - '0';
				Matcher match = pattern.matcher(line);//used to find words that match the pattern object's criteria
				while(match.find()) 
					if(!stopTable.contains(line.substring(match.start(),match.end())))
						reviewTable.add(line.substring(match.start(),match.end()),score);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String input;//for reading yes or no values when asked to proceed
		String phrase;//stores the phrase to be analyzed
		double score;//the total score of the words in the phrase
		int amount;//the amount of review words in the phrase
		do{
			score=0;
			amount=0;
			System.out.println("Would you like to analyze a phrase? (yes/no)");
			input = scan.nextLine().toLowerCase();
			switch(input){
			case "yes":
				System.out.println("Enter phrase: ");
				phrase = scan.nextLine();
				Matcher match = pattern.matcher(phrase);//used to find words that match the pattern object's criteria
				while(match.find())					
					if(!stopTable.contains(phrase.substring(match.start(),match.end()))){
						score += reviewTable.getScore(phrase.substring(match.start(),match.end()));
						amount++;
				}
				SentimentAnalyzer.rate(score, amount);
				break;
			case "no":
			default:
				break;
			}
			
		}while(!input.equals("no"));
		PrintWriter write = null;//used to write out the words to the file
		try {
			write = new PrintWriter(review +".words");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reviewTable.print(write);
		write.flush();
		scan.close();
	}
	/**
	 * divides the score by the amount of words to find the rating of the phrase
	 * @param score
	 * @param amount
	 */
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
