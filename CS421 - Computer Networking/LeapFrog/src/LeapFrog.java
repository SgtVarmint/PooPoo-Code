import java.io.FileNotFoundException;

public class LeapFrog {
	public static void main(String[] args) throws FileNotFoundException {
		SrcReader src=new SrcReader("test.txt");
		Lexan lex=new Lexan(src);
		
		Token sy=lex.next();
		
		while(sy!=Token.EOF) {
			if(sy==Token.Number)
				System.out.printf("%s: %d\n",sy,lex.getInum());
			else if(sy==Token.Ident) 
				System.out.printf("%s: %s\n",sy,lex.getIdent());
			else if(sy==Token.Real)
				System.out.printf("%s: %.2f\n",sy,lex.getReal());
			else
				System.out.println(sy);
			sy=lex.next();
		}
		
//		while(ch!=src.EOF) {
//			System.out.print(ch);
//			ch=src.nextCh();
//		}
		System.out.println("\nEnd of file.");
		
		
	}
}
