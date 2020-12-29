package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists
public class FindRestaurant599 {

    //思路将list放入hashmap
    //如果list2中存在map相同的key， 意味着他们有相同的喜欢的餐厅
    //比较他们喜欢的餐厅是否是最小的sumIndex
    //是的继续下一个不是的话替代并删除
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> result = new ArrayList<>();
        int minSum = Integer.MAX_VALUE, sum;
        for (int k = 0; k < list2.length; k++) {
            if (map.containsKey(list2[k])) {
                sum = k + map.get(list2[k]);
                if (sum < minSum) {
                    result.clear();
                    result.add(list2[k]);
                    minSum = sum;
                } else if (sum == minSum) {
                    result.add(list2[k]);
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }

}
