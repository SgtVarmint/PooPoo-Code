public class MergeThread extends Thread{
	static int i,j,k;
	public MergeThread(int[] list1) {
	}

	public static void split(int[] list,int[] list1,int[] list2){
		for(int size=100000;size<=100000;size+=5000){
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
			
			bubbleSort(list1);
			bubbleSort(list2);
			
			merge(list,list1,list2);
		}
	}

	public static void bubbleSort(int[] list) {
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

	public static void  merge(int[] list,int[] list1,int[] list2){
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

	public void run(){

	}
}

