package isolationGame;

public class Driver {

	public static void main(String args[]) {
		Board board = new Board();
		GameAgent agent = new GameAgent();
		
		Pair inf = new Pair(null, 9999);
		Pair nInf = new Pair(null, -9999);
		
		board.setX('C', 4);
		System.out.println(board);
		board = agent.alphaBeta(board, inf, nInf, 3,0, false).getBoard();
		System.out.println("depth limit " + board.getDepth());
		System.out.println("=============================================");
		
		
		board.setX('C', 8);
		System.out.println(board);
		board = agent.alphaBeta(board, inf, nInf, 3,0, false).getBoard();
		System.out.println("depth limit " + board.getDepth());
		System.out.println("=============================================");
		
		board.setX('G', 4);
		System.out.println(board);
		board = agent.alphaBeta(board, inf, nInf, 3, 0, false).getBoard();
		System.out.println("depth limit " + board.getDepth());

		

		
	
		
/*		for (Board state : board.getChildren(board, true)) {
			System.out.println(state.getDepth());
			System.out.println(state);
		}*/
		
		
		
		

		
		
	
	}
	
}
