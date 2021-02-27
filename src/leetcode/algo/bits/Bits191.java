package leetcode.algo.bits;

// https://leetcode-cn.com/problems/number-of-1-bits/
public class Bits191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;

        for (count = 0; n != 0; count++) {
            n &= n - 1;
        }

        return count;
    }
}
