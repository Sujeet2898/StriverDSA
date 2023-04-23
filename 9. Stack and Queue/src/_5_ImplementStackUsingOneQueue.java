/*
Question: Implement Stack using only one queue

Time Complexity: Only push is O(n), others are O(1).
Space Complexity: O(N) --> 1 queue is used

 */

import java.util.LinkedList;
import java.util.Queue;

public class _5_ImplementStackUsingOneQueue {

    Queue<Integer> queue;

    public _5_ImplementStackUsingOneQueue() {
        queue = new LinkedList<>();

    }

    public void push(int x) {

        queue.add(x);
        for(int i = 0; i < queue.size() - 1; i++){
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();  
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
