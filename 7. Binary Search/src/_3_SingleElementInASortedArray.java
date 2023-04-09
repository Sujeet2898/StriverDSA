/*
Question: Single Element in a Sorted Array
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
element which appears exactly once.
Return the single element that appears only once.
Your solution must run in O(log n) time and O(1) space.

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Input: nums = [3,3,7,7,10,11,11]
Output: 10
 */

public class _3_SingleElementInASortedArray {

    // Using XOR(^)
    // Time Complexity: O(N)
    // Space Complexity: O(1)

    public int singleNonDuplicate1(int[] nums) {
        int n = nums.length;
        int element = 0;

        for (int i = 0; i < n; i++) {
            element = element ^ nums[i];
        }

        return element;
    }

    // Using Binary Search
    // Time Complexity: O(NlogN)
    // Space Complexity: O(1)  

    public int singleNonDuplicate2(int[] nums) {
        int low = 0; // first
        int high = nums.length - 2;  // second last

        while (low <= high){
            int mid = (low + high)/2;

            // In left array, the first instance of every element is occurring on the even index and the second instance on the odd index.
            // Similarly, in the right array, the first instance of every element is occurring on the odd index and the second index is occurring on the even index.

            // mid ^ 1 gives previous even index and next odd index
            if(nums[mid] == nums[mid ^ 1]){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        // single NonDuplicate will be at low pointer index
        return nums[low];
    }
}
