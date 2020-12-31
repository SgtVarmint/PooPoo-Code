/**
 *  lab 8 - shuffling a deck of cards 
 * @author Sean O'Brien
 * @since 3/27/2020
 * 
 */
public class Poker {

	/**
	 * initialize a deck of cards
	 * @return an array of Strings representing the a deck of 52 cards
	 * */
	
	public static String[] generateCards() {
		
		String[] suits = {"clubs", "diamonds", "hearts", "spades"};
		String[] values = {"ace", "jack", "queen", "king" , "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		
		
		String[] cards = new String[52];
		
		for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < values.length; j++) {
                cards[suits.length*i + j] = values[j] + "-" + suits[i];
            }
        }
			
			for(int i = 0; i< cards.length; i++)
				System.out.println(cards[i]);

		return cards;
	}
	
	
	/**
	 * randomly shuffle the cards
	 * @return an array of Strings representing the a deck of 52 cards
	 * */
	public static String[] shuffle(String[] cards) {
		// TODO: shuffle the card deck
		
		
		return cards;
	}
	
	
	
	public static void main(String[] args) {
		// TODO: to generate the card deck, call the generateCards() method and print 
		generateCards();
		// TODO: to shuffle the cards, call the shuffle() method and print the shuffled deck

	}
	

}