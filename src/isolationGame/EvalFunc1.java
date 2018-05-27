package isolationGame;

public class EvalFunc1 extends Heuristic {

	@Override
	public int evalFunc(Board board) {
	
		int funcVal = board.getMoves(true) - board.getMoves(false);
		
		return funcVal;
	}

}
