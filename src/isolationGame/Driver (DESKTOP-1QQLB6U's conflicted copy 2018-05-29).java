package isolationGame;

import java.util.*;

public class Driver {

	public static void main(String args[]) {
		Board board = new Board();
		GameAgent agent = new GameAgent();
		EvalFunc1 eval = new EvalFunc1();
		SimpleEval eval1 = new SimpleEval();

		Pair inf = new Pair(null, 9999);
		Pair nInf = new Pair(null, -9999);

		Scanner keyboard = new Scanner(System.in);
		String choice;

		System.out.println(board);
		System.out.println(eval.evalFunc(board));

		// board.setO('G', 7);

/*		for (int i = 0; i < 50; i++) {
			System.out.println("X's move");
			System.out.println(board);
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true).getBoard();

			for (int j = 5; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println("O's move");
			System.out.println(board);

			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false).getBoard();
			for (int j = 5; j > 1; j--) {
				board = board.getParent();
			}
		}*/

		
		int depth1;
		int depth2;
		for (int i = 0; i < 50; i++) {
			System.out.println("O's move");
			System.out.println(board);
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false).getBoard();
			depth1 = board.getDepth();
			for (int j = depth1; j > 1; j--) {
				board = board.getParent();
			}
			
			System.out.println("X's move");
			System.out.println(board);
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true).getBoard();

			for (int j = 5; j > 1; j--) {
				board = board.getParent();
			}
			
			
		}

		/*
		 * board.setX('G', 7); System.out.println(board);
		 * 
		 * board = agent.alphaBeta(board, nInf, inf, 5, 0, false).getBoard();
		 * System.out.println(board);
		 */
		/*
		 * for (Board state : board.getChildren(false)) {
		 * System.out.println(state); System.out.println(eval.evalFunc(state));
		 * }
		 */

		/*
		 * board.setX('G', 7); System.out.println(board);
		 * 
		 * board = agent.alphaBeta(board, nInf, inf, 3, 0, false).getBoard();
		 * 
		 * for (int i = 3; i > 1; i--) { board = board.getParent(); }
		 * System.out.println(board);
		 * 
		 * 
		 * 
		 * 
		 * board.setX('G', 6); System.out.println(board); board.setParent(null);
		 * board = agent.alphaBeta(board, nInf, inf, 3, 0, false).getBoard();
		 * for (int i = 3; i > 1; i--) { board = board.getParent(); }
		 * System.out.println(board);
		 * 
		 * board.setX('G', 1); System.out.println(board); board.setParent(null);
		 * board = agent.alphaBeta(board, nInf, inf, 3, 0, false).getBoard();
		 * for (int i = 3; i > 1; i--) { board = board.getParent(); }
		 * System.out.println(board);
		 */

		/*
		 * board.setX('G', 7); System.out.println(board);
		 * 
		 * for (Board state :
		 * board.getChildren(false).get(0).getChildren(true).get(1).getChildren(
		 * false)) { System.out.println("=======================");
		 * System.out.println(state); System.out.println(eval.evalFunc(state));
		 * System.out.println(state.getParent()); }
		 * 
		 * 
		 * 
		 * board = agent.alphaBeta(board, nInf, inf, 3, 0, false).getBoard();
		 * 
		 * System.out.println(board); System.out.println(board.getParent());
		 * System.out.println(board.getParent().getParent());
		 * System.out.println(board.getParent().getParent().getParent());
		 */

		/*
		 * while (true) { System.out.print("Enter the command: "); choice =
		 * keyboard.nextLine(); board.setX(choice.charAt(0),
		 * Character.getNumericValue(choice.charAt(1)));
		 * System.out.println("Player's Move:"); System.out.println(board);
		 * board = agent.alphaBeta(board, nInf, inf, 5, 0, false).getBoard(); //
		 * replaced 3 with val
		 * 
		 * for (int i = 5; i > 1; i--) { board = board.getParent(); } //val++;
		 * // test System.out.println("Computer's Move:");
		 * System.out.println(board); System.out.println("depth limit " +
		 * board.getDepth()); }
		 */

	}

}
