package isolationGame;

public class Pair {
	
	private Board board;
	private double score;
	
	public Pair (Board board, double score) {
		this.board = board;
		this.score = score;
	}
	
	
	public Board getBoard() {
		return board;
	}

	public double getScore() {
		return score;
	}
	
	
	public String toString() {
		String boardScore = "";
		
		
		boardScore += board;
		boardScore += '\n';
		boardScore += score;
		
		
		return boardScore;
	}
}
