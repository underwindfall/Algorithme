package leetcode.datastructure.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// https://leetcode-cn.com/problems/map-sum-pairs/
public class MapSum677 {
    // time O(N)
    // espace O(1)
    class TrieTree {
        class MapSum {
            class MapSumNode {
                MapSumNode[] next;
                int val;

                public MapSumNode() {
                    next = new MapSumNode[26];
                    val = 0;
                }
            }

            MapSumNode root;

            /** Initialize your data structure here. */
            public MapSum() {
                root = new MapSumNode();
            }

            public void insert(String key, int val) {
                MapSumNode node = root;
                for (int i = 0; i < key.length(); i++) {
                    int ch = key.charAt(i) - 'a';
                    if (node.next[ch] == null) {
                        node.next[ch] = new MapSumNode();
                    }
                    node = node.next[ch];
                }
                node.val = val;
            }

            public int sum(String prefix) {
                MapSumNode node = root;
                for (int i = 0; i < prefix.length(); i++) {
                    int ch = prefix.charAt(i) - 'a';
                    if (node.next[ch] == null) {
                        node = null;
                        break;
                    }
                    node = node.next[ch];
                }
                return getTreeSum(node);
            }

            int getTreeSum(MapSumNode node) {
                if (node == null) {
                    return 0;
                }
                int sum = 0;
                sum += node.val;
                for (MapSumNode i : node.next) {
                    sum += getTreeSum(i);
                }
                return sum;
            }
        }
    }

    class HashMapSolution {
        Map<String, Integer> map;

        /** Initialize your data structure here. */
        public HashMapSolution() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int sum = 0;
            for (Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().contains(prefix)) {
                    sum += entry.getValue();
                }
            }
            return sum;
        }
    }
}