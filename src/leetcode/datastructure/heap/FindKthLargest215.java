package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/kth-largest-element-in-an-array
public class FindKthLargest215 {
    // time O(N * logN)
    // espace O(logN)
    // 这个思路主要是推排序的 根本远离
    class HeapSort {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeapSize(nums, heapSize);

            for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
                swap(nums, 0, i);
                heapSize--;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        void buildMaxHeapSize(int[] nums, int heapSize) {
            for (int i = heapSize / 2; i >= 0; i--) {
                maxHeapify(nums, i, heapSize);
            }
        }

        void maxHeapify(int[] array, int index, int heapSize) {
            int left = index * 2 + 1, right = index * 2 + 1 + 1;
            int largest = index;
            if (left < heapSize && array[left] > array[largest]) {
                largest = left;
            }
            if (right < heapSize && array[right] > array[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(array, index, largest);
                maxHeapify(array, largest, heapSize);
            }
        }

        void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
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
