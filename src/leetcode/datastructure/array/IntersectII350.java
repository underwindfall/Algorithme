package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;



//https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
public class IntersectII350 {
    class DoubleIndex {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            List<Integer> list = new ArrayList<>();
            int left = 0, right = 0;
            while (left < nums1.length && right < nums2.length) {
                if (nums1[left] == nums2[right]) {
                    list.add(nums1[left]);
                    left++;
                    right++;
                } else if (nums1[left] < nums2[right]) {
                    left++;
                } else {
                    right++;
                }
            }
            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
    }

    class Map {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }
            
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();

            for (int num : nums1) {
                int count = map.getOrDefault(num, 0) + 1;
                map.put(num, count);
            }
            int[] intersection = new int[nums1.length];
            int index = 0;
            for (int num : nums2) {
                int count = map.getOrDefault(num, 0);
                if (count > 0) {
                    intersection[index++] = num;
                    count--;
                    if (count > 0) {
                        map.put(num, count);
                    } else {
                        map.remove(num);
                    }
                }
            }
            return Arrays.copyOfRange(intersection, 0, index);
        }
    }
}
