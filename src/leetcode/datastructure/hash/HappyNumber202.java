package leetcode.datastructure.hash;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/happy-number/
public class HappyNumber202 {
    class Hash {
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            while (n != 1 && !set.contains(n)) {
                set.add(n);
                n = getNextNum(n);
            }
            return n == 1;
        }

        int getNextNum(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }
    }

    class FastSlowIndex {
        public boolean isHappy(int n) {
            int slow = n;
            int fast = getNextNum(n);
            while (fast != 1 && slow != fast) {
                slow = getNextNum(slow);
                fast = getNextNum(getNextNum(fast));
            }
            return fast == 1;
        }

        int getNextNum(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }
    }
}
