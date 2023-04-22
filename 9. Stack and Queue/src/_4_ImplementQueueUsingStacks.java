/*
Question: Implement Queue using Stacks
--------------------------------------
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:
void push(int x) : Pushes element x to the back of the queue.
int pop() : Removes the element from the front of the queue and returns it.
int peek() : Returns the element at the front of the queue.
boolean empty() : Returns true if the queue is empty, false otherwise.

Notes:
You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false

Time Complexity: Only enQueue is O(n), others are O(1).
Space Complexity: O(2N) --> 2 stacks are used
  
 */

import java.util.Stack;

public class _4_ImplementQueueUsingStacks {

    Stack<Integer> st1;
    Stack<Integer> st2;

    public _4_ImplementQueueUsingStacks() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    public void enQueue(int x) {
        while(!st1.isEmpty()){
            st2.push(st1.pop());
        }

        st1.push(x);

        while(!st2.isEmpty()) {
            st1.push(st2.pop());
        }
    }

    public int dequeue() {
        return st1.pop();
    }

    public int peek() {
        return st1.peek();
    }

    public boolean empty() {
        return st1.isEmpty();
    }
}
