package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/design-hashmap/
public class MyHashMap {
    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    class Bucket {
        private List<Pair<Integer, Integer>> container;

        Bucket() {
            container = new ArrayList<>();
        }

        public Integer get(Integer key) {
            for (Pair<Integer, Integer> pair : container) {
                if (pair.first.equals(key)) {
                    return pair.second;
                }
            }
            return -1;
        }

        public void update(Integer key, Integer value) {
            boolean found = false;
            for (Pair<Integer, Integer> pair : container) {
                if (pair.first.equals(key)) {
                    pair.second = value;
                    found = true;
                }
            }
            if (!found) {
                container.add(new Pair<>(key, value));
            }
        }

        public void remove(Integer key) {
            for (Pair<Integer, Integer> pair : container) {
                if (pair.first.equals(key)) {
                    container.remove(pair);
                    break;
                }
            }
        }
    }

    private int key_space;
    private List<Bucket> hash_table;

    MyHashMap() {
        key_space = 2069;
        hash_table = new ArrayList<>();
        for (int i = 0; i < key_space; i++) {
            hash_table.add(new Bucket());
        }
    }

    private int _hash(int key) {
        return key % key_space;
    }

    public void put(Integer key, Integer value) {
        int hashKey = _hash(key);
        hash_table.get(hashKey).update(key, value);
    }

    public int get(int key) {
        int hashKey = _hash(key);
        return hash_table.get(hashKey).get(key);
    }

    public void remove(int key) {
        int hashKey = _hash(key);
        hash_table.get(hashKey).remove(key);
    }

}
