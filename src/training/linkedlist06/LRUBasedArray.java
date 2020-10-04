package training.linkedlist06;

/**
 * 
 */
public class LRUBasedArray<T> {
    private static final Integer INITIAL_SIZE = 10;
    private int capacity;
    private T[] value;
    private int count;

    LRUBasedArray() {
        this(INITIAL_SIZE);
    }

    LRUBasedArray(int capacity) {
        this.capacity = capacity;
        this.value = (T[]) new Object[capacity];
        count = 0;
    }

    public void set(T data) {
        int targetIndex = 0;
        for (int i = 0; i <= capacity - 1; i++) {
            if (value[i] == data) {
                targetIndex = i;
                count--;
                break;
            } else {
                targetIndex = i;
            }
        }

        if (count < capacity) {
            System.out.println(" count" + count);
            for (int j = count - 1; j >= 0; j--) {
                value[j + 1] = value[j];
            }
            value[0] = data;
            System.out.println(" value[0]" + toString());
        } else {
            for (int j = targetIndex - 1; j >= 0; j--) {
                value[j + 1] = value[j];
            }
            value[0] = data;
        }

        System.out.println("targetIndex" + targetIndex);

        count++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUBasedArray<Integer> lru = new LRUBasedArray<>(3);
        lru.set(1);
        lru.set(2);
        lru.set(3);
        lru.set(4);
        lru.set(5);
        System.out.println(lru);
        lru.set(6);
        lru.set(7);
        lru.set(8);
        lru.set(9);
        System.out.println(lru);
    }
}
