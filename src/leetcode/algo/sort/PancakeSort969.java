package leetcode.algo.sort;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/pancake-sorting/
public class PancakeSort969 {
    /**
     * 观察提示中的数据范围，数组元素是从 1 到 n 的，且互不相同，所以，我们可以先找到最大的数，即数值为 n 的数，把它先换到 0
     * 号位置，再把它换到最后一位，这样最大的数就已经排好了。然后，我们再找到次大的数，即数值为 n-1 的数，把它先换到 0
     * 号位置，再把它换到次大位置，这样次大的数也就排好了。依次进行，直到只剩下一个数为止。
     * 
     * 通过这种方式，每个数最多进行两次翻转就可以排好它的位置，所以，总共不超过 2 * n 次的交换，是满足题目条件 10 * n 次的。
     * 
     * 
     */
    // time O(n^2)
    // space O(1)
    public List<Integer> pancakeSort(int[] arr) {
        // 类似于冒泡，先把最大的换到最后面，再把次大的换到次后面，依次进行
        int n = arr.length ;
        // last 存储“最后一个位置”（非排好序的最后一位）
        int last = n - 1;
        // x存储当前轮“最大的数”（非排好序的数中最大的）
        int x = n;
        List<Integer> ans = new ArrayList<>();
        while (x != 1) {
            // 先找到x的位置
            int k = 0;
            while (true) {
                if (arr[k] == x) {
                    break;
                }
                k++;
            }
            // k==last说明x已经在“最后一位”了，进入下一轮
            if (k != last) {
                // 把最大的数换到了0号位置了，k==0时不予要交换
                if (k != 0) {
                    reverse(arr, 0, k);
                    ans.add(k + 1);
                }
                // 最大的数换到“最后一位”
                reverse(arr, 0, last);
                ans.add(last + 1);
            }
            last--;
            x--;
        }
        return ans;
    }

    void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
}
