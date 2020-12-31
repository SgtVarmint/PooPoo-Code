public class Parser {
	Token tok;
	Lexan lex;
	final boolean debug=true;
	
	public Parser(Lexan lex) {
		tok=lex.next();
		this.lex=lex;
	}	

	public void program() {
		System.out.println("Program "+tok);
		while(tok==Token.Print || tok==Token.Ident) 
			Statement();
		expect(Token.EOF);
	}

	//method to expect a certain token or send an error message
	public void expect(Token sy) {
		if(debug) System.out.println("Expect "+sy+" Found "+tok);
		if(tok==sy)
			tok=lex.next();
		else
			error("Oof: "+sy+" not found.");
	}

	//print out error message
	public void error(String msg) {
		System.out.println("ERROR "+msg);
	}

	//BNF rules
	public void Statement() {
		if(debug) System.out.println("Statement "+tok);
		switch(tok) {
		case Print:
			printStatement();
			break;
		case Ident:
			Assignment();
			break;
		default:
			error("Illegal beginning of a statement.");
			break;
		}
		expect(Token.EOL);
	}

	public void printStatement() {
		expect(Token.Print);
		Expression();
	}
	
	public void Assignment() {
		VarRef();
		expect(Token.Equal);
		Expression();
	}
	
	public void Expression() {
		Term();
		while(tok==Token.Plus || tok==Token.Minus) {
			tok=lex.next();
			Term();
		}
	}
	
	public void Term() {
		Factor();
		while(tok==Token.Mult || tok==Token.Divide) {
			tok=lex.next();
			Factor();
		}
	}
	
	public void Factor() {
		switch(tok) {
			case Integer:
				tok=lex.next();
				break;
			case Ident:
				VarRef();
				break;
			case leftParen:
				tok=lex.next();
				Expression();
				expect(Token.rightParen);
				break;
			default:
				error("Illegal token in an expression.");
		}
	}
	
	public void VarRef() {
		expect(Token.Ident);
	}
}
