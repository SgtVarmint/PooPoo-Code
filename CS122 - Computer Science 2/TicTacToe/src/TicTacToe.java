import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToe extends JFrame{
	JButton[][] board = new JButton[3][3];
	char player = 'X';
	JButton square = new JButton("Squirt");
	JButton newGame = new JButton("New Game");
	JTextField wins = new JTextField(4);
	JTextField status = new JTextField(10);
	JPanel controlpanel = new JPanel();
	JPanel boardPanel = new JPanel();
	
	public TicTacToe()
	{
		setTitle("Tic Tac Toe");
		setSize(400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		boardPanel.setLayout(new GridLayout(3,3));
		for(int r = 0; r<3; r++)
		{
			for(int c = 0; c<3; c++)
			{
				board[r][c] = new JButton("");
				board[r][c].addActionListener(new SquareListener(r,c));
				boardPanel.add(board[r][c]);
			}
			
		}
		add(boardPanel,BorderLayout.CENTER);
		newGame.addActionListener(new NewGameListener());
		controlpanel.add(newGame);
		controlpanel.add(wins);
		controlpanel.add(status);
		//this.add(panel);//don't need "this."
		add(controlpanel,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	class SquareListener implements ActionListener{
		int row,col;//global vars
		boolean taken=false;
		
		public SquareListener(int r, int c){//constructor
			row=r;
			col=c;
		}
		
		public void actionPerformed(ActionEvent e) {
			//System.out.println(player + " get's this square.");
			if(taken)return;
			board[row][col].setText("" + player);
			taken=true;
			if(player == 'X')
				player = 'O';
			else
				player = 'X';
			
		}
		
	}
	
	public static void main(String[] args) {
		new TicTacToe();
	}

}
