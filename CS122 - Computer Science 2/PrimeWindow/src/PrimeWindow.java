import javax.swing.*;
public class PrimeWindow extends JFrame
{
	int[] primes;
	JTextArea display;
	
	public PrimeWindow(String name)//constructor cannot be static or void(says method doesn't return a value)
	{
		this.setTitle(name);//'this' means the method works in this class
		this.setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		primes=new int[10000000];
		display = new JTextArea();
		this.add(display);//adds shit to the JFrame in the Constructor method
		display.setText(name);
		name+="\n"+display.getText();
		display.setText("Austin, Nine is cooler than you");
	}
	
	public void updateText()
	{
		String text;
		text=display.getText();
		text+=text+"\n"+primes[0];
		display.setText(text);
	}
	public static void main(String[]args)
	{
		PrimeWindow win=new PrimeWindow("Prime Window");
		PrimeWindow lose=new PrimeWindow("Loser Window(Austin this is for you)");
		win.primes[0]=2;
		lose.primes[0]=3;
		win.updateText();
		
	}
	
}
