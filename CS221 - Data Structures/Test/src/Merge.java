
public class Merge {

	public static void main(String[] args){

		int[] list;
		int size;

		int i;
		for(size = 5000; size<= 1000000; size+=5000){
			list = new int[size];
			for(i=0; i<list.length; i++)
				list[i] =  (int) (Math.random()*10000);
			double startTime = System.currentTimeMillis();
			mergesort(list);
			double runTime = System.currentTimeMillis() - startTime;
			
			if(isSorted(list))
				System.out.format("Merge sort took %.4f s to run for list size %d\n",runTime/1000,list.length);
			else
	            System.out.print("\nThe shit ain't sorted b. RIP fam X_X");

//			System.out.println("For an array of " + size + " intergers, the run time was " + runTime + "ms");
//			for(i=0; i<list.length; i++)
//				System.out.println(list[i]);

		}
	}
	
	public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length-1; ++i)
            if (array[i]>array[i+1])
                return false;
        return true;
    }
	
	public static void mergesort(int[] sortlist){
		int i,j;
		int[] list1, list2;
		list1 = new int[sortlist.length/2];
		list2 = new int[sortlist.length - list1.length];
		for(i=0; i<list1.length; i++)
			list1[i]=sortlist[i];
		for(j=0; j<list2.length; j++)
			list2[j]=sortlist[i++];
		if(list1.length>1) mergesort(list1);
		if(list2.length>1) mergesort(list2);
		merge(list1, list2, sortlist);
	}

	public static void merge(int[] list1, int[] list2, int[] sortlist){
		int i,j,k;
		i=j=k=0;
		while(j<list1.length && k<list2.length){
			if(list1[j]<= list2[k])
				sortlist[i++] = list1[j++];
			else
				sortlist[i++] = list2[k++];
		}
		while(j<list1.length)
			sortlist[i++] = list1[j++];
		while(k<list2.length)
			sortlist[i++] = list2[k++];
	}

}
/* Run Times
 * 1,000 2ms
 * 10,000 7ms
 * 100,000 26ms
 * 1,000,000 292ms
 */


