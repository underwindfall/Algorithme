package leetcode.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/design-snake-game/
public class SnakeGame353 {
    class SnakeGame {
        int width, height, foodIndex;
        Deque<Integer> snake;
        Set<Integer> seen;
        int[][] food;
        int score;

        public SnakeGame(int width, int height, int[][] food) {
            snake = new ArrayDeque<>();
            seen = new HashSet<>();
            this.width = width;
            this.height = height;
            foodIndex = 0;
            score = 0;
            this.food = food;
            snake.addLast(0);
            seen.add(0 * width + 0);
        }

        public int move(String direction) {
            int head = snake.peekLast();
            int row = head / width, col = head % width;
            if (direction.equals("U")) {
                row--;
            } else if (direction.equals("D")) {
                row++;
            } else if (direction.equals("R")) {
                col++;
            } else {
                col--;
            }
            if (row < 0 || row >= height || col < 0 || col >= width) {
                return -1;
            }
            if (foodIndex < food.length && food[foodIndex][0] == row && food[foodIndex][1] == col) {
                snake.addLast(row * width + col);
                seen.add(row * width + col);
                foodIndex++;
                return ++score;
            }

            seen.remove(snake.pollFirst());
            if (seen.contains(row * width + col)) {
                return -1;
            } else {
                seen.add(row * width + col);
                snake.addLast(row * width + col);
                return score;
            }
        }
    }
}
