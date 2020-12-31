import java.io.PrintWriter;

public class WordTable {
	WordTree[] table;
	
	public WordTable(){
		table = new WordTree[26];
		for(int i=0;i<table.length;i++)
			table[i] = new WordTree();
	}
	
	/**
	 * Adds each word to the appropriate list in the tree based on the first character of the word
	 * @param word
	 * @param score
	 */
	public void add(String word, int score){
		int index = word.toLowerCase().charAt(0)-'a';
		table[index].add(word, score);
	}

	/**
	 * Gets the score for each word
	 * @param word
	 * @return
	 */
	public double getScore(String word){
		int index = word.toLowerCase().charAt(0)-'a';
		return table[index].getScore(word);
	}

	/**
	 * Returns true if the word is in the table
	 * @param word
	 * @return
	 */
	public boolean contains(String word){
		int index = word.toLowerCase().charAt(0)-'a';
		return table[index].contains(word);
	}
	
	/**
	 * Calls the print method from the WordTree class
	 * @param out
	 */
	public void print(PrintWriter out){
		for(WordTree tree : table)
			tree.print(out);
	}
}