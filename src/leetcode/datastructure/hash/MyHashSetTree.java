package leetcode.datastructure.hash;

public class MyHashSetTree {
    private Bucket[] bucketArray;
    private int keyRange;
    

    MyHashSetTree() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; ++i)
            this.bucketArray[i] = new Bucket();
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }

    class Bucket {
        private BSTTree tree;

        public Bucket() {
            tree = new BSTTree();
        }

        public void insert(Integer key) {
            tree.root = tree.insertToBST(tree.root, key);
        }

        public void delete(Integer key) {
            tree.root = tree.deleteNode(tree.root, key);
        }

        public boolean exists(Integer key) {
            BSTTree.TreeNode node = tree.searchBST(tree.root, key);
            return node != null;
        }
    }

    class BSTTree {
        TreeNode root = null;

        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int val) {
                this.val = val;
            }
        }

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null || root.val == val) {
                return root;
            }
            if (val < root.left.val) {
                return searchBST(root.left, val);
            } else {
                return searchBST(root.right, val);
            }
        }

        public TreeNode insertToBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            if (root.val < val) {
                root.right = insertToBST(root.right, val);
            }
            if (root.val > val) {
                root.left = insertToBST(root.left, val);
            }
            return root;
        }

        public TreeNode deleteNode(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    TreeNode minNode = getMinNode(root.right);
                    root.val = minNode.val;
                    root.right = deleteNode(root.right, minNode.val);
                }
            } else if (root.val > val) {
                root.left = deleteNode(root.left, val);
            } else {
                root.right = deleteNode(root.right, val);
            }
            return root;
        }

        private TreeNode getMinNode(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }
}
