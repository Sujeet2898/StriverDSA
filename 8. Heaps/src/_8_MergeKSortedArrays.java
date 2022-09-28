/*
Link: https://www.youtube.com/watch?v=E5WSILx1q0Q
Question: Merge K Sorted Lists
1. You are given a list of lists, where each list is sorted.
2. You are required to complete the body of mergeKSortedLists function. The function is expected to merge k sorted lists to create one sorted list.
 */

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _8_MergeKSortedArrays {
    
    // Time complexity: O((N * K) * log(K)), Where ‘K’ is the number of arrays and ‘N’ is the average number of elements in every array. 
    // Reason: We are using the min-heap of size K. Due to the insertion and the removal of elements in the heap, the final complexity of this approach is O(K * N * log(K)).
    // Space complexity: O(N * K), where ‘K’ is the number of arrays and ‘N’ is the average number of elements in every array.
    // Reason: Since we are using a min-heap of size K arrays for the average N elements present in every array, therefore, the space complexity of the approach is O(N * K)
    
    public static class Pair implements Comparable<Pair> {
        int listIndex;
        int dataIndex;
        int value;

        Pair(int listIndex, int dataIndex, int value){
            this.listIndex = listIndex;
            this.dataIndex = dataIndex;
            this.value = value;
        }

        public int compareTo(Pair other){         // Pair1 & Pair2 are compared using interface 'Comparable'
            return this.value - other.value;      // 'this' means Pair1 & 'other' means Pair2
        }                                         // +ve ->  Pair1 > Pair2
    }                                             // -ve ->  Pair1 < Pair2
                                                  // 0 ->  Pair1 = Pair2

    public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> input){
        ArrayList<Integer> output = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // Adding value of '0'th index (dataIndex) of each sorted array (listIndex) into PriorityQueue
        for (int i = 0; i < input.size(); i++){
            Pair ldv = new Pair(i, 0, input.get(i).get(0));
            pq.add(ldv);
        }

        // Work is done till PriorityQueue becomes empty
        while (pq.size() > 0){

            // Remove the minElement of PriorityQueue
            Pair ldv = pq.remove();

            // And add the removedElement into the ArrayList
            output.add(ldv.value);

            // Increase the index of same removedElement of sortedArray
            ldv.dataIndex++;

            // If increaseIndex is less than size of that sortedArray,
            // then update with value of increaseIndex
            if (ldv.dataIndex < input.get(ldv.listIndex).size()){
                ldv.value = input.get(ldv.listIndex).get(ldv.dataIndex);

                // Add the updatedValue into PriorityQueue
                pq.add(ldv);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int k = scn.nextInt();
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
        while(k > 0) {
            int n = scn.nextInt();
            ArrayList<Integer> current = new ArrayList<Integer>();
            for(int i = 0; i < n; i++) {
                current.add(scn.nextInt());
            }
            input.add(current);
            k--;
        }
        ArrayList<Integer> output = mergeKSortedArrays(input);
        for(int i : output) {
            System.out.print(i + " ");
        }
    }
}

/*
Input:
3
4
10 20 30 40
5
5 9 12 18 32
3
11 15 17
Output:
5 9 10 11 12 15 17 18 20 30 32 40
 */
