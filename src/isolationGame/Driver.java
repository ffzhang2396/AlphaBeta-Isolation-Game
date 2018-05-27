package isolationGame;

public class Driver {

	public static void main(String args[]) {
		Board board = new Board();
		
		board.setHole(1, 4);
		
		System.out.println(board.isXValidMove('A', 8, true));
		
		System.out.println(board.toString());
		
		System.out.println(board.getMoves(true));
		System.out.println(board.getMoves(false));
	}
}
