package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/kth-largest-element-in-an-array
public class FindKthLargest215 {
    // time O(N * logN)
    // espace O(logN)
    // 这个思路主要是推排序的 根本远离
    class HeapSort {
        public int findKthLargest(int[] nums, int k) {
            /*
             * 第一步：将数组堆化 beginIndex = 第一个非叶子节点。 从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
             * 叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
             */
            int len = nums.length - 1;
            int beginIndex = (nums.length) / 2 - 1;
            for (int i = beginIndex; i >= 0; i--) {
                maxHeapify(nums, i, len);
            }
            /*
             *
             * 第二步：对堆化数据排序 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
             * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。 直至未排序的堆长度为 0。
             */
            for (int i = len; i > len - k + 1; i--) {
                swap(nums, 0, i);
                maxHeapify(nums, 0, i - 1);
            }

            return nums[0];
        }

        void maxHeapify(int[] arr, int index, int len) {
            int left = index * 2 + 1;
            int right = left + 1;
            int cMax = left;
            if (left > len) {
                return;
            }
            if (right <= len && arr[right] > arr[left]) {
                cMax = right;
            }
            if (arr[cMax] > arr[index]) {
                swap(arr, cMax, index);
                maxHeapify(arr, cMax, len);
            }
        }

        void swap(int[] arr, int index1, int index2) {
            int tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;
        }

    }

    // time O(logN)
    // espace O(1)
    class HEAP {
        public int findKthLargest(int[] nums, int k) {
            // k size 的 最小堆
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            if (nums.length < k) {
                for (int i = 0; i < nums.length; i++) {
                    queue.offer(nums[i]);
                }
                return queue.peek();
            }
            for (int i = 0; i < k; i++) {
                queue.offer(nums[i]);
            }
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
            return queue.peek();
        }
    }

    // quicksort 和 LeastKSmallest40 有点类似
    // quick sort 从小到达排序 找Kth largest === length - k 就是k最大
    // time O(N)
    // espace O(logN)
    class Partition {
        public int findKthLargest(int[] nums, int k) {
            return quickSort(nums, nums.length - k, 0, nums.length - 1);
        }

        int quickSort(int[] arr, int k, int l, int r) {
            int i = l, j = r;
            while (i < j) {
                while (i < j && arr[j] >= arr[l]) {
                    j--;
                }
                while (i < j && arr[i] <= arr[l]) {
                    i++;
                }
                swap(arr, i, j);
            }
            swap(arr, i, l);
            if (i > k)
                return quickSort(arr, k, l, i - 1);
            if (i < k)
                return quickSort(arr, k, i + 1, r);
            return arr[k];
        }

        void swap(int[] arr, int i, int j) {
            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }
    }
}
