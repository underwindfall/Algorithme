package leetcode.algo.binarysearch;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
public class FindMinFibonacciNumbers1414 {
    //time O(logk * logC)
    //espace O(n)
    static List<Integer> list = new ArrayList<>();
    static {
        list.add(1);
        int a = 1, b = 1;
        while (b <= (int) 1e9) {
            int c = a + b;
            a = b;
            b = c;
            list.add(c);
        }
    }

    public int findMinFibonacciNumbers(int k) {
        int ans = 0;
        while (k != 0) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid) <= k)
                    l = mid;
                else
                    r = mid - 1;
            }
            k -= list.get(r);
            ans++;
        }
        return ans;
    }
}
