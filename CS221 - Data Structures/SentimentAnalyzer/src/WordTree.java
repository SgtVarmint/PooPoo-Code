import java.io.PrintWriter;

public class WordTree {
	Node root = null;

	private static class Node {
		public String word;
		public int score;
		public int count;
		public boolean black;
		public Node left;
		public Node right;
	}

	
	/**
	 * adds word to the red black tree
	 * @param word
	 * @param score
	 */
	public void add(String word, int score){
		root = addWord(this.root,word,score);
		root.black=true;
	}
	
	
	/**
	 * stores the words into the tree and makes appropriate rotations to red black tree
	 * 
	 * @param root
	 * @param word
	 * @param score
	 * @return
	 */
	private Node addWord(Node root ,String word ,int score){
		
		if(root==null){
			Node node = new Node();
			node.word = word;
			node.score = score;
			node.count = 1;
			node.black=false;
			return node;
		}
		else{
			int value = root.word.compareTo(word);
			if(value<0)
				root.right=addWord(root.right,word,score);
			else if(value>0)
				root.left = addWord(root.left,word,score);
			else{
				root.score+=score;
				root.count++;
			}
			
			if(isRed(root.right) && !isRed(root.left))
				root = rotateLeft(root);
			if(isRed(root.left) && isRed(root.left.left))
				root = rotateRight(root);
			if(isRed(root.left) && isRed(root.right))
				flipColors(root);
			
			return root;
		}
	}
	
	
	/**
	 * flips red to black and vice versa as needed
	 * @param root
	 */
	private void flipColors(Node root) {
		root.black=false;
		root.right.black=true;
		root.left.black=true;
	}

	
	/**
	 * checks to see if a node is red
	 * @param x
	 * @return
	 */
	private boolean isRed(Node x){
		if(x==null)
			return false;
		return !x.black;
	}
	
	
	/**
	 * Left rotation method for red black tree
	 * @param root
	 * @return
	 */
	public Node rotateLeft(Node root){
		Node temp=root.right;
		root.right=temp.left;
		temp.left=root;
		temp.black=root.black;
		root.black=false;
		return temp;
	}

	
	/**
	 * Right rotate method
	 * @param root
	 * @return
	 */
	public Node rotateRight(Node root){
		Node temp=root.left;
		root.left=temp.right;
		temp.right=root;
		temp.black=root.black;
		root.black=false;
		return temp;
	}

	
	/**
	 * Checks tree for a word and returns true if word was found
	 * @param word
	 * @return
	 */
	public boolean contains(String word){
		return found(root,word);
	}

	
	/**
	 * returns true if the specified word was found
	 * @param root
	 * @param word
	 * @return
	 */
	private boolean found(Node root,String word){
		if(root==null)
			return false;
		else if(root.word.equals(word))
			return true;
		else {
			int value=root.word.compareTo(word);

			if(value<0)
				return found(root.right,word);
			else
				return found(root.left,word);
		}
	}

	
	/**
	 * Calls the getScore method
	 * @param word
	 * @return
	 */
	public double getScore(String word){//assume word passed is always valid?
		if(!contains(word))
			return 2.0;
		else
			return getScore(root,word);

	}
	

	/**
	 *Gets the score attributed to each word in the reviews text file
	 * @param root
	 * @param word
	 * @return
	 */
	private double getScore(Node root,String word){
		if(root.word.equals(word))
			return root.score/(double)root.count;
		else {
			int value=root.word.compareTo(word);

			if(value<0)
				return getScore(root.right,word);
			else
				return getScore(root.left,word);
		}
	}

	
	/**
	 * Calls the printAll method
	 * @param out
	 */
	public void print( PrintWriter out ){
		printAll(out,root);
	}

	
	/**
	 * This method prints out each word in the WordTree in alphabetical order, followed by a tab, 
	 * followed by its total score, followed by a tab, followed by its count, 
	 * followed by a newline. It makes sense to adapt an inorder traversal to this task.
	 * @param out
	 * @param root
	 */
	private void printAll(PrintWriter out,Node root){
		
		if(root==null){
			return;
		}
		else{
			printAll(out,root.left);
			out.println("Word: "+root.word+", score: "+root.score+", count: "+root.count);
			printAll(out,root.right);
		}
	}



}
