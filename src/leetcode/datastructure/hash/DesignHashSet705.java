package leetcode.datastructure.hash;

import java.util.Iterator;
import java.util.LinkedList;

// https://leetcode.com/problems/design-hashset/
@SuppressWarnings("unchecked")
public class DesignHashSet705 {
    private static final int BASE = 769;
    private LinkedList[] data;

    DesignHashSet705() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }


    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while(iterator.hasNext()) {
            Integer element = iterator.next();
            if(element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }


    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while(iterator.hasNext()) {
            Integer element = iterator.next();
            if(element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while(iterator.hasNext()) {
            Integer element = iterator.next();
            if(element == key) {
                return true;
            }
        }
        return false;
    }


    int hash(int key) {
        return key % BASE;
    }
}
