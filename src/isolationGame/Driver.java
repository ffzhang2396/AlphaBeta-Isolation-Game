package isolationGame;

public class Driver {

	public static void main(String args[]) {
		Board board = new Board();
		GameAgent agent = new GameAgent();
		
		Pair inf = new Pair(null, 9999);
		Pair nInf = new Pair(null, -9999);

	
		
		System.out.println(agent.alphaBeta(board, inf, nInf, 2, true));
		
		

		
		
	
	}
	
}
