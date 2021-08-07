package leetcode.datastructure.array;

//https://leetcode-cn.com/problems/circular-array-loop/
public class CircularArrayLoop457 {

    // 注意判断同方向 + 非自身k=1的ring
    // time O(n^2)
    // espace O(1)
    class FastSlow {
        int n;
        int[] nums;

        public boolean circularArrayLoop(int[] nums) {
            this.n = nums.length;
            this.nums = nums;
            // 按照题意，起点seq[0]可能不从下标0开始
            for (int i = 0; i < n; i++) {
                int slow = i, fast = next(i);
                // 同方向
                while (nums[fast] * nums[slow] > 0 && nums[next(fast)] * nums[slow] > 0) {
                    if (fast == slow) {
                        if (slow == next(slow))
                            break; // 存在长度k为1的同向环
                        else
                            return true;
                    }
                    // 指针移动
                    fast = next(next(fast));
                    slow = next(slow);
                }
            }
            return false;
        }

        int next(int i) {
            return ((i + nums[i]) % n + n) % n;
        }
    }

    // time O(n^2)
    // espace O(1)
    class Simulation {

        int n;
        int[] nums;

        public boolean circularArrayLoop(int[] _nums) {
            nums = _nums;
            n = nums.length;
            for (int i = 0; i < n; i++) {
                if (check(i))
                    return true;
            }
            return false;
        }

        boolean check(int start) {
            int cur = start;
            boolean flag = nums[start] > 0;
            int k = 1;
            while (true) {
                if (k > n)
                    return false;
                int next = ((cur + nums[cur]) % n + n) % n;
                if (flag && nums[next] < 0)
                    return false;
                if (!flag && nums[next] > 0)
                    return false;
                if (next == start)
                    return k > 1;
                cur = next;
                k++;
            }
        }
    }
}
