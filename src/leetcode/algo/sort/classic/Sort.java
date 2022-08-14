package leetcode.algo.sort.classic;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

// https://zhuanlan.zhihu.com/p/42586566
public class Sort {
    public int heapSort(int[] nums, int k) {
        int len = nums.length;
        // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        // Java 里没有 heapify ，因此我们逐个将前 k 个元素添加到 minHeap 里
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < len; i++) {
            // 看一眼，不拿出，因为有可能没有必要替换
            Integer topElement = minHeap.peek();
            // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
            if (nums[i] > topElement) {
                // Java 没有 replace()，所以得先 poll() 出来，然后再放回去
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    /**
     * 建堆过程 建立最大值堆或最小值堆
     * 都是完全二叉树
     * 3 2 3 1 2 4 5 5 6
     * 用数组表示的完全二叉树，对于位置i来说，2 * i + 1 2*i + 2 分别为其左右子节点 i 从 0 开始 最多到堆大小的一半
     */
    public int buildMaxHead(int[] nums, int k) {
        int heapSize = nums.length;
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
            // System.out.println(Arrays.toString(nums));
        }
        int index = nums.length - 1;

        for (int i = 0; i < k - 1; i++) {
            swap(nums, 0, index--);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
            // System.out.println(Arrays.toString(nums));
        }
        return nums[0];
    }

    public void maxHeapify(int[] nums, int i, int heapSize) {// 这样怎么处理右子节点比根大比左子节点小？ 不用处理 堆值只要保证根节点大于左右子节点即可
        int l = 2 * i + 1, r = 2 * i + 2, largest = i;
        if (l < heapSize && nums[l] > nums[largest]) {
            largest = l;
        }
        if (r < heapSize && nums[r] > nums[largest])
            largest = r;
        if (largest != i) {
            swap(nums, i, largest);
            maxHeapify(nums, largest, heapSize);
        }
    }

    /**
     * 建最小堆 堆值为 k
     * 上浮 调整数组顺序以建立最小堆
     * 下沉 交换堆顶元素并将其下沉到合适的位置
     */
    public int buildMinHeap(int[] nums, int k) {
        int heapSize = k - 1;
        for (int i = heapSize / 2; i >= 0; i--)
            swim(nums, i, heapSize); // 建堆
        // System.out.println(Arrays.toString(nums));
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                swap(nums, 0, i);
                swim(nums, 0, heapSize);
            }
            // System.out.println(Arrays.toString(nums));
        }
        return nums[0];
    }

    public void swim(int[] nums, int i, int heapSize) {// 上浮建堆 也可用于调整堆
        int l = 2 * i + 1, r = 2 * i + 2, smallest = i;
        if (l <= heapSize && nums[l] < nums[smallest])
            smallest = l;
        if (r <= heapSize && nums[r] < nums[smallest])
            smallest = r;
        if (smallest != i) {
            swap(nums, i, smallest);
            swim(nums, smallest, heapSize);
        }
        // System.out.println(Arrays.toString(nums));
    }

    /**
     * 冒泡排序 222ms
     * 遍历n次，每次两两比较，如果存在左值小于右值，则交换左右值
     * 加入flag判断，提前终止循环，时间116ms
     */
    int ans = 0;

    public int BubbleSort(int[] nums, int k) {
        for (int i = nums.length - 1; i >= 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (flag == false)
                break;
        }
        // System.out.println(Arrays.toString(nums));
        return nums[k - 1];
    }

    /**
     * 直接插入排序 120ms
     * 直接将数组中一个数插入到已经进行排序的数组中去
     * 前半部分都已经排好序
     */
    public int InsertSort(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            int temp = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[temp] < nums[j]) {
                    swap(nums, temp, j);
                    temp -= 1;
                }
            }
            // System.out.println(Arrays.toString(nums));
        }
        return nums[nums.length - k];
    }

    /**
     * 直接插入排序对于部分有序数组很有效，但是每次只能交换相邻元素
     * shell排序在直接插入排序数组的基础上进行改进，可以交换距离为h的元素，将一个大数组拆分为若干个距离为h的小数组，在小数组中使用直接插入排序，不断降低h的值到h=1，在大数组上使用直接插入排序
     */
    public int ShellSort(int[] nums, int k) {
        int N = nums.length;
        int h = 1;
        while (h < N / 5)
            h = 5 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h = h / 5;
        }
        return nums[nums.length - k];
    }

    /**
     * 选择排序
     * 每次选择未排序序列中的最小值与未排序序列的第一个位置交换
     * 超时！
     */
    public int selectSort(int[] nums, int k) {
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            min = nums[i];
            int tempIndex = -1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] <= min) {
                    min = nums[j];
                    tempIndex = j;
                }
            }
            if (tempIndex == -1)
                break;
            swap(nums, i, tempIndex);
        }
        return nums[nums.length - k];
    }

    /**
     * 快速排序 填坑+分治法
     * 填坑 要先挖坑 每次随机选取一个基准点 取出来 基准左右分区
     * 选中间值作为基准点
     * 选左值作为基准点
     * 随机值作为基准点
     * 发现选中间值最快
     */
    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            // Random random = new Random();
            // int mid = left + (right - left) / 2;
            // int randomIndex = left + random.nextInt(right - left + 1);
            int i = left, j = right;
            swap(nums, i, (left + right) / 2);
            // swap(nums,i,randomIndex);
            int pivot = nums[i];
            while (i < j) {
                while (i < j && nums[j] >= pivot)
                    j--; // 从右往左找比pivot小的值
                if (i < j)
                    nums[i++] = nums[j];
                while (i < j && nums[i] < pivot)
                    i++;// 从左到右找大于等于pivot的值
                if (i < j)
                    nums[j--] = nums[i];
            }
            nums[i] = pivot;
            quickSort(nums, left, i);
            quickSort(nums, i + 1, right);
        }
    }

    // 快速排序
    void quick_sort(int s[], int l, int r) {

        Random random = new Random();
        if (l < r) {
            swap(s, l, (l + r) / 2); // 将中间的这个数和第一个数交换 参见
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    s[i++] = s[j];
                while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }

    /**
     * 快速选择排序，专门用来解决第k大问题
     * 只排序半边，保证每次取出基准值，排序后该基准值右侧都比他小，左侧都比他大，
     * 位置为k的基准值，就是要找的！
     */
    int quickPartition(int[] nums, int left, int right, int k) {
        Random random = new Random();
        int mid = left + (right - left) / 2;
        // int randomIndex = left + random.nextInt(right - left + 1);
        int i = left, j = right;
        swap(nums, i, (left + right) / 2);
        // swap(nums,i,randomIndex);
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= pivot)
                j--; // 从右往左找比pivot小的值
            if (i < j)
                nums[i++] = nums[j];
            while (i < j && nums[i] < pivot)
                i++;// 从左到右找大于等于pivot的值
            if (i < j)
                nums[j--] = nums[i];
        }
        nums[i] = pivot;
        if (i == k) {
            return nums[i];
        }
        if (i > k)
            return quickPartition(nums, left, i, k);
        else
            return quickPartition(nums, i + 1, right, k);
    }

    /**
     * 计数排序 是一种桶排序
     * 需要一个额外数组存储数组中每个元素出现次数，
     * 然后从小到大依次插入回数组
     * 4ms 赶上快排了 以空间换时间，元素少时很快
     */
    public int countSort(int[] nums, int k) {
        int max = maxValue(nums);
        int min = minValue(nums);
        // System.out.println(max);
        int bucketLen = max - min + 1;
        int[] bucket = new int[bucketLen];
        for (int i = 0; i < nums.length; i++)
            bucket[nums[i] - min] += 1; // 偏移值，以到以0为起点，max为终点
        // System.out.println(Arrays.toString(bucket));
        int newIndex = 0;
        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i] > 0) {
                nums[newIndex++] = i + min; // 再移回来偏移值
                bucket[i] -= 1;
            }
            // System.out.println(Arrays.toString(bucket));
        }
        // System.out.println(Arrays.toString(nums));
        return nums[nums.length - k];
    }

    /**
     * 桶排序，计数排序的升级
     * 分成若干个桶 桶内进行排序
     * 桶数量为5个
     * 需要将数组中数分入到5个桶中去
     * 桶用什么数据结构？
     * 还需要从小到大把桶的数据分配进去
     * 然后在从小到大取出来，
     * ArrayList吧，桶的大小是不固定的
     */
    public int bucketSort(int[] nums, int k) {
        return 0;
    }

    /**
     * 基数排序 按位数分配
     * 按位数从低到高分配到桶中，有多少位分配多少次，分配完成后，从低位到高位依次取出
     */
    public int radixSort(int[] arr, int m) {
        return 0;
    }

    /**
     * 递归进行划分
     * 回溯的时候进行合并
     */
    public void mergeSort(int[] nums, int left, int right) {
        if (left == right)
            return;
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int[] tempArray = new int[right - left + 1];
        int tempIndex = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j])
                tempArray[tempIndex++] = nums[i++];
            else
                tempArray[tempIndex++] = nums[j++];
        }
        while (i <= mid)
            tempArray[tempIndex++] = nums[i++];
        while (j <= right)
            tempArray[tempIndex++] = nums[j++];
        tempIndex = 0;
        while (left <= right)
            nums[left++] = tempArray[tempIndex++];
    }

    public int minValue(int[] nums) {
        int min = nums[0];
        for (int i = 0; i < nums.length; i++)
            min = nums[i] < min ? nums[i] : min;
        return min;
    }

    public int maxValue(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = nums[i] > max ? nums[i] : max;
        }
        // System.out.format("max: %d \n",max);
        return max;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
