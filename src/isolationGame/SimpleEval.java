package isolationGame;

public class SimpleEval extends Heuristic {

	@Override
	public double evalFunc(Board board) {
		
		return board.getMoves(false) - board.getMoves(true);
	}

}
