
public class Recurse {
	public static void main(String[] args) {
		int n=50;
		long startTime = System.currentTimeMillis();
		System.out.println(tailFib(n));
		System.out.printf("Time to find the %d Fibonacci number using tail recursion: %dms\n", n, System.currentTimeMillis()-startTime);
		
		startTime = System.currentTimeMillis();
		System.out.println(normalFib(n));
		System.out.printf("Time to find the %d Fibonacci number normally: %dms\n", n, System.currentTimeMillis()-startTime);
		

	}

	public static long normalFib(long n) {
		if(n==0) return 0;
		if(n==1) return 1;
		else return normalFib(n-1)+normalFib(n-2);
	}
	
	public static long tailFib(long n) {
		return go(n,0,1);
	}
	
	public static long go(long n, long a, long b) {
		if(n==0) return a;
		if(n==1) return b;
		else return go(n-1, b, a+b);
	}
	
}
