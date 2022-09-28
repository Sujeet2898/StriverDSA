/*
Question: Find Median from Data Stream
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and
the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Implement the MedianFinder class:
MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class _7_FindMedianFromDataStream {

    // Time complexity: O(N * log(N)) --> There are N additions, and each new addition takes O(log(N)) time for transition (insertion/deletion) of elements between the two heaps. Hence, the overall Time Complexity is O(N * log(N)).
    // Space complexity: O(N) --> Maintaining the two heaps will require O(N) memory. Hence, the overall Space Complexity is O(N).

    // To store lower half of data stream eg. 1, 2, 3, 6
    PriorityQueue<Integer> lowerHalf;
    // To store upper half of data stream eg. 8, 9, 11
    PriorityQueue<Integer> upperHalf;

    /** initialize your data structure here. */
    public _7_FindMedianFromDataStream() {
        lowerHalf = new PriorityQueue<>(Collections.reverseOrder()); // Max heap : To fetch largest
        // element from lower half in O(1) time
        upperHalf = new PriorityQueue<>(); // Min heap : To fetch lowest
        // element from upper half in O(1) time
    }

    public void addNum(int num) {
        // Insert in lowerHalf if it is empty or if number being inserted is less than the peek of lowerHalf otherwise insert in upperHalf
        if(lowerHalf.isEmpty() || num <= lowerHalf.peek()){
            lowerHalf.add(num);
        }else{
            upperHalf.add(num);
        }

        // We also need to ensure that the halves are balanced i.e. there is no more than a difference of 1 in size of both halves
        if(upperHalf.size() > lowerHalf.size()){
            lowerHalf.add(upperHalf.poll());
        } else if(lowerHalf.size() > upperHalf.size() + 1){
            upperHalf.add(lowerHalf.poll());
        }
    }

    public double findMedian() {
        if(lowerHalf.size() == upperHalf.size()){
            return (double)(lowerHalf.peek() + upperHalf.peek())/2;
        }else{
            return (double)(lowerHalf.peek());
        }
    }
}
