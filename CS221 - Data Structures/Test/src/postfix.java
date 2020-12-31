/*
 * Project 2: Inifx to postfix Expression
 * 
 * 
 * cases:
 * 	(num)(num) == num*num
 * 
 * if charAt(0) == '-' it's negative
 */
import java.util.Scanner;

public class InfixToPostfix {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int i=0;
		double num=0;
		String expression="";//used to parse from character to double
		String stdInfix="";
		String postFix="";
		boolean operatorFlag=false;//to check for multiple operators for optional negative and positive numbers
		boolean decimalFlag=false;//to check for more decimals after inputting a decimal

		OperatorStack stack=new OperatorStack();
		Queue output=new Queue();
		Queue infixQueue=new Queue();//copies queue for converting to postfix


		System.out.print("Enter infix notation: ");
		String infixExp=in.nextLine();

		while(i<infixExp.length()){

			if(infixExp.charAt(i)==' ') i++;//iterates through whitespace and skips

			else if((infixExp.charAt(i)=='+' || infixExp.charAt(i)=='-' || infixExp.charAt(i)=='/' || infixExp.charAt(i)=='*' || infixExp.charAt(i)=='(')) {
				if(!operatorFlag) {
					output.enqueue(infixExp.charAt(i));
					infixQueue.enqueue(infixExp.charAt(i));
					i++;
					operatorFlag=true;
				}
				else {//if the opFlag has been tripped
					if(infixExp.charAt(i)=='+' || infixExp.charAt(i)=='-')//checks only for '+' or '-'
						expression+=infixExp.charAt(i);//shows presence of an option '+' or '-'
					i++;
				}
			}

			else{
				try{
					while(i<infixExp.length() && (Character.isDigit(infixExp.charAt(i)) || infixExp.charAt(i)=='.')) {//if the character is a digit or a decimal
						if(Character.isDigit(infixExp.charAt(i)) || infixExp.charAt(i)=='.') {
							expression+=infixExp.charAt(i);//adds the double to a
							i++;
						}
						else {
							expression+=infixExp.charAt(i);
						}
					}
					num=Double.parseDouble(expression);//parses the data into a Double value
					output.enqueue(num);//puts it in the queue
					infixQueue.enqueue(num);
					expression="";//clears the string
					operatorFlag=false;
				}catch(Exception e){
					System.out.println("Invalid Operand");
					return;
				}
			}
		}//end of while


		System.out.println("Standard Infix Notation: ");

		while(output.size()>0)
			System.out.print(output.dequeue()+" ");//prints out infix notation

		System.out.println("\nPostfix expression: ");

		while(infixQueue.size()>0){//printing out postfix
			
			if(infixQueue.front().equals((char)'/') || infixQueue.front().equals((char)'*')){
				
				while(stack.size()>0){//checks if the stack is not empty
					if(stack.top()=='+' || stack.top()=='-' || stack.top()=='('){//checks for lower precedence operators
						System.out.print(stack.pop());//pops top operator
						stack.push((char)infixQueue.front());//pushes
						infixQueue.dequeue();
					}
				}

				stack.push((char) infixQueue.front());
				infixQueue.dequeue();

			}

			else if(infixQueue.front().equals('+') || infixQueue.front().equals('-')){
				
				while(stack.size()>0){
					if(stack.top()=='('){//checks for lower precedence operators
						System.out.print(stack.pop());//pops top operator
						stack.push((char)infixQueue.front());//pushes
						infixQueue.dequeue();
					}
				}
				stack.push((char) infixQueue.front());
				infixQueue.dequeue();
				
			}
			
			else if(infixQueue.front().equals('(')){
				stack.push((char)infixQueue.front());
				infixQueue.dequeue();

			}
			
			else{//if the value of infixQueue.front() is a number
				System.out.print(infixQueue.dequeue()+" ");
			}
		}
	}
}

public class postfix {

}
