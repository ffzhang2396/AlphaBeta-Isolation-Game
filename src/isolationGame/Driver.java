package isolationGame;

public class Driver {

	public static void main(String args[]) {
		Board board = new Board();
		EvalFunc1 e = new EvalFunc1();
		
		//board.setHole(1, 4);
		
		int test = e.evalFunc(board);
		
		//System.out.println(board.isXValidMove('A', 8, true));
		if (board.isXValidMove('A', 3, true)) {
			board.setX('A', 3);
		}
		
		if (board.isXValidMove('F', 3, true)) {
			board.setX('F', 3);
		}
		
		System.out.println(board.toString());
		
		System.out.println(board.getMoves(true));
		System.out.println(board.getMoves(false));
		
		System.out.println("Evaluation: " + test);
	}
}
