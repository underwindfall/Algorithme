package leetcode.algo.greedy;

// https://leetcode-cn.com/problems/super-washing-machines/
public class SuperWashMachines517 {
    //time O(n)
    //space O(1)
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0;

        for (int i : machines) {
            sum += i;
        }
        int avg = sum / n;
        if (sum % n != 0)
            return -1;

        int res = -1, prefixSum = 0;
        for (int num : machines) {
            num -= avg;
            prefixSum += num;
            res = Math.max(res, Math.max(Math.abs(prefixSum), num));
        }

        return res;
    }
}
