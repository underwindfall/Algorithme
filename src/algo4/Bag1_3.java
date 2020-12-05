package algo4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag1_3 {
    public class Bag<Item> implements Iterable<Item> {
        private Node<Item> first; // beginning of bag
        private int n; // number of elements in bag

        // helper linked list class
        private class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Initializes an empty bag.
         */
        public Bag() {
            first = null;
            n = 0;
        }

        /**
         * Returns true if this bag is empty.
         *
         * @return {@code true} if this bag is empty; {@code false} otherwise
         */
        public boolean isEmpty() {
            return first == null;
        }

        /**
         * Adds the item to this bag.
         *
         * @param item the item to add to this bag
         */
        public void add(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            n++;
        }

        /**
         * Returns the number of items in this bag.
         *
         * @return the number of items in this bag
         */
        public int size() {
            return n;
        }

        // an iterator, doesn't implement remove() since it's optional
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;

            public LinkedIterator(Node<Item> first) {
                current = first;
            }

            public boolean hasNext() {
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }

        @Override
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }

    }

    public static void main(String[] args) {
        Bag1_3 nBag1_3 = new Bag1_3();
        // List<Double> in = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        Bag<Double> numbers = nBag1_3.new Bag<Double>();
        for (Double double1 : numbers) {
            numbers.add(double1);
        }

        int N = numbers.size();
        double sum = 0.0;
        for (double x : numbers)
            sum += x;
        double mean = sum / N;
        sum = 0.0;
        for (double x : numbers)
            sum += (x - mean) * (x - mean);
        double std = Math.sqrt(sum / (N - 1));
        System.out.printf("Mean: %.2f\n", mean);
        System.out.printf("Std dev: %.2f\n", std);
    }

}
