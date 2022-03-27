package leetcode.algo.greedy;

// https://leetcode-cn.com/problems/broken-calculator/solution/
public class BrokenCalc991 {
    // time O(logTarget)
    // space O(1)
    // 从X->Y 有多种方法 要么 x*2 要么 (x-1)*2 是不确定的
    // 但是从Y->X 是固定的。 如果Y是偶数，那就要/2或者（/2）+1 如果Y是奇数，因为奇数情况下不能/2 所以奇数只能+1或者(+1)/2
    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (target > startValue) {
            ans++;
            if (target % 2 == 0) {
                target /= 2;
            } else {
                target++;
            }
        }
        return ans + startValue - target;
    }

    /**
     * 正向思维
     */
    public int brokenCalcInOrder(int startValue, int target) {
        if (target <= startValue)
            return startValue - target;
        else if (target % 2 == 0)
            return 1 + brokenCalc(startValue, target / 2); // 只有/2这个操作
        else
            return brokenCalc(startValue, (target + 1) / 2) + 1 + 1; // 只有+1 /2 两个操作s
    }
}
