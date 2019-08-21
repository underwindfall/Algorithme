package swordoffer.exercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 第3题
 * 一个二维数组，每一行从左到右递增，每一列从上到下递增．
 * 输入一个二维数组和一个整数，判断数组中是否含有整数
 */
public class JDuplicationInArray3 {

    //espace: O(n)
    public List<Integer> findDuplicationArray(int[] inputArray) {
        List<Integer> output = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inputArray.length; i++) {
            if (!hashMap.containsValue(inputArray[i])) {
                hashMap.put(i, inputArray[i]);
            } else {
                output.add(inputArray[i]);
            }
        }
        return output;
    }
}


