//100k entities:
//Threads	:  ms
//No Threads: 8090 ms
//No Split	: 15492 ms

public class Merge {
	public static final int numthread=1; 
	public static int[] list;
	public static int[] list1, list2, list3;
	public static int i,j,k,l;
	public static long runTime;

	public static void main(String[] args) throws InterruptedException{

		for(int size=5000;size<=500000;size+=5000){
			list=new int[size];
			for(i=0;i<list.length;i++){
				list[i]=(int)(Math.random()*100000);
			}

			list1=new int[list.length/2];
			list2=new int[list.length-list1.length];

			for(i=0;i<list1.length;i++){
				list1[i]=list[i];
			}
			for(j=0;j<list2.length;j++){
				list2[j]=list[i++];
			}
			runTime=System.currentTimeMillis();

//			MergeThread L1=new MergeThread(list1);
//			MergeThread L2=new MergeThread(list2);
//			L1.start();
//			L2.start();
//			L1.join();
//			L2.join();

//			bubbleSort(list);
//			bubbleSort(list1);			
//			bubbleSort(list2);
			
//			SelectionSort(list1);
//			SelectionSort(list2);

//			SortThread ListOne=new SortThread(list1);
//			SortThread ListTwo=new SortThread(list2);
//			ListOne.start();
//			ListTwo.start();
//			ListOne.join();
//			ListTwo.join();
			
			mergeSort(list);
			
			//System.out.println("1st Ordered List for size: "+size);
			for(j=0;j<list1.length;j++){
				//System.out.println(list1[j]);
			}

			//System.out.println("2nd Ordered List for size: "+size);
			for(j=0;j<list2.length;j++){
				//System.out.println(list2[j]);
			}

			merge(list, list1, list2);
			//System.out.println("Merged Ordered List for size: "+size);
			for(j = 0; j<list.length; j++){
				//System.out.println(list[j]);
			}

			runTime=System.currentTimeMillis()-runTime;
			System.out.println("Runtime for size "+size+": "+runTime);
		}
	}

	static void mergeSort(int[] list) {
		int i,j,k;
		int[] list1, list2;
		list1 = new int[list.length/2];
		list2 = new int[list.length-list1.length];
		// Split the list into two lists
		for (i=0; i<list1.length; i++)
			list1[i] = list[i];
		for (j=0; j<list2.length; j++)
			list2[j] = list[i++];
		if(list1.length>1) mergeSort(list1);
		if(list2.length>1) mergeSort(list2);
		merge(list, list1, list2);
	}

	
	static void  merge(int[] list,int[] list1,int[] list2){
		int i,j,k;
		i=j=k=0;

		while(j<list1.length && k<list2.length){
			if(list1[j]<list2[k]){
				list[i++]=list1[j++];
			}
			else{
				list[i++]=list2[k++];
			}
		}
		while(j<list1.length)
			list[i++]=list1[j++];
		while(k<list2.length)
			list[i++]=list2[k++];
	}

	static void bubbleSort(int[] list){
		int i,j,temp=0;
		for(i=0;i<list.length;i++){
			for(j=1;j< list.length;j++){
				if(list[j-1]>list[j]){
					temp=list[j-1];
					list[j-1]=list[j];
					list[j]=temp;
				}
			}
		}
	}
	
	
	
	static void SelectionSort(int[] list){
		int i,j,s,t;
		
		for(i=0;i<list.length-1;i++){
			s=i;//smallest ,s, val at pos 'i'
			for(j=s+1;j<list.length;j++)
				if(list[j]<list[s]) s=j;
			t=list[i];
			list[i]=list[s];
			list[s]=t;
		}
	}
}