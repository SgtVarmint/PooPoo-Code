/*
 * Project 2: Inifx to postfix Expression
 * 
 * 
 * special cases:
 * 	(num)(num) == num*num
 * 
 * if charAt(0) == '-' it's negative
 */
import java.util.Scanner;

public class InfixToPostfix {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int i=0;
		String digit="";

		operatorStack<Character> stack=new operatorStack<Character>();
		operandStack operandStack=new operandStack();


		System.out.print("Enter infix notation: ");
		String infixExp=in.next();


		while(i<infixExp.length()){
			if(Character.isDigit(infixExp.charAt(i)) || infixExp.charAt(i)=='.' || infixExp.charAt(0)=='-'){//if the char at the index is a digit or a decimcal
				digit+=infixExp.charAt(i);//add to string
			}

			double num=Double.parseDouble(digit);
			operandStack.push(num);//pushes operators onto operatorStack

			if(infixExp.charAt(i)=='+' || infixExp.charAt(i)=='-' || infixExp.charAt(i)=='*' || infixExp.charAt(i)=='/' || infixExp.charAt(i)=='('){
				if(infixExp.charAt(i)=='-' && Character.isDigit(infixExp.charAt(i+1))){
					
				}
				stack.push(infixExp.charAt(i));
			}
		}

		System.out.println(infixExp);

	}
}
