package isolationGame;

public class EvalFunc1 extends Heuristic {

	@Override
	public double evalFunc(Board board) {
	
		double funcVal = 1 / eDistance(board);
		
		return funcVal;
	}
	
	
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
