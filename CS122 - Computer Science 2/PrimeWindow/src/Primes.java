
public class Primes {
	
	public static void main(String[] args){
	
	boolean prime;
	int counter;
	counter=1;//initializes a counter as 0
	int n;//declares n
	n=1000;
	int d =0;//initializes d
	int i = 1;
	int[] primes;//initializes array primes
	primes=new int[1000];//declares array primes with a size of 1k
	
	primes[0]=2;//sets 'primes[0]'=2
	primes[1]=3;//sets 'primes[1]'=3
	
	for(i=1;i<1000;i++)
		if(d<n)
		{
			d++;
			if(d%2!=0)
				{
					prime=true;
					System.out.println("Prime Number "+counter+" "+prime+", "+d+" is prime up to 1000.");
					counter++;
				}
			else if(d%2==0)
				{
					prime=false;
				}	
		}
	for(i=0;i<primes.length;i++)
	{
		i=primes[i];
		System.out.println(primes[7]);
		return;
	}
	}
}
