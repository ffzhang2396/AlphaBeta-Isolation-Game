package isolationGame;

public class AiOP extends Heuristic {

	@Override
	public double evalFunc(Board board) {
		int oppSpaces = occupiedSpaces(board, true); //calculates enemy distance from occupied squares, lower is better
		double distance = eDistance(board); // calculates distance from  computer to player, "chases player" lower is better
		int closeSpaces = occupiedSpaces(board, false); //calculates distance from comp to walls, tries to not trap itself. higher is better.
		
		double resultFunction = closeSpaces - oppSpaces - distance;
		
		System.out.println(closeSpaces);
		System.out.println(oppSpaces);
		System.out.println(distance);
		
		return resultFunction;
	}

	/*
	 * true = O player is computer false = X player is computer 
	 * Used for both calculating the enemy postion as well as computer
	 * position.
	 * 
	 */
	public int occupiedSpaces(Board board, boolean player) {

		int numOfMoves = 0;

		numOfMoves += check(1, 1, player, board);
		numOfMoves += check(-1, 1, player, board);
		numOfMoves += check(-1, -1, player, board);
		numOfMoves += check(1, -1, player, board);
		numOfMoves += check(1, 0, player, board);
		numOfMoves += check(-1, 0, player, board);
		numOfMoves += check(0, 1, player, board);
		numOfMoves += check(0, -1, player, board);

		return numOfMoves;
	}

	private double check(int x, int y, boolean player, Board board) {
		int moves = 0;
		int xPos = (player) ? board.getXRow() : board.getORow();
		int yPos = (player) ? board.getOCol() : board.getOCol();

		while (board.isOpen((xPos += x), (yPos += y)) && (moves < 3)) {
			moves++;
		}
		return moves;
	}

	/*
	 * true == Xplayer is pc false == oPlayer is pc tested OK Higher the number
	 * is worse
	 */
	private double eDistance(Board board) {
		int xxPos = board.getXRow();
		int xyPos = board.getXCol();
		int oxPos = board.getORow();
		int oyPos = board.getOCol();

		int yResult = Math.abs(xyPos - oyPos);
		int xResult = Math.abs(xxPos - oxPos);

		double distance = Math.sqrt((yResult * yResult) + (xResult * xResult));

		return distance;
	}

}
