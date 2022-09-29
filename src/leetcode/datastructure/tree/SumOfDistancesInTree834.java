package leetcode.datastructure.tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode-cn.com/problems/sum-of-distances-in-tree/
public class SumOfDistancesInTree834 {
    //https://leetcode-cn.com/problems/sum-of-distances-in-tree/solution/shou-hua-tu-jie-shu-zhong-ju-chi-zhi-he-shu-xing-d/
    //time O(n)
    //space O(n)
    private List<List<Integer>> graph = new ArrayList<>();//邻接表
    int[] distSum; //距离和
    int[] nodeNum; //子树节点个数（包括自己）
    int N;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dst = edges[i][1];
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }
        distSum = new int[N];
        nodeNum = new int[N];
        Arrays.fill(nodeNum, 1);
        Arrays.fill(distSum, 0);
        postOrder(0, -1);
        preOrder(0, -1);
        return distSum;
    }


    //求root到子树所有节点的距离和
    void postOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for(Integer neighbor : neighbors) {
            if(neighbor == parent)
                continue; //如果邻接点是父节点，则跳过
            postOrder(neighbor, root);
            nodeNum[root] += nodeNum[neighbor];
            distSum[root] += distSum[neighbor] + nodeNum[neighbor];
        }
    }

    
    //根据root计算其邻居到所在子树之外的节点的距离和（包括root节点）
    void preOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for(Integer neighbor : neighbors) {
            if(neighbor == parent)
                continue;
            distSum[neighbor] = distSum[root] - nodeNum[neighbor] + (N - nodeNum[neighbor]);
            preOrder(neighbor, root);
        }
    }

//     nodeNum[i]：
//     状态：以节点i为根节点的子树的节点数  
//     初始化：nodeNum = [1] * N
//     状态转移：nodeNum[root] += nodeNum[neibor]
// dp[i]：
//     状态：在第一次dfs（后序）里表示节点i到它的子孙节点的距离和
//     初始化：dp = [0] * N
//     状态转移：dp[root] += dp[neibor] + nodeNum[neibor]
//         节点i的dp值等于i的所有孩子的dp值加上i的所有子孙的数量
//     状态：在第二次dfs（先序）里表示节点i到其他所有节点的距离和
//     初始化：第一次dfs后的状态
//     状态转移：dp[neibor] = dp[root] - nodeNum[neibor] + (N - nodenum[neibor]) = dp[root] + N - 2 * nodeNum[neibor]
//         dp[neibor] = dp[root] - ？？？：从当前父节点root推当前子节点neibor的dp值，即由所有节点到root推所有节点到neibor的距离
//         - nodeNum[neibor]：neibor的子节点原来是要走到root，现在只需要走到neibor，那么neibor的子节点需要走的距离少了1
//         + (N - nodenum[neibor])：不是neibor的子节点原来是要走到root，现在需要多走一步才能到neibor，它们要走的距离多了1
}
