package leetcode.algo.dp.maxnumNeigbors;
// https://leetcode.cn/problems/binary-tree-cameras/solution/shou-hua-tu-jie-cong-di-gui-you-hua-dao-dong-tai-g/

public class MinCam986 {
    // time O()
    // space O()
    // 当前minCam = left minCam + right minCam （不一定 + 1）
    // 1. 当前节点root放相机 （当前子树的相机保底为1）
    // -- 1.左右儿子都没有相机 被父亲监控
    // -- minCam(root.left, false, true) + minCam(root.right, false, true)
    // -- 2.左儿子放了相机，被自己监控。右儿子没相机，被父亲root监控
    // -- minCam(root.left, true, true) + minCam(root.right, false, true)
    // -- 3.左儿子没有相机，被父亲root监控，右儿子放了相机，被自己监控
    // -- minCam(root.left, false, true) + minCam(root.right, true, true)
    // 2. 当前root没有相机，但被root的父亲监控
    // -- 1.两个儿子都有相机， 自己被自己监控
    // -- 2.左儿子有相机，被自己监控，右儿子没有相机，被自己儿子监控
    // -- 3.左儿子没有相机，被自己儿子监控。右儿子有相机，被自己监控
    // 3. 当前root没有相机，也没被父亲监控，是被儿子监控
    // -- 1.两个儿子都有相机， 自己被自己监控
    // -- 2.左儿子有相机，被自己监控，右儿子没有相机，被自己儿子监控
    // -- 3.左儿子没有相机，被自己儿子监控。右儿子有相机，被自己监控
    public int minCameraCover(TreeNode root) {
        return Math.min(minCam(root, true, true), // 根节点有相机
                minCam(root, false, false)// 根节点没有相机，因为没有父亲，没有被父亲监控，是被儿子监控
        );
    }

    int minCam(TreeNode root, boolean placeCamera, boolean watched) {
        if (root == null) { // 遍历到null节点
            if (placeCamera) { // 父节点问当前null节点: 有相机的minCam，但null节点不可能有相机
                return Integer.MAX_VALUE; // 让它返回无穷大，在取 min 时被忽略掉
            } else { // 父节点问当前null节点: 没有相机的minCam，null不可能有相机
                return 0;// 所以返回0
            }
        }
        if (placeCamera) {// root 放了相机

            return 1 + Math.min(Math.min(
                    minCam(root.left, false, true) + minCam(root.right, false, true),
                    minCam(root.left, true, true) + minCam(root.right, false, true)),
                    minCam(root.left, false, true) + minCam(root.right, true, true));
        } else {
            if (watched) { // root没放相机，但被父亲监控了
                return Math.min(Math.min(Math.min(
                        minCam(root.left, true, true) + minCam(root.right, true, true),
                        minCam(root.left, true, true) + minCam(root.right, false, false)),
                        minCam(root.left, false, false) + minCam(root.right, true, true)),
                        minCam(root.left, false, false) + minCam(root.right, false, false));

            } else {// root没有相机，也没被父亲监控，被儿子监控了
                return Math.min(Math.min(
                        minCam(root.left, true, true) + minCam(root.right, true, true),
                        minCam(root.left, true, true) + minCam(root.right, false, false)),
                        minCam(root.left, false, false) + minCam(root.right, true, true));

            }
        }
    }

    class DP {
        public int minCameraCover(TreeNode root) {
            // withCam ：当前子树 root 有相机，情况下的 minCam
            // noCamWatchByDad ：当前子树 root 没有相机，被父亲监控，情况下的minCam
            // noCamWatchBySon ：当前子树 root 没有相机，被儿子监控，情况下的minCam
            int[] res = minCam(root);
            return Math.min(res[0], res[2]);
        }

        int[] minCam(TreeNode root) {
            if (root == null) {
                return new int[] { Integer.MAX_VALUE / 2, 0, 0 };
            }
            int[] left = minCam(root.left);
            int[] right = minCam(root.right);
            // 下面相当于状态转移方程
            int withCam = 1 + Math.min(Math.min(
                    left[1] + right[1],
                    left[0] + right[1]),
                    left[1] + right[0]);

            int noCamWatchByDad = Math.min(Math.min(Math.min(
                    left[0] + right[0],
                    left[0] + right[2]),
                    left[2] + right[0]),
                    left[2] + right[2]);

            int noCamWatchBySon = Math.min(Math.min(
                    left[0] + right[0],
                    left[0] + right[2]),
                    left[2] + right[0]);

            return new int[] { withCam, noCamWatchByDad, noCamWatchBySon };
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
