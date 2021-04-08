package leetcode.datastructure.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/find-the-duplicate-number
// espace for algo + 双指针解法
public class FindDuplicateNumber287 {
    // https://leetcode-cn.com/problems/find-the-duplicate-number/solution/kuai-man-zhi-zhen-de-jie-shi-cong-damien_undoxie-d/
    // time O(N)
    // espace O(1)
    // 精髓在于把数组当成了转换成linkedList 这里很巧妙
    class OfficalFastSlowIndex {
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            while (true) {
                slow = nums[slow];
                fast = nums[nums[fast]];
                if (fast == slow)
                    break;
            }
            int finder = 0;
            while (true) {
                finder = nums[finder];
                slow = nums[slow];
                if (slow == finder)
                    break;
            }
            return slow;
        }
    }

    // time O(N)
    // espace O(N)
    class MapSolution {
        public int findDuplicate(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    return num;
                }
                map.put(num, num);
            }
            return -1;
        }
    }

    // 取出区间的mid
    // 遍历nums 和 mid 进行比较
    // 得到遍历中cnt 小于mid 的总数量值
    // cnt <= mid 意味着左边部分正常不存在重复
    // time O(N * logN)
    // espace O(1)
    class BinarySearch {
        public int findDuplicate(int[] nums) {
            int n = nums.length;
            int l = 1, r = n - 1, ans = -1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                int cnt = 0;
                for (int i = 0; i < n; ++i) {
                    if (nums[i] <= mid) {
                        cnt++;
                    }
                }
                if (cnt <= mid) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                    ans = mid;
                }
            }
            return ans;
        }
    }

    // time O(N)
    // espace O(N)
    class TransformArrayToCount {
        public int findDuplicate(int[] nums) {
            int[] arr = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[nums[i]] += 1;
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 1) {
                    return arr[i];
                }
            }
            return -1;
        }
    }

    // time O(N*lgN)
    // espace O(1)
    class FastSlowIndex {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            int slow = 0;
            for (int fast = 1; fast < nums.length; fast++) {
                if (nums[slow] == nums[fast]) {
                    return nums[fast];
                } else {
                    slow++;
                }
            }
            return -1;
        }
    }

}
