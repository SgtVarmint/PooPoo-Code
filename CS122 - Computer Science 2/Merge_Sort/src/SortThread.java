
public class SortThread extends Thread{
	int[] data;
	
	public SortThread(int[] list){
		data=list;
	}
	
	public void run(){
		SelectionSort(data);
	}
	
	void SelectionSort(int[] list){
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
