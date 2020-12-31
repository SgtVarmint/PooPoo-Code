import java.util.Arrays;
import java.util.Random;
/*
 *Author: Joe Divinagracia
 * Sorts random set of int's using 4 sorting algorithms:
 * - Merge Sort
 * - Quick Sort
 * - Heap Sort
 * - Radix Sort
 *
 * for arrays of sizes 10,000 , 100,000 , and 1,000,000
 */
public class SortTest {

	public static void main(String[] args) {
		Random random = new Random();
		int power=10;

		for(int i=power;i<=10;i*=10) {
			int[] quickArray = new int[i];
			for(int j=0;j<quickArray.length;j++)
				quickArray[j] = random.nextInt(1000);

			int[] unsortedQuick = Arrays.copyOf(quickArray, quickArray.length);
			int[] mergeArray = Arrays.copyOf(quickArray,quickArray.length);
			int[] heapArray = Arrays.copyOf(quickArray, quickArray.length);
			int[] radixArray = Arrays.copyOf(quickArray, quickArray.length);


			runTimes(quickArray.length, quickArray,mergeArray,heapArray, radixArray);

			//			if(!isSorted(quickArray) || !isSorted(mergeArray) || !isSorted(heapArray) || !isSorted(radixArray)) {
			//				System.out.println("RIP u homie. One of these shits aint sorted my guy");
			//				break;
			//			}
		}
	}

	public static void runTimes(int length, int[] quickArray, int[] mergeArray, int[] heapArray, int[] radixArray) {
		
		double mergeStartTime = System.nanoTime();
		mergeSort(mergeArray);
		double mergeRunTime = (System.nanoTime() - mergeStartTime)/1000000000.0;


		double quickStartTime = System.nanoTime();
		quickSort(quickArray);
		double quickRunTime = (System.nanoTime() - quickStartTime)/1000000000.0;


		double heapStartTime = System.nanoTime();

		double heapRunTime = (System.nanoTime() - heapStartTime)/1000000000.0;


		double radixStartTime = System.nanoTime();
		radixSort(radixArray, 6);
		double radixRunTime = (System.nanoTime() - radixStartTime)/1000000000.0;

		System.out.format("Array length: %10s%-1d\n"
				+"Quicksort:    %10s%-1.4fs\n"
				+"Merge Sort:   %10s%-1.4fs\n"
				+"Heap Sort:    %10s%-1.4fs\n"
				+"Radix Sort:   %10s%-1.4fs\n\n", " ", length, " ", quickRunTime, " ", mergeRunTime, " ", heapRunTime, " ", radixRunTime);

	}
	/*
	 * Boolean method to check if an array is sorted
	 */
	public static boolean isSorted(int[] array) {
		for (int i = 0; i < array.length-1; ++i)
			if (array[i]>array[i+1])
				return false;
		return true;
	}

	/*
	 * helper method that calls the recursive mergeSort method
	 */
	public static void mergeSort(int[] values) {
		mergeSort(values, 0, values.length);
	}
	/*
	 * Recursive mergeSort method
	 */
	private static void mergeSort(int[] values, int start, int end) {
		//splits values[] into 2 smaller arrays
		int[] values1=new int[values.length/2];
		int[] values2=new int[values.length-values1.length];

		//copies values from values[] to each smaller list
		for(int i=0;i<values1.length;i++)
			values1[i] = values[i];
		for(int i=0;i<values2.length;i++)
			values2[i] = values[values1.length+i];

		//if either of the lists are >1, recursively do it all again
		if(values1.length>1) mergeSort(values1, 0, values1.length);
		if(values2.length>1) mergeSort(values2, values1.length, values2.length);
		merge(values, values1, values2);
	}
	/*
	 * Method that merges all the tiny arrays into one mondo array
	 */
	private static void merge(int[] values, int[] values1, int[] values2) {
		int valuesIndex=0, values1Index=0, values2Index=0;

		while(values1Index<values1.length && values2Index < values2.length) {
			if(values1[values1Index] < values2[values2Index])
				values[valuesIndex++] = values1[values1Index++];
			else
				values[valuesIndex++] = values2[values2Index++];
		}
		while(values1Index < values1.length)
			values[valuesIndex++] = values1[values1Index++];
		while(values2Index < values2.length)
			values[valuesIndex++] = values2[values2Index++];
	}

	/*
	 * helper method to call the recursive quickSort method
	 */
	public static void quickSort(int[] values) {
		quickSort(values,0,values.length-1);
	}
	/*
	 * Recursive quickSort method
	 */
	private static void quickSort(int[] values, int start, int end) {
		if(start>=end) return;

		else {
			int partition = partition(values, start, end);

			quickSort(values, start, partition-1);
			quickSort(values, partition+1, end);
		}
	}
	/*
	 * Partition method
	 */
	private static int partition(int[] values, int left, int right) {
		int pivot = values[right];//set pivot to last element in the array
		int index=left;//index =0
		for(int i=left;i<right;i++) {
			if(values[i] <= pivot) {
				int temp = values[i];//swap
				values[i] = values[index];
				values[index] = temp;
				index++;
			}
		}
		//swaps the value at values[index] with the pivot
		int temp = values[index];
		values[index] = values[right];
		values[right] = temp;

		return index;
	}


	public static void radixSort(int[] array) {
		radixSort(array,6);
	}

	private static void radixSort(int[] array, int digits) {
		int[] scratch = new int[array.length];
		int[] buckets=new int[10];

		int power=1;
		for(int i=0;i<digits;i++) {
			for(int values: array) {
				int num = (values%(10*power))/power;
				buckets[num]++;
			}
			int count=0, k=0;
			for(int j=0;j<buckets.length;j++) {
				if(buckets[j] > 0) {
					count+=buckets[j];
					int bucket=0;
					while(k<count && k<scratch.length) {
						if((array[bucket]%(10*power))/power == j) {
							scratch[k] = array[bucket];
							k++;
						}
						bucket++;
					}
				}
			}
			//array clearing
			for(int j=0;j<buckets.length;j++)
				buckets[j] = 0;
			power*=10;
			for(int j=0;j<array.length;j++)
				array[j] = scratch[j];

		}	
	}

	public static void heapSort(int[] array) {

	}
}