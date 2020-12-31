import java.math.*;
public class Primes {

	public static void main(String[] args) {
		long n=179424691;
		long d;
		long startTime, endTime, runTime;
		startTime=System.currentTimeMillis();
		n=46810093859L;//constantL... L makes the constant a Long data type
		for(d=2;d<=n;d++)//or ...;d<Math.sqrt(n);...
			if(n%d==0)
			{
				System.out.println("Not prime. it can be divided by "+d);
				return;
			}
		endTime=System.currentTimeMillis();
		System.out.println("Is prime");
		runTime=endTime-startTime;
		System.out.println("Runtime = " +runTime);
	}

}
