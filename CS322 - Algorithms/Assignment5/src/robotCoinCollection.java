
public class robotCoinCollection {

	public static void main(String[] args) {
		int[][] coins = new int[10][10];
		coins[0][0] = 1;
		coins[1][4] = 1;
		coins[1][7] = 1;
		coins[2][6] = 1;
		coins[3][3] = 1;
		coins[3][4] = 1;
		coins[5][6] = 1;
		coins[6][9] = 1;
		coins[7][5] = 1;
		coins[9][0] = 1;
		
		System.out.printf("Max coins: %d\n", robotCoinCollect(coins));
	}

	static int robotCoinCollect(int coins[][]) {
		int rowLength = coins.length;
		int colLength = coins[0].length;
		int[][] finalArray = new int[rowLength][colLength];
		
		finalArray[0][0] = coins[0][0];
		
		for(int col=1;col<colLength;++col) 
			finalArray[0][col] = finalArray[0][col-1] + coins[0][col];
		
		for(int row=1;row<rowLength;++row) {
			finalArray[row][0] = finalArray[row-1][0] + coins[row][0];
			for(int col=1;col<colLength;++col)
				finalArray[row][col] = max(finalArray[row-1][col], finalArray[row][col-1]) + coins[row][col];
		}
		
		return finalArray[rowLength-1][colLength-1];
	}
	
	static int max(int i, int j) {
		return i>j ? i : j;
	}
	
}
