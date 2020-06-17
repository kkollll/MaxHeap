import java.util.*;

public class Solution {

    public int[] topKFrequent(int[] nums, int k) {


        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int num : nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 0);
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (a, b) -> treeMap.get(a) - treeMap.get(b)
        );

        treeMap.forEach(
            (key, value) -> {
                if (queue.size() < k) {
                    queue.add(key);
                } else if (value > treeMap.get(queue.peek())) {
                    queue.remove();
                    queue.add(key);
                }
            }
        );

        int size = queue.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = queue.remove();
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 1, 1, 2, 2, 3};
        arr = solution.topKFrequent(arr, 2);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        list.forEach(System.out::println);
    }
}
