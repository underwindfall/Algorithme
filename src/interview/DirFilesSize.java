package interview;

import java.io.File;

/**
 * 给一个file system structure (json) 算出所有文件的内存大小。去壳：遍历树并求叶子的值之和
 */
public class DirFilesSize {
    public static void main(String[] args) {
        System.out.println(getDirSize(new File(".")));
    }

    static long getDirSize(File dirs) {
        if (dirs == null || dirs.listFiles() == null) {
            return 0;
        }
        long size = 0;
        for (File file : dirs.listFiles()) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += getDirSize(file);
            }
        }
        return size;
    }

    static int getSumTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return getSumTree(root.left) + getSumTree(root.right) + root.val;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
