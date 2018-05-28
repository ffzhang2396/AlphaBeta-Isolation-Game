package isolationGame;

import java.util.*;

public class Board {

	private char[][] board = new char[9][9];
	private XPos xPlayer = new XPos();
	private OPos oPlayer = new OPos();
	private Board parent;
	private List<Board> children = new ArrayList<Board>();


	public Board() {
		popBoard();
		startingPosition();
		parent = null;
		
	}
	
	/*
	 * True == x move
	 * false == o move
	 */
	public Board(Board board, int x, int y, boolean player) {
		this.board = board.getBoard();
		char row = (char) (x + 64);
		
		if (player) {
			setX(row, y);
		} else {
			setO(row, y);
		}
	}
	
	
	/*
	 * true == X player
	 * false == Y player
	 */
	public List<Board> getChildren(Board board, boolean player) {
		int xPos = (player) ? xPlayer.getRow() : oPlayer.getRow();
		int yPos = (player) ? xPlayer.getCol() : oPlayer.getCol();
		
		//down 
		for (int i = yPos + 1; i < 9; i++) {
			if (isOpen(xPos, i)) {
				children.add(new Board(this, xPos, i, player));
			}
		}
		
		//up
		for (int i = yPos - 1; i > 0; i--) {
			if (isOpen(xPos, i)) {
				children.add(new Board(this, xPos, i, player));
			}
		}
		
		//right
		for (int i = xPos + 1; i < 9; i++) {
			if (isOpen(i, yPos)) {
				children.add(new Board(this, i, yPos, player));
			}
		}
		
		//left
		for (int i = xPos - 1; i > 0; i--) {
			if (isOpen(i, yPos)) {
				children.add(new Board(this, i, yPos, player));
			}
		}
		
		//upLeft
		for (int i = xPos - 1, yPos - 1;)
		
	}
	
	
	/*
	 * checks if there is a valid move for the
	 * player based on true or false.
	 * TRUE == X PLAYER
	 * FALSE == O PLAYER
	 */
	public boolean isXValidMove(char row, int col, boolean player) {
		int rowIndex = (int) (row - 64);
		int xPos = (player) ? xPlayer.getRow() : oPlayer.getRow();
		int yPos = (player) ? xPlayer.getCol() : oPlayer.getCol();
		int dx, dy;
		
		if (xPos == rowIndex) {
			dx = 0;
		} else {
			dx = (rowIndex - xPos) / Math.abs((rowIndex - xPos));
		}
		
		if (yPos == col) {
			dy = 0;
		} else {
			dy = (col - yPos) / Math.abs((col - yPos));
		}
		
		while ((rowIndex != xPos) || (col != yPos)) {
			xPos += dx;
			yPos += dy;
			if (isHole(xPos, yPos)) {
				return false;
			}
		}	
		return true;	
	}
	
	/*
	 * True == X player
	 * False == O player
	 */
	public int getMoves(boolean player) {		
		int moves = 0;
		
		moves += check(1, 1, player);
		moves += check(-1, 1, player);
		moves += check(-1, -1, player);
		moves += check(1, -1, player);
		moves += check(1, 0, player);
		moves += check(-1, 0, player);
		moves += check(0, 1, player);
		moves += check(0, -1, player);
		
		
		return moves;
	}
	
	public char[][] getBoard() {
		return board;
	}

	/*
	 * sets position of hole on map.
	 * Used after a player moves to set previouis position as a hole.
	 */
	public void setHole(int row, int col) {
		board[row][col] = '#';
	}

	/*
	 * Sets position of x player
	 */
	public boolean setX(char row, int col) {
		Character.toUpperCase(row);

		if (board[row - 64][col] == '-') {
			board[row - 64][col] = 'X';
			return true;
		}
		return false;
	}

	/*
	 * sets the position of the O.
	 */
	public boolean setO(char row, int col) {
		Character.toUpperCase(row);

		if (board[row - 64][col] == '-') {
			board[row - 64][col] = 'O';
			return true;
		}
		return false;
	}
	

	/*
	 *Prints out the board
	 */
	public String toString() {
		String printBoard = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				printBoard += board[i][j];
				printBoard += ' ';
			}
			printBoard += '\n';
		}
		return printBoard;
	}
	

	/*
	 * checks if there is a valid move in a certain direction.
	 */
	private int check(int x, int y, boolean player) {
		int xPos = (player) ? xPlayer.getRow() : oPlayer.getRow();
		int yPos = (player) ? xPlayer.getCol() : oPlayer.getCol();
		
		int moves = 0;
		
		while (isOpen((xPos += x),(yPos += y))) {
			moves++;
		}
		
		return moves;	
	}
	
	/*
	 * checks if the spot is open.
	 */
	private boolean isOpen(int x, int y) {
		if ((x < 9 && y < 9) && (x > 0 && y > 0)) {
			if (board[x][y] == '-') {
				return true;
			}
		}		
		return false;
	}
	


	/*
	 * Checks if there is a hole at target
	 * location
	 */
	private boolean isHole(int row, int col) {
		if (board[row][col] == '#') {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Populates the board and sets it to shown
	 * as such in the project guidelines.
	 */
	private void popBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((i == 0) && (j > 0)) {
					board[i][j] = (char) (j + 48);
				} else if ((j == 0) && (i > 0)) {
					board[i][j] = (char) (i + 64);
				} else if ((j != 0) && (i != 0)) {
					board[i][j] = '-';
				}
			}
		}
	}
	
	/*
	 * sets the starting position for X
	 * and O.
	 */
	private void startingPosition() {
		board[1][1] = 'X';
		board[8][8] = 'O';
		
		xPlayer.setRow(1);
		xPlayer.setCol(1);
		
		
		oPlayer.setRow(8);
		oPlayer.setCol(8);	
	}
	
	private static class XPos {
		
		private int row;
		private int col;
		
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
		public void setRow(int row) {
			this.row = row;
		}
		
		public void setCol(int col) {
			this.col = col;
		}
		
		
	}
	
	private static class OPos  {
		
		private int row;
		private int col;
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
		public void setRow(int row) {
			this.row = row;
		}
		
		public void setCol(int col) {
			this.col = col;
		}

	}

}
