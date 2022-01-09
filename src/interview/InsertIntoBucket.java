package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertIntoBucket {
    public static void main(String[] args) {
        List<Integer> input1 = Arrays.asList(1, 2, 3);
        List<Integer> input2 = Arrays.asList(1, 2, 3, 4, 5);
        insert(input1, 2, 2);
        insert(input2, 2, 2);
        insert(input2, 2, 3);
    }

    // time O(n)
    // space O(n)
    static void insert(List<Integer> numbers, int bucketSize, int totalBucket) {
        int size = numbers.size();
        int needBucket = size % bucketSize == 0 ? size / bucketSize : (size / bucketSize) + 1;

        totalBucket = Math.min(needBucket, totalBucket);

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < numbers.size();) {
            if (totalBucket > 0) {
                if (!map.containsKey(totalBucket)) {
                    map.put(totalBucket, new ArrayList<>());
                }
                for (int j = 0; j < bucketSize; j++) {
                    if (i >= numbers.size())
                        break;
                    map.get(totalBucket).add(numbers.get(i));
                    i++;
                }
                totalBucket--;
            } else {
                break;
            }
        }

        System.out.println(map.values());
    }

}
