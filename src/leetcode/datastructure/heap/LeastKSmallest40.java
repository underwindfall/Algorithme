package leetcode.datastructure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
public class LeastKSmallest40 {
    // 维护一个 最大堆 最大堆的数量是K
    // 依次将元素添加到「最大堆」中；
    // 当「最大堆」的元素个数达到 K 时，将当前元素与堆顶元素进行对比
    // 如果当前元素大于堆顶元素，则放弃当前元素，继续进行下一个元素；
    // 如果当前元素小于堆顶元素，则删除堆顶元素，将当前元素加入到「最小堆」中。

    class Heap {
        public int[] getLeastNumbers(int[] arr, int k) {
            int[] result = new int[k];
            if (k == 0) {
                return result;
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int i = 0; i < k; i++) {
                queue.offer(arr[i]);
            }
            for (int i = k; i < arr.length; i++) {
                if (queue.peek() > arr[i]) {
                    queue.poll();
                    queue.offer(arr[i]);
                }
            }
            for (int i = 0; i < k; i++) {
                result[i] = queue.poll();
            }
            return result;
        }
    }

    // 快排的步骤有两个 哨兵划分， 递归左右数组为1 求解
    // time O(N*logN)
    // espace O(N)
    class QuickSort {
        public int[] getLeastNumbers(int[] arr, int k) {
            quickSort(arr, 0, arr.length - 1);
            return Arrays.copyOf(arr, k);
        }

        void quickSort(int[] arr, int l, int r) {
            // 子数组长度为 1 时终止递归
            if (l >= r)
                return;
            // 哨兵划分操作（以 arr[l] 作为基准数)
            int i = l, j = r;
            while (i < j) {
                // 右边找到第一个小于哨兵节点的数字
                while (i < j && arr[j] >= arr[l]) {
                    j--;
                }
                // 左边找到第一个大于哨兵节点的数字
                while (i < j && arr[i] <= arr[l]) {
                    i++;
                }
                swap(arr, i, j);
            }
            // 交换哨兵节点
            swap(arr, i, l);
            // 递归左（右）子数组执行哨兵划分
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);
        }

        void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // time O(N)
    // espace O(logN)
    class QuickSortK {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k >= arr.length)
                return arr;
            return quickSort(arr, k, 0, arr.length - 1);
        }

        int[] quickSort(int[] arr, int k, int l, int r) {
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
            return Arrays.copyOf(arr, k);
        }

        void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
