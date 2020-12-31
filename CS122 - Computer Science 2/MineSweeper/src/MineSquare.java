import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSquare extends JButton{
	boolean mine=false;//initializes boolean mine as false
	int row,col;
	boolean exposed=false;//initializes var exposed as false to start
	int count;
	boolean flagged=false;//initializes var flagged as false to start
	static MineSweeper parent=null;
	int mineTotal;
	int size;
	int mineCount;

	public MineSquare(boolean mine, int row, int col, MineSweeper p, int size){
		this.mine=mine;//'this.' initializes mine as global var mine(false)
		this.row=row;//...initializes row as global var row
		this.col=col;//..............
		parent=p;
		setText("");//...............
		this.size= size;
		addMouseListener(new SquareListener());
		//addActionListener(new SquareListener());
	}

	public void setMine(){
		mine=true;//initializes var mine as true
		//		setText("*");
		//		setBackground(Color.RED);
	}
	public void expose(){//when the square is exposed...
		if(exposed) return;//base case; if the square is exposed, return.
		exposed=true;
		if (mine) {//if the square is a mine
			setText("*");
			setBackground(Color.RED);
			for(int r=0;r<=size;r++)
				for(int c=0;c<=size;c++){
					if (parent.board[r][c].mine){
						parent.board[r][c].setBackground(Color.RED);
						parent.board[r][c].setText("*");
					}
				}
			parent.lose();
			//JOptionPane.showConfirmDialog(null, "GAME OVER, MY MAN. Start a new game?");
		}
		
		else {
			int count = 0;
			if(!flagged){
			setBackground(Color.CYAN);
			for (int i=-1; i<=+1; i++)
				for (int j=-1; j<=+1; j++)
					if (parent.board[row+i][col+j].mine) count++;

			if(count>0) setText(""+count);
			if (count==0) {
				for (int i=-1; i<=+1; i++)
					for (int j=-1; j<=+1; j++)
						if(!parent.board[row+i][col+j].exposed)
							parent.board[row+i][col+j].expose();//get rid of doClick somehow
			}
			parent.win(size);
		}	
		}
		if(flagged){
			return;
		}
	}

	private class SquareListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			int n;
			n=e.getButton();//gets button that was clicked

			if(n==1){//if left-click...
				if(flagged){//...if the square is flagged..
					return;//return(can't click)
				}
				if(!flagged){
					expose();
				}	
			}
			if(n==3){//if right-click...
				if(exposed){
					return;
				}
				if(flagged){//...if the square is not flagged..
					flagged = false;
					moreMines(n);
					setBackground(null);
					setText(null);
					return;
				}

				if(!flagged){
					int mineCount=parent.mineCount--;
					parent.amountMine.setText("Amount of mines: "+(mineCount-1));

					if(mineCount>0){
						flagged=true;
						setBackground(Color.YELLOW);
						setText("|>");
						return;
					}
					
					if(mineCount<=0){
						parent.amountMine.setText("Amount of mines: 0");
						JOptionPane.showMessageDialog(null, "Not enough flags");
						setText(null);
						setBackground(null);
						return;
					}
				}
			}

			if(mine){//if the box is a mine...
				if(parent.board[row][col].mine) expose();
			}
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

	}
public static int amountmine(int x){
	parent.amountMine.setText("Amount of mines: "+parent.mineCount);
	return x;
}
public static int moreMines(int x){
	int mineCount=parent.mineCount++;
		parent.amountMine.setText("Amount of mines: "+(mineCount+1));
		return x;
	}
}

