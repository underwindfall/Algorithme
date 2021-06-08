package leetcode.datastructure.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;



//https://leetcode-cn.com/problems/combination-sum/
public class CombinationSum39 {
    /**
     * 题目
     * 通过从target 寻找 每次 都是从candidates消减相对应位置上的值 知道最后target是负数或者为0时候停止
     * 然后通过剪枝操作 如果 已经走了-candidates[i] 那之后就不要操作这个i了 理由是 -candidates[i] 已经通过递归的形式剪掉了
     * @param candidates
     * @param target
     * @return
     */
    //backtracking
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res=  new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Deque<Integer> path= new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int startIndex, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
         // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        //剪枝操作在这里
        for (int i = startIndex; i < len; i++) {
            path.addLast(candidates[i]);
            dfs(candidates, i, len, target - candidates[i], path, res);
            //backtracking
            path.removeLast();
        }
    }
}
