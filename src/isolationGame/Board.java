package isolationGame;

public class Board {

	private char[][] board = new char[9][9];
	private XPos xPlayer = new XPos();
	private OPos oPlayer = new OPos();

	public Board() {
		popBoard();
		startingPosition();
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
			
			System.out.println(dx + " " + dy + "dxdy");
			System.out.println(xPos + " " + yPos);
			if (isHole(xPos, yPos)) {
				return false;
			}
		}	
		return true;	
	}

	public void setHole(int row, int col) {
		board[row][col] = '#';
	}


	

	public boolean setX(char row, int col) {
		Character.toUpperCase(row);

		if (board[row - 64][col] == '-') {
			board[row - 64][col] = 'X';
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
