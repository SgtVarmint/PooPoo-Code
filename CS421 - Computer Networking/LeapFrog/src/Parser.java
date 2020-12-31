
public class Parser {
	Token tok;
	Lexan lex;
	
	public Parser(Lexan lex){
		this.lex=lex;
	}
	
	public void trump(){
		expect(Token.Trump);
		expect(Token.leftBrack);
		while(tok==Token.Tweet || tok==Token.Octothorp || tok==Token.Integer || tok==Token.Ident || tok==Token.leftParen || tok==Token.Question || tok==Token.EOL){
			statement();
		}
		expect(Token.rightBrack);
		expect(Token.Impeached);
	}
	
	public void expect(Token sy) {
		if(tok==sy) 
			tok=lex.next();
		else 
			error("Expected: "+sy+" not found.");
	}
	
	public void error(String mesg) {
		System.out.println(mesg);
	}
	
	public void tweetStat() {
		expect(Token.Tweet);
		expression();
	}
	
	public void expression() {
		simpleExp();
		while(tok==Token.LSS || tok==Token.GTR || tok==Token.EQL) {
			expression();
		}
	}
	
	public void simpleExp() {
		term();
		while(tok==Token.Plus || tok==Token.Minus) {
			term();
		}
	}
	
	public void term() {
		factor();
		while(tok==Token.Mult || tok==Token.Divide) {
			term();
		}
	}
	
	public void factor() {
		switch(tok) {
		case Integer:
			expect(Token.Integer);
			break;
		case Ident:
			expect(Token.Ident);
			break;
		case leftParen:
			expect(Token.leftParen);
			expression();
			expect(Token.rightParen);
			break;
		default:
			error("Unexpected Symbol.");
			break;
		}
	}
	
	public void statement(){
		switch(tok){
		case Tweet:
			tweetStat();
			break;
		case Integer:
			
			break;
		case leftParen:
			
			break;
		case Ident:
			expression();
			break;
		case Question:
			
			break;
		case Assignment:
			
			break;
		case EOL:
			tok=lex.next();
			break;
		default:
			error("Unexpected Symbol.");
			break;
		}
	}
}
