/*
Question: Top K Frequent Elements
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Input: nums = [1], k = 1
Output: [1]
 */

import java.util.HashMap;
import java.util.PriorityQueue;

public class _9_TopKFrequentElements {

    // Using minHeap
    // Time complexity: O(k+ (n-k) log k) == effectively O(n log k) using minHeap which limits the size of heap to 'k'
    // Reason: O(k) to build the heap; (n-k) times deletion occurs and therefore time would be (n-k)log k as the size is 'k'
    // Space complexity: O(N) --> The Hash map and the heap can have at most N elements. Hence, the total space complexity is O(N).


    public int[] topKFrequent1(int[] nums, int k) {
        if(nums.length == 0){
            return new int[k];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int val: nums){
            map.put(val, map.getOrDefault(val,0)+1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
        for(int val : map.keySet()){
            pq.add(val);
            if(pq.size() > k){
                pq.remove();
            }
        }

        int i = 0;
        int[] ans = new int[k];
        while(pq.size() != 0){
            int rem = pq.poll();
            ans[i] = rem;
            i++;
        }

        return ans;
    }

    // Using maxHeap
    // Time complexity: O(N * LOG(N)), where 'N' is the size of the input array.
    // Reason: Building hash map takes O(N) and, in the worst case, building heap will take O(N * LOG(N)) time as adding an element to heap takes LOG(N) time. Hence, the total time complexity is O(N * LOG(N)).
    // Space complexity: O(N) --> The Hash map and the heap can have at most N elements. Hence, the total space complexity is O(N).

    public int[] topKFrequent2(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        // Build map where the key is val and value is how often this element appears in 'ARR'.
        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        // Elements in heap will be sorted in descending order according to the frequency of the element.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));

        // Build heap of maximum size 'K'.
        for (int val : map.keySet()) {
            pq.add(val);
        }

        int[] ans = new int[k];

        // Build output array.
        for (int i = 0; i < k; i++) {
            ans[i] = pq.remove();
        }

        return ans;
    }
}
