import java.awt.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LifeWindow extends JFrame{
	LifeSquare[][] board;
	JPanel boardPanel = new JPanel();
	JPanel controlPanel = new JPanel();
	JButton move = new JButton("Move");
	JButton clear= new JButton("Clear");
	JButton run = new JButton("Run");
	Timer timer = new Timer(100,new MoveListener());

	public LifeWindow(int boardSize) {
		int r,c;
		board = new LifeSquare[boardSize+2][boardSize+2];
		setTitle("Game of Life");
		setSize(boardSize*14,boardSize*14+30);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		boardPanel.setLayout(new GridLayout(boardSize,boardSize));
		for (r=0; r<board.length; r++)
			for (c=0; c<board.length; c++) {
				board[r][c] = new LifeSquare();
			}
		for (r=1; r<board.length-1; r++)
			for (c=1; c<board.length-1; c++) {
				boardPanel.add(board[r][c]);
			}
		add(boardPanel,BorderLayout.CENTER);
		controlPanel.add(move);
		controlPanel.add(clear);
		controlPanel.add(run);
		clear.addActionListener(new ClearListener());
		run.addActionListener(new RunListener());
		move.addActionListener(new MoveListener());
		add(controlPanel,BorderLayout.SOUTH);
		setVisible(true);
		
	}

	private class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int r,c;
			for (r=0; r<board.length; r++)
				for (c=0; c<board.length; c++) {
					board[r][c].alive = false;
					board[r][c].birth = false;
					board[r][c].death = false;
					board[r][c].setBackground(null);
				}
		}
	}
	private class MoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int r,c, i, j;
			int count;
			for (r=1; r<board.length-1; r++)
				for (c=1; c<board.length-1; c++) {
					count=0;
					for (i=-1; i<=+1; i++)
						for (j=-1; j<=+1; j++)
								if (board[r+i][c+j].alive) count++;
					if (board[r][c].alive) count--;
					if (board[r][c].alive) {
						if (count>=4 || count<2) board[r][c].death=true;
					}
					else
						if (count==3) board[r][c].birth=true;
				}
			for (r=1; r<board.length-1; r++)
				for (c=1; c<board.length-1; c++) {
					if (board[r][c].birth) {
						board[r][c].alive = true;
						board[r][c].birth = false;
						board[r][c].setBackground(Color.RED);
					}
					if (board[r][c].death) {
						board[r][c].alive = false;
						board[r][c].death = false;
						board[r][c].setBackground(null);
					}
				}
		}
	}
	
	private class RunListener implements ActionListener {
		boolean running = false;
		public void actionPerformed(ActionEvent e) {
			if (running) {
				running = false;
				run.setText("Run");
				timer.stop();
			}
			else {
				running = true;
				run.setText("Stop");
				timer.start();
			}
		}
	}
}
