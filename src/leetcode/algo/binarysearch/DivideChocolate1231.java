package leetcode.algo.binarysearch;

//https://leetcode-cn.com/problems/divide-chocolate/
class DivideChocolate1231 {
    //time O(n Logn)
    //space O(1s)
    public int maximizeSweetness(int[] sweetness, int k) {
        int sum = 0;
        for (int i : sweetness) {
            sum += i;
        }

        if (k == 0) {
            return sum;
        }

        int left = 0, right = sum / (k + 1);

        int res = 0;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (canSplit(sweetness, k, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    boolean canSplit(int[] sweetness, int k, int avg) {
        int sum = 0, count = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
            if (sum >= avg) {
                sum = 0;
                count++;
            } else {
                continue;
            }
        }
        return count >= k + 1;
    }
}