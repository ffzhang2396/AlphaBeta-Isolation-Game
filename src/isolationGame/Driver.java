package isolationGame;

import java.util.*;

public class Driver {

	public static void main(String args[]) {

		Scanner keyboard = new Scanner(System.in);
		String choice;
		int time;


		System.out.print("Which player is moving first? ('X' = Player, 'O' = Computer): ");
		choice = keyboard.nextLine();
		while (!choice.equals("X") && !choice.equals("O")) {
			System.out.print("Please enter either 'X' or 'O': ");
			choice = keyboard.nextLine();
		}
		System.out.print("Enter amount of time per move in seconds (must be at least 15 seconds): ");
		time = keyboard.nextInt();
		keyboard.nextLine(); // to consume whitespace
		while (time < 15) {
			System.out.print("Plese enter a time of at least 15 seconds: ");
			time = keyboard.nextInt();
			keyboard.nextLine(); // to consume whitespace
		}
		if (choice.equals("X")) {
			matchStart(true, time);
		}
		else {
			matchStart(false, time);
		}
		
		keyboard.close();
	}
	
	public static void matchStart(boolean start, int maxTime) {
		Board board = new Board();
		GameAgent agent = new GameAgent();
		EvalFunc1 eval = new EvalFunc1();
		SimpleEval eval1 = new SimpleEval();
		List<String> playerMoves = new ArrayList<String>();
		List<String> computerMoves = new ArrayList<String>();

		Pair inf = new Pair(null, 9999, 0);
		Pair nInf = new Pair(null, -9999, 0);
		Pair optimalPair;

		Scanner keyboard = new Scanner(System.in);
		String choice;
		boolean valid;
		long startTime = 0;
		long elapsedTime = 0;
		int depth;
	
		System.out.println("Initial board:\n" + board);
		if (start) { // X goes first
			while (true) {
				System.out.println("Enter command in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8)");
				System.out.print("Choice: ");
				System.out.println("");
				startTime = System.currentTimeMillis();
				choice = keyboard.nextLine();
				elapsedTime = System.currentTimeMillis();
				elapsedTime = (elapsedTime - startTime) / 1000;
				valid = board.setX(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
				
				
				while (!valid) { 
					System.out.println("Invalid choice, please try again input in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8))");
					System.out.print("Choice: ");
					System.out.println("");
					startTime = System.currentTimeMillis();
					choice = keyboard.nextLine();
					elapsedTime = System.currentTimeMillis();
					elapsedTime = (elapsedTime - startTime) / 1000;
					valid = board.setX(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
				}
				
				
				System.out.println("Player's time to make a move: " + elapsedTime + " seconds");
				if (elapsedTime > maxTime) {
					System.out.println("Player has taken more than " + maxTime + " seconds to make a move, Computer wins.");
					break;
				}
				
				
				System.out.println("Player's Move: " + board.getMove() + "\n");
				playerMoves.add(board.getMove());
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
				optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, true, true, false);
				elapsedTime = System.currentTimeMillis();
				elapsedTime = (elapsedTime - startTime) / 1000;
				
				
				System.out.println("Computer's time to make a move: " + elapsedTime + " seconds");
				if (elapsedTime > maxTime) {
					System.out.println("Computer has taken more than " + maxTime + " seconds to make a move, Player wins.");
					break;
				}
				else {
					board = optimalPair.getBoard();
					depth = optimalPair.getDepth();
					for (int j = depth; j > 1; j--) {
						board = board.getParent();
					}
					
					System.out.println("Computer's Move: " + board.getMove() + "\n");
					computerMoves.add(board.getMove());
					System.out.println(board);
					
					
					System.out.print("Player moves:\t");
					for (int j = 0; j < playerMoves.size(); j++) {
						System.out.print(playerMoves.get(j) + " ");
					}
					System.out.println("");
					System.out.print("Computer moves:\t");
					for (int i = 0; i < computerMoves.size(); i++) {
						System.out.print(computerMoves.get(i) + " ");
					}
					System.out.println("\n");
					
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
		}
		else {
			while (true) {
				startTime = System.currentTimeMillis();
				optimalPair = agent.alphaBeta(board, nInf, inf, 5, 0, false, true, false);
				elapsedTime = System.currentTimeMillis();
				elapsedTime = (elapsedTime - startTime) / 1000;
				
				
				System.out.println("Computer's time to make a move: " + elapsedTime + " seconds");
				if (elapsedTime > maxTime) {
					System.out.println("Computer has taken more than " + maxTime + " seconds to make a move, Player wins.");
					break;
				}
				else {
					board = optimalPair.getBoard();
					depth = optimalPair.getDepth();
					for (int j = depth; j > 1; j--) {
						board = board.getParent();
					}
					
					System.out.println("Computer's Move: " + board.getMove() + "\n");
					computerMoves.add(board.getMove());
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
				
				System.out.println("Enter command in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8)");
				System.out.print("Choice: ");
				startTime = System.currentTimeMillis();
				choice = keyboard.nextLine();
				elapsedTime = System.currentTimeMillis();
				elapsedTime = (elapsedTime - startTime) / 1000;
				valid = board.setX(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
				

				while (!valid) { 
					System.out.println("Invalid choice, please try again input in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8))");
					System.out.print("Choice: ");
					startTime = System.currentTimeMillis();
					choice = keyboard.nextLine();
					elapsedTime = System.currentTimeMillis();
					elapsedTime = (elapsedTime - startTime) / 1000;
					valid = board.setX(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
				}
				
				
				System.out.println("Player's time to make a move: " + elapsedTime + " seconds");
				if (elapsedTime > maxTime) {
					System.out.println("Player has taken more than " + maxTime + " seconds to make a move, Computer wins.");
					break;
				}

				
				System.out.println("Player's Move: " + board.getMove() + "\n");
				playerMoves.add(board.getMove());
				System.out.println(board);
				
				
				System.out.print("Computer moves:\t");
				for (int i = 0; i < computerMoves.size(); i++) {
					System.out.print(computerMoves.get(i) + " ");
				}
				System.out.println("");
				System.out.print("Player moves:\t");
				for (int j = 0; j < playerMoves.size(); j++) {
					System.out.print(playerMoves.get(j) + " ");
				}
				System.out.println("\n");
				
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
	}
}

