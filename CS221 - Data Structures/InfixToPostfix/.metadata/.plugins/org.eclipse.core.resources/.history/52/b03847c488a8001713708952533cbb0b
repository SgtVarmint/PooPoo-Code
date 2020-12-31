import java.util.NoSuchElementException;

public class operandStack {

	private double[] array = new double[10];
	private int size=0;
	
	public void push(double value){
		if(size==array.length){
			double[] temp  = new double[size*2];
			for(int i=0;i<size;i++)
				temp[i]=array[i];
			array=temp;
		}
		
		array[size]=value;
		size++;
	}
	
	public  double pop(){
		if(size==0)
			throw new NoSuchElementException();
		
		double top = array[size];
		size--;
		return top;
	}
	
	public double get(int index){
		return array[index];
	}

}
