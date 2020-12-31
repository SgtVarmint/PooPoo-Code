
public class PrimeFinder {
	final static int nthread=8;
	
	static long start = 10_000_000_000_000_000L;
	static long end   = 10_000_000_000_002_000L;
	
	public static void main(String[] args) throws InterruptedException{
		
		long startTime,runTime;
		long range, s;
		int i;
		
		range=end-start+1;
		range=range/nthread;
		s=start;
		
		ThreadWorker[] matrix=new ThreadWorker[nthread];
		for(i=0;i<nthread;i++){
			matrix[i]=new ThreadWorker("Thread "+i,s,s+range);
			s=s+range;
		}
		
		startTime=System.currentTimeMillis();
		
		for(i=0;i<nthread;i++)
			matrix[i].start();
		
		for(i=0;i<nthread;i++)
			matrix[i].join();
		
		runTime=System.currentTimeMillis()-startTime;
		System.out.println("Run Time: "+runTime+" ms");
	}
	
}
