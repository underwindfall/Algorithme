package leetcode.algo.dp.doublesequence.interval;

//https://leetcode-cn.com/problems/remove-boxes/
public class RemoveBox546 {
    /*
* 补充题目：一次可以移除具有相同颜色的连续盒子，是每次只能移除一个滑窗，而不是一次移除同一种颜色所有地方
* 设dp[l][r][k]
  起始下标l(以0开始)，结束下标r，k表示在下标r后面紧接着有k个元素值和boxes[r]相同，的最大积分和
* 比如[l,l+1,···,r-1,r,值同r，值同r，值同r]
  这里有3个元素和boxes[r]相同，即k==3，那么dp[l][r][3]=dp[l][r-1][0]+4*4
  因为有3个和[r]相同，即可以消除4个所以加上4*4
** 得到初始化条件dp[l][r][k]=dp[l][r-1][0]+(k+1)*(k+1)
* 但是有可能在boxes[l]~boxes[r-1]中也存在和boxes[r]相同值的元素，有可能获得更大的积分和
  比如[l,l+1,···,i,···,r-1,r,值同r，值同r，值同r]，假设boxes[i]==boxes[r]
  那么可能先移除boxes[i+1]~boxes[r-1]，这样就能使原来的dp[l][r][3]的k=3变的更大，但是r变得更小，但是积分和更大
  因此就需要在boxes[l]~boxes[r-1]中找到boxes[i]==boxes[r]
** 这样子先移除boxes[i+1]~boxes[r-1]，这一部分的最大积分和是dp[i+1][r-1][0]
  移除之后是[l,l+1,···,i,值同i(原来是r)，值同i(原来是r+1)，值同i(原来是r+2)，值同i(原来是r+3)]
  剩下这部分是dp[l][i][k+1]
** 总和起来就是dp[l][r][k]=max(dp[l][r][k],dp[i+1][r-1][0]+dp[l][i][k+1])
* 最后的答案就是dp[0][boxes.size()-1][0]
*/

    int[][][] dp;

    public int removeBoxes(int[] boxes) {
        int length = boxes.length;
        dp = new int[length][length][length];
        return calculatePoints(boxes, 0, length - 1, 0);
    }
    
    int calculatePoints(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] == 0) {
            dp[l][r][k] = calculatePoints(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, l, i, k + 1) + calculatePoints(boxes, i + 1, r - 1, 0));
                }
            }
        }
        return dp[l][r][k];
    }


    //https://leetcode-cn.com/problems/remove-boxes/solution/shou-hua-tu-jie-dai-ma-can-zhao-liao-guan-fang-ti-/
    class PrefixDp {
        int[][][] dp;
        public int removeBoxes(int[] boxes) {
            int length = boxes.length;
            dp = new int[length][length][length];
            return calculatePoints(boxes, 0, length - 1, 0);
        }

        // 函数定义：移除区间[l,r]和区间前面和boxes[l]相同的k个数，所能产出的最大积分和
        int calculatePoints(int[] boxes, int l, int r, int k) {
            if (l > r) return 0;
            if (dp[l][r][k] != 0) return dp[l][r][k];
            // 找出连续的数字，有k+1个
            while (l < r && boxes[l] == boxes[l + 1]) {
                k++;
                l++;
            }

            // 直接把这段移除，收益(k+1)*(k+1)，加上对剩余部分的递归结果
            int points = (k + 1) * (k + 1) + calculatePoints(boxes, l + 1, r, 0);
            // 移除中间部分子数组，让连续数字遇上和自己相同的数字
            for (int i = l + 1; i <= r; i++) {
                if (boxes[l] == boxes[i]) {
                    points = Math.max(points,
                            calculatePoints(boxes, l + 1, i - 1, 0) + calculatePoints(boxes, i, r, k + 1));
                }
            }
            dp[l][r][k] = points;
            return points;
        }
    }
}
