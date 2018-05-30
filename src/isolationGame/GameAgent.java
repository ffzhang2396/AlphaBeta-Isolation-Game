package isolationGame;

import java.util.*;

public class GameAgent {

	AiOP eval = new AiOP();
	EvalFunc1 eval1 = new EvalFunc1();
	SimpleEval eval2 = new SimpleEval();
	private double inf = 9999;
	private double nInf = -9999;
	private long startTime = 0;
	private long elapsedTime = 0;

	/*
	 * Alpha beta pruning with pairs to hold both board state as well as its
	 * evaluation score.
	 */
	public Pair alphaBeta(Board start, Pair alpha, Pair beta, int depthLimit, int depth, boolean player, boolean func, boolean Xplayer) {

		if (depth == depthLimit) {
			if (func) {
				return new Pair(start, eval2.evalFunc(start), depth);
			} else {
				return new Pair(start, eval1.evalFunc(start), depth);
			}
		}

		List<Board> children = start.getChildren(Xplayer);
		if (children.isEmpty()) {
			if (func) {
				return new Pair(start, eval2.evalFunc(start), depth);
			} else {
				return new Pair(start, eval1.evalFunc(start), depth);
			}
		}

		if (player) {
			Pair bestValue = new Pair(null, nInf, depth);
			for (int i = 0; i < children.size(); i++) {
				Pair value = alphaBeta(children.get(i), alpha, beta, depthLimit, depth + 1, !player, func, !Xplayer);
				if (value.getScore() > bestValue.getScore()) {
					bestValue = value;
				} else if (value.getScore() == bestValue.getScore()) {
					if (Math.random() > 0.5) {
						bestValue = value;
					}
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
			Pair bestValue = new Pair(null, inf, depth);
			for (int i = 0; i < children.size(); i++) {
				Pair value = alphaBeta(children.get(i), alpha, beta, depthLimit, depth + 1, !player, func, !Xplayer);
				if (value.getScore() < bestValue.getScore()) {
					bestValue = value;
				} else if (value.getScore() == bestValue.getScore()) {
					if (Math.random() > 0.5) {
						bestValue = value;
					}
				}

				if (beta.getScore() > bestValue.getScore()) {
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
