import java.util.NoSuchElementException;

public class operatorStack<T>{
	private T[] array = (T[])new Object[10];
	private int size=0;
	
	public void push(T value){
		if(size==array.length){
			T[] temp  = (T[])new Object[size*2];
			for(int i=0;i<size;i++)
				temp[i]=array[i];
			array=temp;
		}
		
		array[size]=value;
		size++;
	}
	
	public <T> T pop(){
		if(size==0)
			throw new NoSuchElementException();
		
		T top = (T)array[size];
		size--;
		return top;
	}
	
	public <T> T get(int index){
		return (T)array[index];
	}

}