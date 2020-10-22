package training.array05;

public class TwoSum {
    // 二分法
    // time: O(N*logN)
    //espace: O(1)
    static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[] { i + 1, mid + 1 };
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[] { -1, -1 };
    }

    //双向指针法
    //time: O(N/2) === O(N)
    //espace: O(1)
    static int[] twoSum1(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum < target) {
                low ++;
            } else if (sum > target) {
                high --; 
            } else {
                return new int[] {low + 1, high + 1};
            }
        }
        return new int[] {-1, -1};
      }

    public static void main(String[] args) {
        int[] numbers = new int[] { 2, 7, 11, 15 };
        int target = 9;
        printResult(twoSum(numbers, target));
        printResult(twoSum1(numbers, target));
    }

    private static void printResult(int[] origin) {
        for (int i = 0; i < origin.length; i++) {
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append(origin[i] + ",");
            System.out.println(stringBuilder + "\n");
        }
    }
}
