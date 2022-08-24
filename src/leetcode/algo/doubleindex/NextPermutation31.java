package leetcode.algo.doubleindex;

// https://leetcode.com/problems/next-permutation/
public class NextPermutation31 {
    //time O(N)
    //space O(1)
    class LooTwice {
        public void nextPermutation(int[] nums) {
                //1. 1,2,3 ==> 1,3,2
                //1. 倒序遍历, 找到第一个数, 这个数比后面的数小;
                //2. 继续倒序遍历, 找到一个比上面的数大的数;
                //3. 交换
                //4. 把1中的这个数后面的数全部递增排列, 因为在1后面的数时递减排列的, 所以首尾交换即可获得升序排列       
                int len = nums.length;
                int i = len - 2; //i = len - 2 是为了防止下面nums[i + 1]越界!
                
                //1. 倒序遍历, 找到第一个数, 这个数比后面的数小;
                while(i >= 0){
                    if(nums[i] < nums[i + 1])break;
                    --i;
                }
            
                //2. 继续倒序遍历, 找到一个上面的数大的数
                if(i >= 0){
                    int j = len - 1;
                    while(j >= 0){
                        if(nums[j] > nums[i])break;
                        --j;
                    }
                    //3. 交换i和j
                    swap(nums, i, j); //交换i和j的位置
                }
                
                //4. 将 i后面的数升序排列, 只需要对撞双指针交换即可(因为i后面的数时降序的)
                reverse(nums, i + 1, len - 1);
            }

            void swap(int[] nums, int left, int right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }

            void reverse(int[] nums, int left, int right){
                while(left < right){
                    swap(nums, left, right);
                    ++left;
                    --right;
                }
            }
    }

    //time O(n)
    //space O(1)
    /*0*/ public void nextPermutation(int[] nums) {
        // pivot is the element just before the non-increasing (weakly decreasing) suffix
/*2*/   int pivot = indexOfLastPeak(nums) - 1;
        // paritions nums into [prefix pivot suffix]
        if (pivot != -1) {
            int nextPrefix = lastIndexOfGreater(nums, nums[pivot]); // in the worst case it's suffix[0]
            // next prefix must exist because pivot < suffix[0], otherwise pivot would be part of suffix
/*4*/       swap(nums, pivot, nextPrefix); // this minimizes the change in prefix
        }
/*5*/   reverseSuffix(nums, pivot + 1); // reverses the whole list if there was no pivot
/*6*/ }
    
    /**
     * Find the last element which is a peak.
     * In case there are multiple equal peaks, return the first of those.
     * @return first index of last peak
     */
/*1*/ int indexOfLastPeak(int[] nums) {
        for (int i = nums.length - 1; 0 < i; --i) {
            if (nums[i - 1] < nums[i]) return i;
        }
        return 0;
    }

    /** @return last index where the {@code num > threshold} or -1 if not found */
/*3*/ int lastIndexOfGreater(int[] nums, int threshold) {
        for (int i = nums.length - 1; 0 <= i; --i) {
            if (threshold < nums[i]) return i;
        }
        return -1;
    }

    /** Reverse numbers starting from an index till the end. */
    void reverseSuffix(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
