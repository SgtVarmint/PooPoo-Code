
public class Tree {
	
	public static class Node{
		public int key;
		public Object value;
		public Node right;
		public Node left;
	}
	
	private Node root = null;
	
	public void put(int key, Object value) {
		root = put(root, key, value);
	}
	
	private static Node put(Node node, int key, Object value) {
		if(node==null) {
			node = new Node();
			node.key = key;
			node.value = value;
		}
		
		else if(key == node.key)
			node.value = value;
		else if(key < node.key)
			node.left = put(node.left, key, value);
		else
			node.right = put(node.right, key, value);
		return node;
	}
	
	public Object get(int key) {
		return get(root, key);
	}
	
	private static Object get(Node node, int key) {
		if(node==null)
			return null;
		else if(key == node.key)
			return node.value;
		else if(key<node.key)
			return get(node.left, key);
		else
			return get(node.right, key);
	}
	
	public int count() {
		return count(root);
	}
	
	private static int count(Node node) {
		if(node==null)
			return 0;
		else {
			int count = 1;
			count+=count(node.left);
			count+=count(node.right);
			return count;
		}
	}
}
