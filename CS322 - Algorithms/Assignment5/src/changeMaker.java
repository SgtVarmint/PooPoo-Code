
public class changeMaker {

	public static void main(String[] args) {
		int coins[] = {25, 25, 5, 10, 1, 10, 10, 25};
		int change = 10;
		int perms = makeChange(coins, coins.length, change);
		System.out.println(perms);
	}

	static int makeChange(int[] coins, int size, int change) {
		if(change==0) return 1;
		if(change<0) return 0;
		if(size<=0 && change>=1) return 0;
		return makeChange(coins, size-1, change) + makeChange(coins, size, change-coins[size-1]);
	}
	
}
