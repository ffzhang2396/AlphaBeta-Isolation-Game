package isolationGame;

public class AiOP extends Heuristic {

	@Override
	public double evalFunc(Board board) {		
		
		return calcEval(board);
	}
	
	
	private double calcEval(Board board) {
		int oppSpaces = occupiedSpaces(board, true); //enemy distance from walls, lower is better.
		int compSpaces = occupiedSpaces(board, false); //comp distance from walls. higher is better.
		double distance = eDistance(board); //distance fromula. lower is better.
		
		double oppSpacesWeight = (1 - (oppSpaces/24)) * 25;
		double compSpacesWeight = (compSpaces / 24) * 25;
		double distanceWeight = (1 - (distance / 9.899494936611665)) * 50;
		
		double totalWeightedEval = oppSpacesWeight + compSpacesWeight + distanceWeight;
		
		return totalWeightedEval;
	}

	/*
	 * true = O player is computer false = X player is computer 
	 * Used for both calculating the enemy postion as well as computer
	 * position.
	 * 
	 */
	private int occupiedSpaces(Board board, boolean player) {

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
		int yPos = (player) ? board.getXCol() : board.getOCol();

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
