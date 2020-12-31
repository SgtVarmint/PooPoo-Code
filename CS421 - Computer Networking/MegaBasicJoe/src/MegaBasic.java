import java.io.FileNotFoundException;

public class MegaBasic {
	public static void main(String[] args) throws FileNotFoundException {
		SrcReader src=new SrcReader("test.txt");
		Lexan lex=new Lexan(src);
		Parser parser=new Parser(lex);
		char ch;
		Token sy=lex.next();
		System.out.println("Parsing Beginning.");
		parser.program();
		System.out.println("Parsing Complete");
	}
}
