import java.util.Random;
public class Randomness {

	public static void main(String[] args) {
		Random random = new Random();
		
		int[] array=new int[10];
		
		for(int i=0;i<array.length;i++)
			array[i] = random.nextInt(10);
		
		for(int i=0;i<array.length;i++)
			System.out.println(array[i]);
	}

}
