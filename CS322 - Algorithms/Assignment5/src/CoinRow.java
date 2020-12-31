
public class CoinRow {

	public static void main(String[] args) {
		int coins[] = {5, 1, 2, 10, 6, 2};
		int finalTable[] = new int[coins.length+1];
		
		finalTable[0] = 0;
		finalTable[1] = coins[0];
		
		for(int i=2;i<=coins.length;++i) 
			finalTable[i]= max(coins[i-1]+finalTable[i-2], finalTable[i-1]);
		
		System.out.print("| ");
		for(int i:finalTable)
			System.out.print(i+" | ");
		
		System.out.printf("\n\nMaximum amount: %d\n", finalTable[finalTable.length-1]);
	}

	static int max(int i, int j) {
		if(i>j) return i;
		return j;
	}

}
