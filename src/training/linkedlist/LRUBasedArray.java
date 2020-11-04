package training.linkedlist;

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

    @SuppressWarnings("unchecked")
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


// public class LRUBasedArray<T> {

//     private static final int DEFAULT_CAPACITY = (1 << 3);

//     private int capacity;

//     private int count;

//     private T[] value;

//     private Map<T, Integer> holder;

//     public LRUBasedArray() {
//         this(DEFAULT_CAPACITY);
//     }

//     public LRUBasedArray(int capacity) {
//         this.capacity = capacity;
//         value = (T[]) new Object[capacity];
//         count = 0;
//         holder = new HashMap<T, Integer>(capacity);
//     }

//     /**
//      * 模拟访问某个值
//      * @param object
//      */
//     public void offer(T object) {
//         if (object == null) {
//             throw new IllegalArgumentException("该缓存容器不支持null!");
//         }
//         Integer index = holder.get(object);
//         if (index == null) {
//             if (isFull()) {
//                 removeAndCache(object);
//             } else {
//                 cache(object, count);
//             }
//         } else {
//             update(index);
//         }
//     }

//     /**
//      * 若缓存中有指定的值，则更新位置
//      * @param end
//      */
//     public void update(int end) {
//         T target = value[end];
//         rightShift(end);
//         value[0] = target;
//         holder.put(target, 0);
//     }

//     /**
//      * 缓存数据到头部，但要先右移
//      * @param object
//      * @param end 数组右移的边界
//      */
//     public void cache(T object, int end) {
//         rightShift(end);
//         value[0] = object;
//         holder.put(object, 0);
//         count++;
//     }

//     /**
//      * 缓存满的情况，踢出后，再缓存到数组头部
//      * @param object
//      */
//     public void removeAndCache(T object) {
//         T key = value[--count];
//         holder.remove(key);
//         cache(object, count);
//     }

//     /**
//      * end左边的数据统一右移一位
//      * @param end
//      */
//     private void rightShift(int end) {
//         for (int i = end - 1; i >= 0; i--) {
//             value[i + 1] = value[i];
//             holder.put(value[i], i + 1);
//         }
//     }

//     public boolean isContain(T object) {
//         return holder.containsKey(object);
//     }

//     public boolean isEmpty() {
//         return count == 0;
//     }

//     public boolean isFull() {
//         return count == capacity;
//     }

//     @Override
//     public String toString() {
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < count; i++) {
//             sb.append(value[i]);
//             sb.append(" ");
//         }
//         return sb.toString();
//     }

//     static class TestLRUBasedArray {

//         public static void main(String[] args) {
//             testDefaultConstructor();
//             testSpecifiedConstructor(4);
// //            testWithException();
//         }

//         private static void testWithException() {
//             LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
//             lru.offer(null);
//         }

//         public static void testDefaultConstructor() {
//             System.out.println("======无参测试========");
//             LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
//             lru.offer(1);
//             lru.offer(2);
//             lru.offer(3);
//             lru.offer(4);
//             lru.offer(5);
//             System.out.println(lru);
//             lru.offer(6);
//             lru.offer(7);
//             lru.offer(8);
//             lru.offer(9);
//             System.out.println(lru);
//         }

//         public static void testSpecifiedConstructor(int capacity) {
//             System.out.println("======有参测试========");
//             LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>(capacity);
//             lru.offer(1);
//             System.out.println(lru);
//             lru.offer(2);
//             System.out.println(lru);
//             lru.offer(3);
//             System.out.println(lru);
//             lru.offer(4);
//             System.out.println(lru);
//             lru.offer(2);
//             System.out.println(lru);
//             lru.offer(4);
//             System.out.println(lru);
//             lru.offer(7);
//             System.out.println(lru);
//             lru.offer(1);
//             System.out.println(lru);
//             lru.offer(2);
//             System.out.println(lru);
//         }
//     }
// }