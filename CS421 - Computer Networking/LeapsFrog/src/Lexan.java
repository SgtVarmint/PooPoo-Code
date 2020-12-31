
public class Lexan {
	private SrcReader src;
	private char ch = ' ';
	private Token[] iniToken = new Token[128];
	private int inum = 0;
	private double rnum = 0;
	private String name = "";

	public Lexan(SrcReader src) {
		this.src = src;
		initialize();
	}

	public int getInum() { return inum; }
	public double getRnum() { return rnum; }
	public String getIdent() { return name; }

	public void initialize() {
		int i;

		for (i=0; i<iniToken.length; i++)
			iniToken[i] = Token.nullToken;
		for (i='0'; i<='9'; i++)
			iniToken[i] = Token.Integer;
		for (i='A'; i<='Z'; i++)
			iniToken[i] = Token.Ident;
		for (i='a'; i<='z'; i++)
			iniToken[i] = Token.Ident;
		iniToken['='] = Token.Equal;
		iniToken['+'] = Token.Plus;
		iniToken['*'] = Token.Mult;
		iniToken['/'] = Token.Divd;
		iniToken['-'] = Token.Minus;
		iniToken['('] = Token.Lparen;
		iniToken[')'] = Token.Rparen;
		iniToken['<'] = Token.Lss;
		iniToken['>'] = Token.Gtr;
		iniToken['!'] = Token.Not;
		iniToken[SrcReader.eof]= Token.eof;
   		//		iniToken[''] = Token.;

	}

	public Token next() {
		Token sy = Token.nullToken;
		while (ch==' ') ch = src.nextch();
		sy = iniToken[ch];
		switch (sy) {
		case Integer:
			inum = 0;
			while (ch>='0' && ch<='9') {
				inum = inum*10+ch-'0';
				ch = src.nextch();
			}
			if (ch=='.') {
				double divisor=10.0;
				sy = Token.Real;
				rnum = inum;
				ch = src.nextch();
				while (ch>='0' && ch<='9') {
					rnum += (ch-'0')/divisor;
					divisor*= 10.0;
					ch = src.nextch();
				}
			}
			break;
		case Ident:
			name = "";
			while (Character.isLetter(ch) || Character.isDigit(ch)) {
				name+=ch;
				ch = src.nextch();
			}
			switch (name) {
			case "if": 		sy = Token.If;
			break;
			case "while": 	sy = Token.While;
			break;
			case "else":	sy = Token.Else;
			break;
			default:
				break;
			}
			break;
		case Lss:
			ch = src.nextch();
			if (ch=='=') {
				sy = Token.Leq;
				ch = src.nextch();
			}
		default:
			ch = src.nextch();
			break;
		}
		return sy;
	}
}
