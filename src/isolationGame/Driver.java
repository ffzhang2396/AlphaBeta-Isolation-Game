package isolationGame;

import java.util.*;
public class Driver {

	public static void main(String args[]) throws InterruptedException {
		Board board = new Board();
		GameAgent agent = new GameAgent();
		EvalFunc1 eval = new EvalFunc1();
		SimpleEval eval1 = new SimpleEval();

		Pair inf = new Pair(null, 9999, 0);
		Pair nInf = new Pair(null, -9999, 0);
		Pair optimalPair;

		Scanner keyboard = new Scanner(System.in);
		String choice;
		boolean valid;
		long startTime = 0;
		long elapsedTime = 0;
		int depth;
		
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
		/*int playerWin = 0;
		// THIS IS CURRENT ONE TO USE
		for (int a = 0; a < 20; a++) {
			System.out.println(a);
		for (int i = 0; i < 50; i++) {
			//System.out.println("X's move");
			optimalPair = agent.alphaBeta(board, nInf, inf, 6, 0, true, true, true);
			board = optimalPair.getBoard();
			depth = optimalPair.getDepth();
			for (int j = depth; j > 1; j--) {
				board = board.getParent();
			}
			//System.out.println(board);
			if (board.isWinner(true)) {
				//System.out.println("Player wins.");
				playerWin++;
				break;
			}
			else if (board.isWinner(false)) {
				//System.out.println("Computer wins.");
				break;
			}
			
			//System.out.println("O's move");
			optimalPair = agent.alphaBeta(board, nInf, inf, 6, 0, true, false, false);
			board = optimalPair.getBoard();
			depth = optimalPair.getDepth();
			for (int j = depth; j > 1; j--) {
				board = board.getParent();
			}
			//System.out.println(board);
			if (board.isWinner(true)) {
				//System.out.println("Player wins.");
				playerWin++;
				break;
			}
			else if (board.isWinner(false)) {
				//System.out.println("Computer wins.");
				break;
			}
		}
		board = new Board();
		}
		System.out.println("Player wins out of 20: " + playerWin);*/


		System.out.println("Initial board:\n" + board);
		while (true) {
			System.out.println("Enter command in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8)");
			System.out.print("Choice: ");
			choice = keyboard.nextLine();
			valid = board.setX(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
			while (!valid) { 
				System.out.println("Invalid choice, please try again input in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8))");
				System.out.print("Choice: ");
				choice = keyboard.nextLine();
				valid = board.setX(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
			}
			System.out.println("Player's Move:");
			System.out.println(board);
			if (board.isWinner(true)) {
				System.out.println("Player wins.");
				break;
			}
			else if (board.isWinner(false)) {
				System.out.println("Computer wins.");
				break;
			}
			startTime = System.currentTimeMillis();
			optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, false, true, false);
			elapsedTime = System.currentTimeMillis();
			elapsedTime = (elapsedTime - startTime) / 1000;
			if (elapsedTime > 20) {
				System.out.println("Computer has taken more than 20 seconds to make a move, Player wins.");
				break;
			}
			else {
				board = optimalPair.getBoard();
				depth = optimalPair.getDepth();
				for (int j = depth; j > 1; j--) {
					board = board.getParent();
				}
				System.out.println("Computer's Move:");
				System.out.println(board);
				if (board.isWinner(true)) {
					System.out.println("Player wins.");
					break;
				}
				else if (board.isWinner(false)) {
					System.out.println("Computer wins.");
					break;
				}
			}
		}
		keyboard.close();
		
/*		for (int i = 0; i < 50; i++) {
			System.out.println("X's move");
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true).getBoard();
			depth = board.getDepth();
			for (int j = depth; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);

			System.out.println("O's move");
			board = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false).getBoard();
			depth = board.getDepth();
			for (int j = depth; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
		}*/

		
		/*for (int i = 0; i < 64; i++) {
			System.out.println("O's move");
			optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, true, false, false);
			board = optimalPair.getBoard();
			depth = optimalPair.getDepth();
			for (int j = depth; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
			if (board.isWinner(false)) {
				System.out.println("O wins.");
				break;
			}
			else if (board.isWinner(true)) {
				System.out.println("X wins.");
				break;
			}

			System.out.println("X's move");
			optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, true);
			board = optimalPair.getBoard();
			depth = optimalPair.getDepth();
			for (int j = depth; j > 1; j--) {
				board = board.getParent();
			}
			System.out.println(board);
			if (board.isWinner(false)) {
				System.out.println("O wins.");
				break;
			}
			else if (board.isWinner(true)) {
				System.out.println("X wins.");
				break;
			}
		}*/

	}
}
