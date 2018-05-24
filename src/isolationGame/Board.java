package isolationGame;

public class Board {

	private char[][] board = new char[9][9];
	private XPos xPlayer;
	private OPos oPlayer;

	public Board() {
		popBoard();
		startingPosition();
	}
	

	

	public boolean setX(char row, int col) {
		Character.toUpperCase(row);

		if (board[row - 64][col] == '-') {
			board[row - 64][col] = 'O';
			return true;
		}
		return false;
	}

	public boolean setO(char row, int col) {
		Character.toUpperCase(row);

		if (board[row - 64][col] == '-') {
			board[row - 64][col] = 'O';
			return true;
		}
		return false;
	}

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
		
		public void setRow(int row) {
			this.row = row;
		}
		
		public void setCol(int col) {
			this.col = col;
		}

	}

}
