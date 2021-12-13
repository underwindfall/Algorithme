package leetcode.datastructure.stack.monotoinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
// time O(k * (m + n +k ^2))
// space O(k)
public class MaxNumber321 {
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] max = new int[k];
        Arrays.fill(max, -1);
        for (int i = Math.max(0, k - len2); i <= Math.min(k, len1); i++) { // /需要讨论k<len1 &&k<len2 、 len1<k<len2
                                                                           // 、len1<k&&len2<k 三种情况
            int[] pick1 = pickKmaxNumber(nums1, i);
            int[] pick2 = pickKmaxNumber(nums2, k - i);
            int[] cur = merge(pick1, pick2);

            if (compare(max, 0, cur, 0)) {
                max = max;
            } else {
                max = cur;
            }
        }
        return max;
    }

    public static int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int cur = 0, p1 = 0, p2 = 0;
        while (cur < nums1.length + nums2.length) {
            if (compare(nums1, p1, nums2, p2)) { // 不能只比较当前值，如果当前值相等还需要比较后续哪个大
                res[cur++] = nums1[p1++];
            } else {
                res[cur++] = nums2[p2++];
            }
        }
        return res;
    }

    public static boolean compare(int[] nums1, int p1, int[] nums2, int p2) {
        if (p2 >= nums2.length)
            return true;
        if (p1 >= nums1.length)
            return false;
        if (nums1[p1] > nums2[p2])
            return true;
        if (nums1[p1] < nums2[p2])
            return false;
        return compare(nums1, p1 + 1, nums2, p2 + 1);
    }

    public static int[] pickKmaxNumber(int[] num, int k) {
        List<Integer> s = new ArrayList<Integer>();
        int len = num.length;
        // 移除 len - k 个元素
        int n = len - k;
        for (int i = 0; i < len; i++) {
            while (!s.isEmpty() && num[i] > s.get(s.size() - 1) && n > 0) {
                s.remove(s.size() - 1);
                n--;
            }
            s.add(num[i]);
        }
        while (n > 0) {
            s.remove(s.size() - 1);
            n--;
        }
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = s.get(i);
        }
        return arr;
    }
}
