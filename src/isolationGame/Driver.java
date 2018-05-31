package isolationGame;

import java.util.*;

public class Driver {

	private static Scanner keyboard = new Scanner(System.in);
	public static void main(String args[]) {

		String choice;
		int gameChoice;
		int time;
		
		do {
			System.out.println("Choose a game mode:\n(1) Player vs Computer\n(2) Player vs Player\n(3) Exit program");
			System.out.print("Choice: ");
			gameChoice = keyboard.nextInt();
			while (gameChoice > 3 || gameChoice < 1) {
				System.out.print("Please enter a valid choice: ");
				gameChoice = keyboard.nextInt();
			}
			if (gameChoice == 3) {
				System.out.println("Thank you for running this program!");
			}
			else {
				System.out.print("Enter amount of time per move in seconds (must be at least 15 seconds): ");
				time = keyboard.nextInt();
				keyboard.nextLine(); // to consume whitespace
				while (time < 15) {
					System.out.print("Plese enter a time of at least 15 seconds: ");
					time = keyboard.nextInt();
					keyboard.nextLine(); // to consume whitespace
				}
		
				// Player vs Computer
				if (gameChoice == 1) {
					System.out.print("Which player is moving first? ('X' = Player, 'O' = Computer): ");
					choice = keyboard.nextLine();
					while (!choice.equals("X") && !choice.equals("O")) {
						System.out.print("Please enter either 'X' or 'O': ");
						choice = keyboard.nextLine();
					}
		
					if (choice.equals("X")) {
						matchStart(true, time);
					}
					else {
						matchStart(false, time);
					}
				}
				else {
					versusMode(time);
				}
			}
		} while (gameChoice != 3);
		
		keyboard.close();
	}
	
	public static void versusMode(int maxTime) { // in progress
		Board board = new Board();
		List<String> playerMoves = new ArrayList<String>();
		List<String> player2Moves = new ArrayList<String>();
		
		String choice;
		boolean valid;
		long startTime = 0;
		long elapsedTime = 0;
		
		System.out.println("Player1 = X\nPlayer2 = O");
		System.out.println("Initial board:\n" + board);
		while (true) {
			// Player 1
			System.out.println("Player1, enter command in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8)");
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
			
			
			System.out.println("Player1's time to make a move: " + elapsedTime + " seconds");
			if (elapsedTime > maxTime) {
				System.out.println("Player1 has taken more than " + maxTime + " seconds to make a move, Computer wins.");
				break;
			}
			

			System.out.println("Player1's Move: " + board.getMove() + "\n");
			playerMoves.add(board.getMove());
			System.out.println(board);
			if (board.isWinner(true)) {
				System.out.println("Player1 wins.");
				break;
			}
			else if (board.isWinner(false)) {
				System.out.println("Player2 wins.");
				break;
			}
			
			// Player 2
			System.out.println("Player2, enter command in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8)");
			System.out.print("Choice: ");
			startTime = System.currentTimeMillis();
			choice = keyboard.nextLine();
			elapsedTime = System.currentTimeMillis();
			elapsedTime = (elapsedTime - startTime) / 1000;
			valid = board.setOVersus(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
			
			
			while (!valid) { 
				System.out.println("Invalid choice, please try again input in form 'a2' or 'A2' given choices (a,b,c,d,e,f,g,h)(1,2,3,4,5,6,7,8))");
				System.out.print("Choice: ");
				startTime = System.currentTimeMillis();
				choice = keyboard.nextLine();
				elapsedTime = System.currentTimeMillis();
				elapsedTime = (elapsedTime - startTime) / 1000;
				valid = board.setOVersus(choice.charAt(0), Character.getNumericValue(choice.charAt(1)));
			}
			
			
			System.out.println("Player2's time to make a move: " + elapsedTime + " seconds");
			if (elapsedTime > maxTime) {
				System.out.println("Player2 has taken more than " + maxTime + " seconds to make a move, Computer wins.");
				break;
			}
			
			
			System.out.println("Player2's Move: " + board.getMove() + "\n");
			player2Moves.add(board.getMove());
			System.out.println(board);
			if (board.isWinner(true)) {
				System.out.println("Player1 wins.\n");
				break;
			}
			else if (board.isWinner(false)) {
				System.out.println("Player2 wins.\n");
				break;
			}	
				
				System.out.print("Player1 moves:\t");
				for (int j = 0; j < playerMoves.size(); j++) {
					System.out.print(playerMoves.get(j) + " ");
				}
				System.out.println("");
				System.out.print("Player2 moves:\t");
				for (int i = 0; i < player2Moves.size(); i++) {
					System.out.print(player2Moves.get(i) + " ");
				}
				System.out.println("\n");
				
				if (board.isWinner(true)) {
					System.out.println("Player1 wins.\n");
					break;
				}
				else if (board.isWinner(false)) {
					System.out.println("Player2 wins.\n");
					break;
				}
			}
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
					//System.out.println("");
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
					System.out.println("Player wins.\n");
					break;
				}
				else if (board.isWinner(false)) {
					System.out.println("Computer wins.\n");
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
						System.out.println("Player wins.\n");
						break;
					}
					else if (board.isWinner(false)) {
						System.out.println("Computer wins.\n");
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
						System.out.println("Player wins.\n");
						break;
					}
					else if (board.isWinner(false)) {
						System.out.println("Computer wins.\n");
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
					System.out.println("Player wins.\n");
					break;
				}
				else if (board.isWinner(false)) {
					System.out.println("Computer wins.\n");
					break;
				}
				
			}
		}
	}
}

