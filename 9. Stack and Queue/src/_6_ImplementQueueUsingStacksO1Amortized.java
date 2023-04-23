/*
Question: Implement Queue using Stacks O(1) amortized
I have one input stack, onto which I push the incoming elements, and one output stack, from which I peek/pop.
I move elements from input stack to output stack when needed, i.e., when I need to peek/pop but the output stack is empty.
When that happens, I move all elements from input to output stack, thereby reversing the order so it's the correct order for peek/pop.
The loop in peek does the moving from input to output stack. Each element only ever gets moved like that once, though,
and only after we already spent time pushing it, so the overall amortized cost for each operation is O(1).
 */

import java.util.Stack;

public class _6_ImplementQueueUsingStacksO1Amortized {

    Stack<Integer> input;
    Stack<Integer> output;

    public _6_ImplementQueueUsingStacksO1Amortized() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void enQueue(int x) {

        input.push(x);
    }

    public int deQueue() {
        int ans;

        if(!output.isEmpty()){
            ans = output.pop();
        }
        else{
            while(!input.isEmpty()){
                output.push(input.pop());  
            }
            ans = output.pop();
        }

        return ans;
    }

    public int peek() {
        int ans;

        if(!output.isEmpty()){
            ans = output.peek();
        }
        else{
            while(!input.isEmpty()){
                output.push(input.pop());
            }
            ans = output.peek();
        }

        return ans;
    }

    public boolean empty() {
        if(input.isEmpty() && output.isEmpty()) {
            return true;
        }
        return false;
    }
}

