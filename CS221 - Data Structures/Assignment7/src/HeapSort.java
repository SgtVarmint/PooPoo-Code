
public class HeapSort {
	
	public static void heapSort(int[] array) {
		for(int i=array.length/2-1;i >= 0;i--) 
			heapify(array, array.length, i);
		
		for(int i=array.length-1;i>=0;i--) {
			int temp = array[i];
			array[i] = array[0];
			array[0] = temp;
			heapify(array, i,0);
		}
	}

	public static void heapify(int[] array, int size, int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int largest = i;
		
		if(left < size && array[left] > array[largest]) 
			largest = left;
		if(right < size && array[right] > array[largest])
			largest = right;
		
		if(largest != i) {
			int temp = array[i];
			array[i] = array[largest];
			array[largest] = temp;
			heapify(array, size, largest);
		}
		
	}

	private static void bubbleDown(int[] array, int index) {
		while(2*index+1 < array.length) {
			int swap = index;
			if(2*index< array.length && array[2*index+1] < array[swap])
				swap = 2*index+1;
			if(2*index < array.length && array[2*index+1] < array[swap])
				swap = 2*index+2;
			
			if(swap == index)
				return;
			
			int temp = array[index];
			array[index] = array[swap];
			array[swap] = temp;
			index=swap;
		}
	}
}
