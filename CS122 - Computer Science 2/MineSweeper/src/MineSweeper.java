import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper extends JFrame{
	JPanel boardPanel=new JPanel();
	JPanel controlPanelSouth=new JPanel();
	JPanel controlPanelNorth=new JPanel();
	JButton newGame=new JButton("New Game");
	JButton close=new JButton("Close");
	int mineCount;
	JLabel amountMine=new JLabel();
	int flagCount;
	JLabel amountFlag=new JLabel();
	MineSquare[][] board;

	public MineSweeper(int size){
		board=new MineSquare[size+2][size+2];
		setTitle("Minesweeper");
		//setSize(size*25,size*25+20);
		setExtendedState(Frame.MAXIMIZED_BOTH);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		boardPanel.setLayout(new GridLayout(size,size));



		for(int r=0;r<size+2;r++)
			for(int c=0;c<size+2;c++){
				board[r][c]=new MineSquare(false,r,c,this,size);
			}
		for(int i=0;i<size+2;i++){
			board[0][i].exposed=true;
			board[size+1][i].exposed=true;
			board[i][0].exposed=true;
			board[i][size+1].exposed=true;
		}

		for(int r=1;r<size+1;r++)
			for(int c=1;c<size+1;c++){
				boardPanel.add(board[r][c]);
				if(Math.random()<0.10){
					board[r][c].setMine();
					mineCount++;
					//flagCount++;
				}
			}
		add(controlPanelNorth, BorderLayout.NORTH);
		//amountMine.setText("Amount of mines: "+mineCount);
		MineSquare.amountmine(mineCount);
		controlPanelNorth.add(amountMine);
		
		add(boardPanel,BorderLayout.CENTER);

		newGame.addActionListener(new newGameListener());
		close.addActionListener(new closeListener());
		controlPanelSouth.add(newGame);
		controlPanelSouth.add(close);
		add(controlPanelSouth,BorderLayout.SOUTH);
		setVisible(true);
	}
	
//	public void mineTotal(int amount){
//		mineCount+=amount; 
//		amountMine.setText("Amount of mines: "+mineCount);
//	}

	public class newGameListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//setVisible(false);
			dispose();

			int size;
			size= Integer.parseInt(JOptionPane.showInputDialog("What size board do you want to play on? (Max 30)"));
			if(size<5) size=5;
			if(size>50) size=50;
			new MineSweeper(size);
		}

	}

	public class closeListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}

	}
	
	public void lose(){
		int choice=JOptionPane.showConfirmDialog(null, "GAME OVER, MY MAN. Start a new game?");
		if(choice==0){
			int size;
			dispose();
			size= Integer.parseInt(JOptionPane.showInputDialog("What size board do you want to play on? (Max 30)"));
			if(size<5) size=5;
			if(size>50) size=50;
			new MineSweeper(size);
		}
		if(choice==1){
			dispose();
		}
	}
	
	public void win(int size){
		int count=size*size;
		for(int r=1;r<board.length-1;r++)
			for(int c=1;c<board.length-1;c++){
				if(board[r][c].exposed==true){
					count--;
				}
				if(board[r][c].mine==true){
					count--;
				}
				if(count==0){
					int choice=JOptionPane.showConfirmDialog(null, "You Win! Do you want to play a new game?");
					if(choice==0){
						dispose();
						size= Integer.parseInt(JOptionPane.showInputDialog("What size board do you want to play on?"));
						if(size<5) size=5;
						if(size>50) size=50;
						new MineSweeper(size);
					}
					else{
						dispose();
						return;
					}
				}
			}
	}

	public static void main(String[] args){
		int size;
		size= Integer.parseInt(JOptionPane.showInputDialog("What size board do you want to play on?"));
		if(size<5) size=5;
		if(size>50) size=50;
		new MineSweeper(size);
	}
}
