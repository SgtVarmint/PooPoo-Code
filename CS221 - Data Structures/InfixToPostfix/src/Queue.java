import java.util.NoSuchElementException;

public class Queue<T> {
	@SuppressWarnings("unchecked")
	private T[] data=(T[])new Object[10];
	private int start=0;
	private int size=0;

	public void enqueue(T value){
		if(size==data.length){
			@SuppressWarnings("unchecked")
			T[] temp=(T[]) new  Object[size*2];
			for(int i=0;i<size;i++)
				temp[i]=data[(start+i)%data.length];
			start=0;
			data=temp;
		}
		
		data[(start+size)%data.length]=value;
		size++;
	}
	
	public T dequeue(){
		if(size==0)
			throw new NoSuchElementException();
		
		T result=data[start];
		start=(start+1)%data.length;
		size--;
		return result;
	}
	
	public T front(){
		return data[start];
	}
	
	public int size(){
		return size;
	}
}
