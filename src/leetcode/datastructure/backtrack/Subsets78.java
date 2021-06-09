package leetcode.datastructure.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/subsets/
public class Subsets78 {
    //time O(2^N*N)
    //espace O(N)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, path, result);
        return result;
    }

    void dfs(int[] nums, int startIndex, List<Integer> path, List<List<Integer>> result) {

        result.add(new ArrayList<>(path));
        System.out.println("dfs=====" + path);
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            System.out.println(" before=====" + path);
            System.out.println(" level=====" + i);
            dfs(nums, i + 1, path, result);
            path.remove(path.size() - 1);
            System.out.println("after=====" + path);
        }
    }

    public static void main(String[] args) {
        Subsets78 subsets78 = new Subsets78();
        subsets78.subsets(new int[] { 1, 2, 3 });
    }
}
