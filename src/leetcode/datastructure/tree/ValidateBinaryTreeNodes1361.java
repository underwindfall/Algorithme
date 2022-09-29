package leetcode.datastructure.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.cn/problems/validate-binary-tree-nodes/
public class ValidateBinaryTreeNodes1361 {
    /**
     * 查找入度为0的节点
     * 即root点 可能会有多个 如果有多个就不是二叉树
     * 然后再判断连通性 用广搜统计每个节点遍历的情况 如果一个节点遍历次数为0或者大于1则都不是二叉树
     * 此时也算是对有两个root的情况下做了判断 因为只遍历一个root 另一个节点的访问次数肯定为0
     * leftChild[i]=j 表明i节点指向j
     */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] in = new int[n];// 记录每个节点的入度
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1)
                in[leftChild[i]]++;
            if (rightChild[i] != -1)
                in[rightChild[i]]++;
        }
        int root = -1;// 看看根节点是哪个
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            // 没有入度为0的 也就是没有根节点
            return false;
        }
        // 开始广搜
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();// 记录访问过的节点 如果有重复说明不是二叉树
        queue.offer(root);
        set.add(root);
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (leftChild[temp] != -1) {
                // 左子树不为空
                if (set.contains(leftChild[temp])) {
                    // 左子节点 被访问过
                    return false;
                }
                queue.offer(leftChild[temp]);
                set.add(leftChild[temp]);
            }
            if (rightChild[temp] != -1) {
                // 右子树不为空
                if (set.contains(rightChild[temp])) {
                    // 有子节点 被访问过
                    return false;
                }
                queue.offer(rightChild[temp]);
                set.add(rightChild[temp]);
            }
        }
        // 如果set里节点数量就为n那么说明全部节点被访问 因为上面有重复的已经判断了 只剩下没访问的没判断
        return set.size() == n;
    }
}
