package leetcode.datastructure.stack;

import java.util.Stack;

// https://leetcode-cn.com/problems/asteroid-collision/
public class AsteroidCollision735 {
    // time O(n)
    // space O(n)
    /**
     *  入栈
     *  1. 栈是空的
     *  2. 栈顶元素是负数（下一个为负数一起向左， 下一个为正数则分向两边）
     *  3. 当前元素为正数（栈顶为正一起向右， 栈顶为负分向两边）
     * 
     * 
     * ====== 碰撞
     * 栈顶元素 > abs（当前元素） 当前元素被撞毁
     * 栈顶元素 = abs(当前元素） 栈顶弹出和当前元素抵消
     * 栈顶元素 < abs(当前元素）栈顶弹出 + 并与新栈顶进行判断
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        while (p < asteroids.length) {
            if (stack.empty() || stack.peek() < 0 || asteroids[p] > 0) {
                stack.push(asteroids[p]);
            }
            else if (stack.peek() <= -asteroids[p]) {
                //栈顶的恒星爆炸
                if (stack.pop() < -asteroids[p]) {
                    continue;
                }
            }
            p++;
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i>=0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
