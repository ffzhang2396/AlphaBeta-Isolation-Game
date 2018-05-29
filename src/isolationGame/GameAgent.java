package isolationGame;

import java.util.*;

public class GameAgent {

	private Board board = new Board();
	AiOP eval = new AiOP();
	private double inf = 9999;
	private double nInf = -9999;
	private long startTime = 0;
	private long elapsedTime = 0;

	
	
	

	/*
	 * Alpha beta pruning with pairs to hold both board state as well as its evaluation score.
	 */
	public Pair alphaBeta(Board start, Pair alpha, Pair beta, int depthLimit, boolean player) {
		elapsedTime = (System.nanoTime() - startTime) / 1000000000;
		
		if (start.getDepth() > depthLimit) {
			return new Pair(start, eval.evalFunc(start));
		}		
		
		
		List<Board> children = board.getChildren(start, player);
		
		if (player) {
			Pair bestValue = new Pair(null, nInf);
			for (int i = 0; i < children.size(); i++) {
				Pair value = alphaBeta(children.get(i), alpha, beta, depthLimit, !player);
				
				if (value.getScore() > bestValue.getScore()) {
					bestValue = value;
				}
				
				if (alpha.getScore() < bestValue.getScore()) {
					alpha = bestValue;
				}
				
				if (alpha.getScore() >= beta.getScore()) {
					break;
				}
			}
			return bestValue;
		} else {
			Pair bestValue = new Pair(null, inf);
			for (int i = 0; i < children.size(); i++) {
				Pair value = alphaBeta(children.get(i), alpha, beta, depthLimit, !player);
				
				if (value.getScore() < bestValue.getScore()) {
					bestValue = value;
				}
				
				if (beta.getScore() < bestValue.getScore()) {
					beta = bestValue;
				}
				
				if (alpha.getScore() >= beta.getScore()) {
					break;
				}
			}
			return bestValue;
		}
	}	
}
