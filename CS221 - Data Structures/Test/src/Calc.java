
public class Calc {

	public static void main(String[] args) {
		int[] buckets=new int[10];
		int power=1;
		int val= 600;
		int num=(val%(10*power))/power;
		System.out.println(buckets[num]);
		buckets[num]++;
		System.out.println(buckets[num]);

	}

}
