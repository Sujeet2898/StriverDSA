/*
Question: Sort a Stack
 */

import java.util.Stack;

public class _9_SortAStack {

    // Time Complexity: O(N^2) --> In the worst case for every sortStack(), sortedInsert() is called for ‘N’ times recursively for putting element to the right place
    // Space Complexity: O(N) --> This is the space used by the stack data structure to store the elements.

    public static void sortStack(Stack<Integer> stack){
        // base case
        if(stack.size()==0 || stack.size()==1){
            return;
        }

        int temp= stack.peek();
        stack.pop();
        sortStack(stack);
        insert(stack, temp);
    }

    public static void insert(Stack<Integer> stack, int temp){
        if(stack.empty()|| stack.peek() < temp){
            stack.push(temp);
            return;
        }
        int val= stack.peek();
        stack.pop();
        insert(stack,temp);
        stack.push(val);
    }
}
