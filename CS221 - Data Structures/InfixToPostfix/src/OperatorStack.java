import java.util.NoSuchElementException;

public class OperatorStack<T>{
	@SuppressWarnings("unchecked")
	private T[] array = (T[])new Object[10];
	private int size=0;
	
	public void push(T value){
		if(size==array.length){
			@SuppressWarnings("unchecked")
			T[] temp  = (T[])new Object[size*2];
			for(int i=0;i<size;i++)
				temp[i]=array[i];
			array=temp;
		}
		
		array[size]=value;
		size++;
	}
	
	public T pop(){
		if(size==0)
			throw new NoSuchElementException();
		
		T top = array[size-1];
		size--;
		return top;
	}
	
	public T top(){
		return array[size-1];
	}
	
	public T get(int index){
		return array[index];
	}
	
	public int size(){
		return size;
	}
}
