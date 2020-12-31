
public class Lexan {
	private SrcReader src;
	private char ch=' ';
	private Token iniToken[] = new Token[128];
	private int inum=0;
	double rnum=0.0;
	private String name="";

	public Lexan(SrcReader src){
		this.src=src;
		initialize();
	}

	public int getInum() {return inum;}
	public String getIdent() {return name;}
	public double getReal() {return rnum;}


	public void initialize() {
		int i;
		for(i=0;i<iniToken.length;i++)
			iniToken[i]=Token.nullToken;
		for(i='0';i<'9';i++)
			iniToken[i]=Token.Number;
		for(i='a';i<'z';i++)
			iniToken[i]=Token.Ident;
		for(i='A';i<'Z';i++)
			iniToken[i]=Token.Ident;
		iniToken['=']=Token.Equal;
		iniToken['+']=Token.Plus;
		iniToken['-']=Token.Minus;
		iniToken['*']=Token.Mult;
		iniToken['/']=Token.Divide;
		iniToken['(']=Token.leftParen;
		iniToken[')']=Token.rightParen;
		iniToken['<']=Token.LSS;
		iniToken['>']=Token.GTR;
		iniToken['!']=Token.NOT;
		iniToken[SrcReader.EOF]=Token.EOF;
	}

	public Token next() {
		Token sy=Token.nullToken;
		while(ch==' ') ch=src.nextCh();//skips whitespace
		sy=iniToken[ch];
		switch(sy) {
		case Number:
			inum=0;
			while(ch>='0' && ch<='9') {
				inum=inum*10+ch-'0';
				ch=src.nextCh();
			}
			if(ch=='.') {
				double divisor=10.0;
				sy=Token.Real;
				ch=src.nextCh();
				while(ch>='0' && ch<='9') {
					rnum=inum+(ch-'0')/divisor;
					divisor*=10.0;
					ch=src.nextCh();
				}
			}
			break;
		case Ident:
			name="";
			while(Character.isLetter(ch) || Character.isDigit(ch)) {
				name+=ch;
				ch=src.nextCh();
			}
			switch(name) {
			case"if": 		sy=Token.If;
			break;
			case"else": 	sy=Token.Else;
			break;
			case"while": 	sy=Token.While;
			break;
			case"for": 		sy=Token.For;
			break;
			case"goto": 	sy=Token.Goto;
			break;
			case"do": 		sy=Token.Do;
			break;
			default:
				break;
			}
			break;
		case LSS:
			ch=src.nextCh();
			if(ch=='=') {
				sy=Token.LEQ;
				ch=src.nextCh();
			}
			break;
		default://following statement was moved up from above the return to handle non-spaces and special characters in an expression
			ch=src.nextCh();
			break;
		}
		return sy;
	}
}
