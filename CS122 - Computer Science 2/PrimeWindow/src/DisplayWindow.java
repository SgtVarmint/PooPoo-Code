import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DisplayWindow extends JFrame{
	
	String text;
	
	JButton setClear=new JButton("Clear");
	JTextField display=new JTextField();
	JScrollPane pane=new JScrollPane(display);
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	
	public DisplayWindow(){
		setTitle("Display Window");
		setSize(200,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
//responsible for only South Panel(Region)
		setClear.addActionListener(new ClearListener());
		southPanel.add(setClear);
		add(southPanel,BorderLayout.SOUTH);
		
//responsible for only Center Panel(Region)		
		add(pane,BorderLayout.CENTER);
		setVisible(true);
	}
	

public void addInt(int num){
	String s;
	text+=num+"\n";
	display.setText(text);
}
}
