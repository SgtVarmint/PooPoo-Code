public class HashTest {
	public static void main(String[] args) {
		HashTable quotes = new HashTable();
		
		quotes.put("Henry Ford", "Thinking is the hardest work there is, which is probably the reason why so few engage in it.");		
		quotes.put("Albert Camus", "Don't walk behind me; I may not lead. Don't walk in front of me; I may not follow. Just walk beside me and be my friend.");		
		quotes.put("Clint Eastwood", "I have a very strict gun control policy: if there's a gun around, I want to be in control of it.");		
		quotes.put("Napoleon Bonaparte", "You must not fight too often with one enemy, or you will teach him all your art of war.");		
		quotes.put("Helen Keller", "Walking with a friend in the dark is better than walking alone in the light.");		
		quotes.put("Voltaire", "It is dangerous to be right in matters on which the established authorities are wrong.");		
		quotes.put("Malcolm X", "Education is the passport to the future, for tomorrow belongs to those who prepare for it today.");
		
		if( quotes.containsKey("Malcolm X") )
			System.out.println("Contains Test 1 passed!");
		else
			System.out.println("Contains Test 1 failed!");
		
		if( quotes.containsKey("Malcolm Gladwell") )
			System.out.println("Contains Test 2 failed!");
		else
			System.out.println("Contains Test 2 passed!");
		
		if( quotes.get("Helen Keller").equals("Walking with a friend in the dark is better than walking alone in the light."))
			System.out.println("Get Test 1 passed!");
		else
			System.out.println("Get Test 1 failed!");
		
		if( quotes.get("Aristotle") == null )
			System.out.println("Get Test 2 passed!");
		else
			System.out.println("Get Test 2 failed!");
		
		if( quotes.put("Socrates", "The only true wisdom is in knowing you know nothing.") )
			System.out.println("Put Test 1 passed!");
		else
			System.out.println("Put Test 1 failed!");
		
		if( quotes.put("Malcolm X", "If you have no critics you'll likely have no success.") )
			System.out.println("Put Test 2 failed!");
		else
			System.out.println("Put Test 2 passed!");
	}
}