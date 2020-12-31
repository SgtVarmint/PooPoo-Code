
public class HashTable {
	private int size=0;
	private int power=10;

	Node[] table=new Node[1<<power];


	private static class Node{
		public int key;
		public Object value;
		public Node next;
	}
	private int hash(String quote) {
		return (quote.hashCode() & 0x7fffffff)%table.length;
	}

	public int get(int key) {
		int index=hash(key);

	}

	public void put(int key,Object value) {
		double loadFactor=size/(double) table.length;
		if(loadFactor>=0.75) {
			Node[] temp=table;
			table=new Node[table.length*2];
			power++;
			size=0;
			for(int i=0;i<table.length;i++) {
				Node list=table[i];
				while(list!=null) {
					put(list.key,list.value);
					list=list.next;
				}
			}
		}
		int index=hash(key);
	}

	public containsKey(int key) {
		
	}
}
