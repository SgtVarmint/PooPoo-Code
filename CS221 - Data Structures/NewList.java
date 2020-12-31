/*
 * CS221: Assignment 1
 * 	-LinkedList
 *Author: Joe Divinagracia 
 *
 *------------------------
 *Creates a linked list <3
 */
public class NewList {

	private static class Node{//entities for class Node
		public String value;
		public Node next;
		public int index;
	}

	private Node head=null;//sets head and tail to null...
	private Node tail=null;
	private int size=0;//...and size to 0
	private Node current;//to help in indexing through lists

	public NewList() {
		String word;
		Node head,tail,next;	
	}

	private void helper(Node temp, int index, String element) {//for code reuse

		while(index==size) {//while index == size / end of list

			if(current.next==tail) {//if node after current is tail
				temp.next=tail;//node after temp is tail
				temp.index=index;//index of temp is updated to given index

				current.next=temp;//update current.next to temp
				current=current.next;//update current
				size++;//increment size
			}

			else {//if !above
				current=current.next;//just update
			}
		}

		while(current.next!=tail) {//while node after current is not tail

			if(index==0) {//if inserting at head
				temp.index=index;//update index of temp to given index
				temp.next=head;//temp's next points to current head
				head=temp;//update head
				current=current.next;//update current
				size++;//increment
			}

			if(current.index==(index-1)) {//stops one node before desired node
				temp.next=current.next;//sets temp's next pointer to that of current
				temp.index=index;//sets index of temp to given index

				current.next=temp;
				current=current.next.next;
				current.index++;
				size++;
			}

			else if(current.index!=index) {
				if(current.index>index) {
					current=current.next;
					current.index++;
				}
				else
					current=current.next;
			}
		}
	}

	void add(String element) {//This one also works, bit janky, but it works
		//add elements to the last position in list

		Node temp=new Node();//instantiates a new node
		temp.value=element;//stores String element as value of temp
		temp.index=size;//stores index as size
		temp.next=tail;//next node after temp is tail(null)

		if(head==null) {//if list is empty
			head=temp;//stores temp as head and tail
			size++;//increment size
		}
		else {//if list is not empty...
			current=head;
			while(current.next!=tail) {//while the node after the current node is not tail
				current=current.next;//update current to next node
			}

			//when current.next DOES point to tail
			current.next=temp;
			size++;
		}
	}

	void addAtIndex(int index,String element) throws IndexOutOfBoundsException{//Works with helper class
		//add elem to list at pos index, moving elems to list.next()

		Node temp=new Node();//create new node for temp
		temp.value=element;//store element in temp's value

		helper(temp,index,element);

	}

	void addAll(NewList list) throws Exception {
		//add all elems from list to end of list

		//should figure out how to index through the new list and how to append old list to new list
		current=head;
		int i=0;


		while(current.next!=tail) {
			current=current.next;//just another #update #lovethis
		}

		while(current.next==tail) {//while the next node is tail
			while(i<list.size()) {

				Node temp=new Node();//create a new node
				temp.value=list.get(i);//set value to that of the node from list of index i (starts at 0)
				temp.next=tail;//set the node after temp to tail
				temp.index=current.index+1;//increment index of temp 

				current.next=temp;//set the current next node to temp
				current=current.next;//update again #update #probablycouldhavewrittenthisintothehelperclass

				size++;//increment size
				i++;//increment i for the loop
			}
			return;
		}	
	}

	void clear() {//DONE
		//Kinda self explanatory
		//write and call a removeLast method-------idiot you could just do that here
		current=head;
		while(current.next!=null) {//while current.next is not null...
			if(head==null) return;//if head is already null, do nothing
			else {//head != null
				current.next=head.next.next;//current points to node after next node
				head=null;//update head to be null
			}
			size=0;//set size to 0
		}
	}


	boolean contains(String element) {//DONE
		//return true if list contains elem, and false otherwise
		current=head;

		while(current!=tail) {//to make sure current is not tail
			if(current.value!=element) current=current.next;//if c.val is not elem, update
			else if(current.value==element) return true;//or else return true
		}
		return false;//ret's false is elem doesn't exist
	}


	boolean isEmpty() {//DONE
		//ret true if empty, false otherewise
		if(head!=null) return false;//if head is not null(list !empty), ret false
		else return true;//if head is null(list empty), ret true
	}

	String get(int index) throws Exception{//This one works
		//ret element at pos index

		current=head;
		String element=null;

		while(current.index!=index) {//while c.index is not index
			current=current.next;//update
		}
		element=current.value;//set elem to the val of curr
		return element;//ret elem
	}


	int indexOf(String element) {//DONE
		//ret  index of first occurence of elem, and -1 if it dont exist
		current=head;
		int index=0,i=0;//sets index to -1 and will update as needed

		while(current.next!=tail) {//while the next node is not the tail

			if(current.value!=element) {//if the val of current is not elem
				current=current.next;//update current to be next node
				index++;//increment index
			}
			else if(current.value==element){//if the val of current == elem
				index=current.index;//set index to the index of current
				return index;//return
			}
		}
		index=-1;//if none of the above is true, index = -1

		return index;
	}


	int lastIndexOf(String element) {//DONE
		//ret index of last occur of elem, -1 if nonexist
		int index=0,storedIndex=0;
		current=head;

		while(current!=null) {
			if(current.value!=element)//if value of current is not elem
				current=current.next;//update current

			else{//if val is elem\
				storedIndex=current.index;//storedIndex is the index of current node
				current=current.next;
			}
		}

		return storedIndex;
	}


	void remove(int index) {//PASSES RIPPPPP <333333
		//remove elem at pos index, all elems after will be moved to 'list.prev'
		current=head;

		while(current!=tail) {
			if(current.index==index-1) {//stops one node before desired index
				current.next=current.next.next;//skips the current next node directly to the node after
				//current.next=null;//sets next node to null
				current=current.next;
				current.index--;
				size--;
			}
			else
				current=current.next;
		}
	}


	void set(int index,String element) {//DONE
		//change elem at pos index to elem

		current=head;//set current == head
		while(current.index!=index) current=current.next;//while index of current is not index, update current to next node

		current.value=element;//when c.index==index, set vale of current to elem
	}


	int size() {//DONE
		//ret size of list
		return size;//I mean...
	}
}
