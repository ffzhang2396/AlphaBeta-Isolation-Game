package isolationGame;



/*
 * This heuristic is used for O to use to 
 * try and calculate 
 * # of O moves - # of X moves
 */
public class SimpleEval extends Heuristic {

	@Override
	public double evalFunc(Board board) {
		
		return board.getMoves(false) - board.getMoves(true);
	}

}
