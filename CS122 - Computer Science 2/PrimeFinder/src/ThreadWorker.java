
public class ThreadWorker extends Thread{
	private long start, end;
	private String name;
	
	public ThreadWorker(String name, long s, long e) {
		start = s;
		end = e;
		this.name = name;
	}
	
	public void run() {
		long n;
		if (start%2==0) start++;
		for (n=start; n<=end; n+=2) {
			if (isPrime(n))
				System.out.println(name+": "+n);
		}
	}
	
	public boolean isPrime(long n) {
		long i;
		for (i=3; i<= Math.sqrt(n); i+=2)
			if (n%i==0) return false;
		return true;
	}
}
