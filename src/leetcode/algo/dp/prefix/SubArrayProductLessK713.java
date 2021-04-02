package leetcode.algo.dp.prefix;

// https://leetcode-cn.com/problems/subarray-product-less-than-k/
public class SubArrayProductLessK713 {
    // 其实是滑动窗口
    /**
     * 1.使用变量times存储个数 
     * 
     * 2.我们让右指针不断前进，当窗口乘积小于k时，就让times+=窗口连续子数组个数
     * 3.当窗口乘积大于等于k时，我们就让乘积去除左指针对应的值，然后左指针右移，直至乘积小于k。
     *  那么窗口中连续子数组的个数是多少呢？ times+=right-left+1（比如例子中[10, 5, 2, 6]，
     * 初始情况窗口中只有10，所以times+1，之后窗口中加上了5，变成[10,5],其中连续子数组有：{{10},{5},{10,5}}，
     * 之前的10已经加过了，因此每次加进去的连续子数组是以当前right对应的数为首的连续子数组，
     * 再以[10,5,2]，以2为首就是{2,25,2510},对应为right-left+1。
     * 
     */
    class DoubleIndex {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k <= 1)
                return 0;

            // right 动 left 不同
            int prod = 1, ans = 0, left = 0;
            for (int right = 0; right < nums.length; right++) {
                prod *= nums[right];
                while (prod >= k)
                    prod /= nums[left++];
                ans += right - left + 1;
            }
            return ans;
        }
    }

    class BinarySearch {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            // math 知识 log(a*b) = log(a) + log(b)
            // 这样做的原因是int[] nums 可能是一个比较大的product乘积 所以在这一点上要注意
            // 同时通过log -> 可以转换成 prefixSum[i:j] <= log(k)
            if (k == 0)
                return 0;
            double logK = Math.log(k);
            double[] prefixSum = new double[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + Math.log(nums[i]);
            }
            // 二分发 > 因为数组具有递增的性质
            int ans = 0;
            for (int i = 0; i < prefixSum.length; i++) {
                int left = i + 1, right = prefixSum.length;

                while (left < right) {
                    int mid = (right - left) / 2 + left;
                    // 精髓
                    if (prefixSum[mid] - prefixSum[i] < logK - 1e-9) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 这里面因为i固定的，动的是left的位置 通过判断 prefixSum[mid] - prefixSum[i] < logK 的情况获得
                // 子数组因为是连续的 所以 是数组的长度 - 1
                ans += left - i - 1;
            }

            return ans;
        }
    }
}
