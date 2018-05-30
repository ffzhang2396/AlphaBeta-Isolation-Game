package isolationGame;

public class Pair {
	
	private Board board;
	private double score;
	private int depth;
	
	public Pair (Board board, double score, int depth) {
		this.board = board;
		this.score = score;
		this.depth = depth;
	}
	
	
	public int getDepth() {
		return depth;
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
