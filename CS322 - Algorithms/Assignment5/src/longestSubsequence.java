
public class longestSubsequence {
	public static void main(String[] args) {
		String str1="educational", str2="advantage";
		
		System.out.printf("Length of longest common subseqence: %d\n", longestSubseq(str1, str2, str1.length(), str2.length()));
	}
	
	static int longestSubseq(String sequence1, String sequence2, int seq1Length, int seq2Length) {
		if(seq1Length == 0 || seq2Length == 0)
			return 0;
		if(sequence1.charAt(seq1Length-1)  == sequence2.charAt(seq2Length-1))
			return 1+longestSubseq(sequence1, sequence2, seq1Length-1, seq2Length-1);
		else
			return max(longestSubseq(sequence1, sequence2, seq1Length, seq2Length-1), longestSubseq(sequence1, sequence2, seq1Length-1, seq2Length));
	}
	
	static int max(int i, int j) {
		return i>j ? i : j;
	}
}
