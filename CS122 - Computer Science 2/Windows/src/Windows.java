import javax.swing.JFrame;

public class Windows extends JFrame{

	static int size=300;
	
	public static void main(String[] args) {
		Windows glass;
		glass=new Windows();
		glass.setSize(size,size);
		glass.setTitle("My Window");
		glass.setVisible(true);
	}

}
