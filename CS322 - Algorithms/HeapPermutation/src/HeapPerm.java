
public class HeapPerm {
	public static void main(String[] args) {
//		int array[]={1,2};
//		int array[]={1,2,3};
		int array[]= {1,2,3,4};
		heapPermutation(array, array.length);
	}
	
	public static void heapPermutation(int[] array, int n) {
		if(n==1) {
			printArray(array);
			System.out.println();
		}
		
		else {
			for(int i=0;i<n;i++) {
				heapPermutation(array, n-1);
				if(n%2!=0){
					int temp = array[0];
					array[0] = array[n-1];
					array[n-1] = temp;
				}
				else {
					int temp = array[i];
					array[i] = array[n-1];
					array[n-1] = temp;
				}
			}
		}
	}
	
	public static void printArray(int[] array) {
		for(int i:array)
			System.out.print(i+" ");
	}
}
