import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int n = 10000000;
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("With Heapify complete time: " + testHeap(arr, true) + " s.");
        arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("Without Heapify complete time: " + testHeap(arr, false) + " s.");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long t1 = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap(testData);
        } else {
            maxHeap = new MaxHeap();
            for (Integer i : testData) {
                maxHeap.add(i);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error.");
            }
        }
        long t2 = System.nanoTime();
        double t = (t2 - t1) / 1000000000.0;
        return t;
    }
}
