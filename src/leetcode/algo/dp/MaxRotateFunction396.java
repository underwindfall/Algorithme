package leetcode.algo.dp;

// https://leetcode-cn.com/problems/rotate-function/
public class MaxRotateFunction396 {
    /**
 * 把数组逆转跟把乘数逆转是一样的，可以看出有如下规律
 *   4     3     2     6   
 *	 
 *  0*4   1*3   2*2   3*6   F(0)
 *  
 *  3*4   0*3   1*2   2*6   F(1) = F(0) - SUM(data) + N * data[0];
 *  
 *  2*4   3*3   0*2   1*6   F(2) = F(1) - SUM(data) + N * data[1];
 *  
 *  1*4   2*3   3*2   0*6   F(3) = F(2) - SUM(data) + N * data[2];
 *
 */
  //F(k) = F(k - 1) + SUM(data) - N * data[n - k]
  public int maxRotateFunction(int[] nums) {
    long sum = 0, pre = 0, cur = 0, max = 0;
    int N = nums.length;
    
    for(int i = 0; i < N; i++){
        sum += nums[i];
        pre += i * nums[i];
    }
    
    max = pre;
    for(int i = 1; i < N; i++){
        cur = pre - sum + N * (long)nums[i-1];
        max = Math.max(max, cur);
        pre = cur;
    }

    return (int)max;
}
}
