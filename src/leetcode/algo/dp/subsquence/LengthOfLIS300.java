package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/longest-increasing-subsequence/
public class LengthOfLIS300 {

    // https://leetcode.cn/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
    // time O(n logN)
    // space O(N)
    // bineary serach + greedy
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    // time O(n^2)
    // espace O(N)
    class DP {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxAns = 1;
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
            return maxAns;
        }
    }

    // time O(n^2)
    // space O(N)
    class DFSMEMO {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            if (n <= 1) {
                return n;
            }
            int[] memo = new int[n + 1];
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (memo[i] == 0) {
                    ans = Math.max(ans, dfs(nums, i, memo));
                }

            }
            return ans;
        }

        int dfs(int[] nums, int index, int[] memo) {
            if (memo[index] != 0) {
                return memo[index];
            }
            int ans = 0;
            for (int i = index + 1; i < nums.length; i++) {
                if (nums[i] > nums[index]) {
                    ans = Math.max(ans, dfs(nums, i, memo));
                }
            }
            ans++;
            memo[index] = ans;
            return ans;
        }
    }

    class HelpArray {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            if (n <= 1) {
                return n;
            }
            // 表示arr数组实际的大小
            int size = 1;
            // 一个数组
            int[] arr = new int[n];
            // 初始化第0个元素
            arr[0] = nums[0];

            //  遍历nums中元素
            for (int i = 1; i < n; i++) {
                // 如果比arr最后一个元素大，直接在后面累加
                if (nums[i] > arr[size - 1]) {
                    arr[size++] = nums[i];
                } else {
                    // 否则的话，在arr中寻找它应该在的位置
                    // 即在arr中寻找大于等于它的元素的位置
                    int right = size - 1;
                    while (right >= 0 && arr[right] >= nums[i]) {
                        right--;
                    }
                    // 上面多减了一次，要补回来
                    arr[right + 1] = nums[i];
                }
            }
            return size;
        }
    }

    // 重复计算过多所以不适合
    class Recursive {
        public int lengthOfLIS(int[] nums) {
            if (nums == null) {
                return 0;
            }
            return dfs(-1, 0, nums);
        }

        private int dfs(int pre, int cur, int[] nums) {
            if (cur == nums.length) {
                return 0;
            }
            int a = 0;
            int b = 0;
            // pre小于0是初始状态，继续往后判断
            // if条件满足说明是上升子序列，长度要+1
            if (pre < 0 || nums[pre] < nums[cur]) {
                a = dfs(cur, cur + 1, nums) + 1;
            }
            // 如果不满足可能是不满足上升子序列条件
            // 也可能是 满足条件但主动放弃
            b = dfs(pre, cur + 1, nums);
            return Math.max(a, b);
        }

    }
}
