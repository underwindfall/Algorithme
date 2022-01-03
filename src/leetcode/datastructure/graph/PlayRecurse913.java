package leetcode.datastructure.graph;

import java.util.HashMap;
import java.util.Map;


// https://leetcode-cn.com/problems/cat-and-mouse/
public class PlayRecurse913 {
    Map<String, Integer> memo = new HashMap<>();
    int WIN = 1, LOSE = 2, DRAW = 0;

    public int catMouseGame(int[][] graph) {
        memo.clear();
        int mousePosition = 1, catPosition = 2, hole = 0, totalMoves = 0;
        return dfs(graph, mousePosition, catPosition, hole, totalMoves);
    }

    int dfs(int[][] graph, int mousePosition, int catPosition, int hole, int totalMoves) {
        if (totalMoves > 2 * graph.length) {
            // all nodes are visited, now if cat or mouse visit a node it would mean that
            // they are repeating again and again to WIN.
            return DRAW;
        }

        if (mousePosition == catPosition) {
            return LOSE;
        }

        if (mousePosition == hole) {
            return WIN;
        }

        String suitation = mousePosition + " " + catPosition + " " + totalMoves;

        if (memo.containsKey(suitation)) {
            return memo.get(suitation);
        }

        if (totalMoves % 2 == 0) { // mouse turn
            // check if we can draw the game, better than lose
            boolean canDraw = false;
            for (int adj : graph[mousePosition]) {
                int playResult = dfs(graph, adj, catPosition, hole, totalMoves + 1);
                if (playResult == WIN) {// mouse wins
                    memo.put(suitation, WIN);
                    return WIN;
                }
                if (playResult == DRAW) {
                    canDraw = true;
                }
            }
            if (canDraw) {
                memo.put(suitation, DRAW);
                return DRAW;
            } else {
                memo.put(suitation, LOSE);
                return LOSE; // cat wins
            }
        } else {
            // cat turn
            boolean canDraw = false;
            for (int adj : graph[catPosition]) {
                if (adj == hole) { // cat cannot travel to hole
                    continue;
                }

                int playResult = dfs(graph, mousePosition, adj, hole, totalMoves + 1);
                if (playResult == LOSE) {
                    memo.put(suitation, LOSE); // cat wins
                }
                if (playResult == DRAW) {
                    canDraw = true;
                }
            }
            if (canDraw) {
                memo.put(suitation, DRAW);
                return DRAW;
            } else {
                memo.put(suitation, WIN); // mouse WINS
                return WIN;
            }
        }
    }
}
