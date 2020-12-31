import java.util.*;

public class JohnsonTrotter {
	
	public final static boolean rightToLeft = false;
	public final static boolean leftToRight = true;
		
	public static void main(String[] args) {
		int n=4;
		johnsonTrotter(n);
	}
	
	public static void johnsonTrotter(int n) {
		int[] array = new int[n]; //used to store the current permutation
		boolean[] direction = new boolean[n];
		
		//print out the initial permutation
		for(int i=0;i<n;i++) {
			array[i] = i+1;
			System.out.print(array[i]);
		}
		
		System.out.print(" ");
		
		for(int i=0;i<n;i++)
			direction[i] = rightToLeft;
		
		for(int i=1;i<factorial(n);i++) 
			printPermutation(array, direction, n);
	}
	
	public static int printPermutation(int[] array, boolean[] direction, int n) {
		int mobileElement = getMobileElement(array, direction, n);
		int position = largestMobile(array, n, mobileElement);
		
		if(direction[array[position-1]-1] == rightToLeft) {
			int temp = array[position-1];
			array[position-1] = array[position-2];
			array[position-2] = temp;
		}
		else if(direction[array[position-1]-1] == leftToRight) {
			int temp = array[position];
			array[position] = array[position-1];
			array[position-1] = temp;
		}
		
		for(int i=0;i<n;i++){
			if(array[i] > mobileElement) {
				if(direction[array[i]-1] == leftToRight) direction[array[i]-1] = rightToLeft;
				
				else if(direction[array[i]-1] == rightToLeft) direction[array[i]-1] = leftToRight;
			}
		}
		
		for(int i:array) System.out.print(i);
		
		System.out.print(" ");
		
		return 0;
	}
	
	public static int getMobileElement(int[] array, boolean[] direction, int n) {
		int previousMobile=0, currentMobile=0;
		
		for(int i=0;i<n;i++) {
			if(direction[array[i]-1] == rightToLeft && i!=0) {
				if(array[i] > array[i-1] && array[i] > previousMobile) {
					currentMobile = array[i];
					previousMobile = currentMobile;
				}
			}
			
			if(direction[array[i]-1] == leftToRight && i!=n-1) {
				if(array[i] > array[i+1] && array[i] > previousMobile) {
					currentMobile = array[i];
					previousMobile = currentMobile;
				}
			}
		}
		
		if(currentMobile == 0 && previousMobile == 0) return 0;
		
		return currentMobile;
	}
	
	public static int largestMobile(int[] array, int n, int mobileElement) {
		for(int i=0;i<n;i++) 
			if(array[i] == mobileElement) return i+1;
		return 0;
	}
	
	public static int factorial(int n) {
		if(n==1)
			return 1;
		else
			return n*factorial(n-1);
	}
}
