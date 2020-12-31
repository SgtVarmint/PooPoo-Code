import javax.swing.*;

public class Frames 
{
	public static void main(String[] args)
	{
		JFrame x=null,y;
		x=new JFrame();
		x.setTitle("First Window, my guy");
		x.setSize(400, 400);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.setVisible(true);
		x.setLocationRelativeTo(null);
		
		y=new JFrame();
		y.setTitle("Second Window");
		y.setSize(300, 300);
		y.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		y.setVisible(true);
		y.setLocationRelativeTo(x);
		x.setLocation(800,600);
	}
}
