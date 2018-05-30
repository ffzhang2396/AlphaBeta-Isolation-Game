package isolationGame;

import java.util.*;

public class Driver {

	public static void main(String args[]) {
		Board board = new Board();
		GameAgent agent = new GameAgent();
		EvalFunc1 eval = new EvalFunc1();
		SimpleEval eval1 = new SimpleEval();

		Pair inf = new Pair(null, 9999, 0);
		Pair nInf = new Pair(null, -9999, 0);
		Pair optimalPair;

		Scanner keyboard = new Scanner(System.in);
		String choice;

		int depth1;
		int depth2;
		
		/*
		 * System.out.println(board);
		 * 
		 * board = agent.alphaBeta(board, nInf, inf, 5, 0, true, true,
		 * true).getBoard();
		 * 
		 * for (int j = 5; j > 1; j--) { board = board.getParent(); }
		 * 
		 * System.out.println(board);
		 */
		
		
		for (int i = 0; i < 50; i++) {
			System.out.println("X's move");
			optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true);
			board = optimalPair.getBoard();
			depth1 = optimalPair.getDepth();
			for (int j = depth1; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
			
			System.out.println("O's move");
			optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false);
			board = optimalPair.getBoard();
			depth2 = optimalPair.getDepth();
			for (int j = depth2; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
		}


/*		for (int i = 0; i < 50; i++) {
			System.out.println("X's move");
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true).getBoard();
			depth1 = board.getDepth();
			for (int j = depth1; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);

			System.out.println("O's move");
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false).getBoard();
			depth2 = board.getDepth();
			for (int j = depth2; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
		}*/

		
/*		for (int i = 0; i < 50; i++) {
			System.out.println("O's move");
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false).getBoard();
			for (int j = 5; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);

			System.out.println("X's move");
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true).getBoard();
			for (int j = 5; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
		}*/

	}
}
