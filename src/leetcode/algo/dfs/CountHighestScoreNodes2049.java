package leetcode.algo.dfs;

// time O(n)
// space O(n)
class CountHigestScoreNodes2049 {
    // Construct tree by using the parents[] array.
    // Find the highest score
    // Count nodes with the highest score
    long highest = 0;

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        int res = 0;
        Node[] arr = new Node[n];
        // construct tree
        for (int i = 0; i < parents.length; i++) {
            arr[i] = new Node();
        }

        for (int i = 1; i < parents.length; i++) {
            int parentId = parents[i];
            if (arr[parentId].left == null) {
                arr[parentId].left = arr[i];
            } else {
                arr[parentId].right = arr[i];
            }
        }

        findSize(arr[0]);

        // find the highest score
        for (int i = 0; i < parents.length; i++) {
            long product = 1;
            int leftCnt = arr[i].left == null ? 0 : arr[i].left.cnt;
            int rightCnt = arr[i].right == null ? 0 : arr[i].right.cnt;
            int restCnt = n - 1 - leftCnt - rightCnt;

            if (leftCnt > 0) {
                product *= leftCnt;
            }
            if (rightCnt > 0) {
                product *= rightCnt;
            }
            if (restCnt > 0) {
                product *= restCnt;
            }
            arr[i].product = product;
            highest = Math.max(highest, product);
        }

        // count nodes
        for (int i = 0; i < parents.length; i++) {
            if (arr[i].product == highest) {
                res++;
            }
        }
        return res;
    }

    class Node {
        Node left;
        Node right;
        long product = 0L;
        int cnt = 0;

        public Node() {}
    }

    // time O(n)
    // space O(n)
    private int findSize(Node root) {
        if (root == null) {
            return 0;
        }
        int size = findSize(root.left) + findSize(root.right) + 1;
        root.cnt = size;
        return size;
    }
}
