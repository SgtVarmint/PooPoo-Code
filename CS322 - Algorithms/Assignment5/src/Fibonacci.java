
public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(fibonacci(6));
	}
	
	static int fibonacci(int initNum) {
		if(initNum <= 0)
			return 0;
		if(initNum == 1)
			return 1;
		return fibonacci(initNum-1) + fibonacci(initNum-2);
	}

}
