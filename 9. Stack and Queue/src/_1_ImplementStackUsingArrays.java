public class _1_ImplementStackUsingArrays {

    private int data[];
    private int top;  // is the index of topmost element of stack

    public _1_ImplementStackUsingArrays(){   // constructor
        data = new int[10];
        top = -1;
    }
    public _1_ImplementStackUsingArrays(int capacity){   // other constructor
        data = new int[capacity];
        top = -1;
    }

    public boolean isEmpty(){
        if (top == -1){
            return  true;
        }else {
            return false;
        }
//        return (top == -1);
    }

    public int size(){
        return top + 1;  // Initially when Stack has made, the Top is -1
        // And Stack size is zero
        // So, top(-1) + 1 will be zero
    }

    public int top() throws StackEmptyException{
        if (size() == 0){
            // StackEmptyException
            StackEmptyException e = new StackEmptyException();
            throw e;
        }
        return data[top];
    }

    public void push(int elem) throws StackFullException {
        if (size() == data.length){
            // StackFullException
            StackFullException e = new StackFullException();
            throw e;
        }
        top++;
        data[top] = elem;
    }

    public int pop() throws StackEmptyException {
        if (size() == 0){
            // StackEmptyException
            StackEmptyException e = new StackEmptyException();
            throw e;
        }
        int temp = data[top];
        top--;
        return temp;
    }
}
