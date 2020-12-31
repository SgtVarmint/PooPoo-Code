import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LifeSquare extends JButton{
	public boolean alive = false, birth = false, death = false;
	public static boolean mouseDown = false;

	public LifeSquare() {
		//addActionListener(new LifeListener());
		addMouseListener(new LifeMouseListener());
	}

	private class LifeMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			int n;
//			n = arg0.getButton();
//			System.out.println("Button clicked: "+n);
			alive = !alive;
			if (alive)
				setBackground(Color.BLACK);
			else
				setBackground(null);

		}

		public void mouseEntered(MouseEvent arg0) {
			if (mouseDown) {
				alive = !alive;
				if (alive)
					setBackground(Color.BLACK);
				else
					setBackground(null);
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			mouseDown = true;
		}

		public void mouseReleased(MouseEvent arg0) {
			mouseDown = false;
		}

	}
	private class LifeListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			alive = !alive;
			if (alive)
				setBackground(Color.BLACK);
			else
				setBackground(null);
		}

	}
}
