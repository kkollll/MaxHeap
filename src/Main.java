import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        test1();
//        System.out.println();
//        test2(); // test2用时20分钟
        test3();
    }

    private static void test1() {
        long t1 = System.nanoTime();
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(100));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error.");
            }
        }
        long t2 = System.nanoTime();
        System.out.println("Test MaxHeap completed." + (t2 - t1) / 1000000000.0);
    }
    private static void test2() {
        long t1 = System.nanoTime();
        int n = 1000000;

        ArrayPriorityQueue<Integer> arrayPriorityQueue = new ArrayPriorityQueue<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arrayPriorityQueue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = arrayPriorityQueue.dequeue();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error.");
            }
        }
        long t2 = System.nanoTime();
        System.out.println("Test arrayPriorityQueue completed." + (t2 - t1) / 1000000000.0);
    }
    private static void test3() {
        long t1 = System.nanoTime();
        int n = 1000000;

        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            minHeap.add(random.nextInt(100));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = minHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new IllegalArgumentException("Error.");
            }
        }
        long t2 = System.nanoTime();
        System.out.println("Test MaxHeap completed." + (t2 - t1) / 1000000000.0);
    }
}
