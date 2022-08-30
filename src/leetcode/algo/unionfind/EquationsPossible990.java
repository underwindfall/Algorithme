package leetcode.algo.unionfind;

// https://leetcode.cn/problems/satisfiability-of-equality-equations/
public class EquationsPossible990 {
    // time O(n + C logC)
    // space O(C)
    public boolean equationsPossible(String[] equations) {
        UnionFind unionFind = new UnionFind(26);
        for (String eq : equations) {
            char[] arr = eq.toCharArray();
            if (arr[1] == '=') {
                int index1 = arr[0] - 'a';
                int index2 = arr[3] - 'a';
                unionFind.union(index1, index2);
            }
        }

        for (String eq : equations) {
            char[] arr = eq.toCharArray();
            if (arr[1] == '!') {
                int index1 = arr[0] - 'a';
                int index2 = arr[3] - 'a';
                if (unionFind.isConnected(index1, index2)) {
                    // 如果不在一个连通分量中，表示等式有矛盾，根据题意，返回 false
                    return false;
                }
            }
        }
        // 如果检查了所有不等式，都没有发现矛盾，返回 true
        return true;
    }

    // https://leetcode.cn/problems/satisfiability-of-equality-equations/solution/shi-yong-bing-cha-ji-chu-li-bu-xiang-jiao-ji-he-we/
    class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * @param x
         * @param y
         * @return 如果合并成功，返回 true
         */
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            parent[rootX] = rootY;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

}
