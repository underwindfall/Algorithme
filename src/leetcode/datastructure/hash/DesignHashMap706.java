package leetcode.datastructure.hash;

import java.util.Iterator;
import java.util.LinkedList;

// https://leetcode.com/problems/design-hashmap
@SuppressWarnings("unchecked")
public class DesignHashMap706 {
    private static final int BASE = 769;
    private LinkedList[] data;

    DesignHashMap706() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Pair>();
        }
    }

    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value));
    }

    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    int hash(int key) {
        return key % BASE;
    }

    class Pair {
        private int key;
        private int value;

        Pair(int k, int v) {
            key = k;
            value = v;
        }

        int getKey() {
            return key;
        }

        int getValue() {
            return value;
        }

        void setValue(int v) {
            this.value = v;
        }
    }
}
