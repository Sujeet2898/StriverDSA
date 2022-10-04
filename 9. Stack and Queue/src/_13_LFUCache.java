/*
Question: LFU Cache
-------------------
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:
1. LFUCache(int capacity) : Initializes the object with the capacity of the data structure.
2. int get(int key) : Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
3. void put(int key, int value) : Update the value of the key if present, or inserts the key if not already present.
                                 When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item.
                                 For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.

To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
-----------------------------------------------------------------------
The functions get and put must each run in O(1) average time complexity.
-----------------------------------------------------------------------

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
 */

    /*
    Approach: Create a class called Node which will be a node of a DoublyLinkedList having key, value, frequency,prevNode and nextNode.
          Use a HashMap (keyNodeMap: key -> Node) to handle data access by key.
          Then use a HashMap (freqNodeDLLMap: frequency -> DoublyLinkedList<Node>) to handle frequency.
          Also, maintain a variable (minumumFrequency) which will store the current minimum frequency of keys in cache.
          Thus if we want to add a new key, we just need to find (or create new) the likedlist by its frequency (which is 1),
          add the item to the start of the linked list.
          If cache is full and we need to remove an item, we will get the minimum frequency (minumumFrequency),
          get the appropriate linkedlist from freqNodeDLLMap by it, then remove the last item of that linkedlist.
          Also we'll use the key of that removed item to remove the item from our cache (keyNodeMap).
          If we want to increment the freqneucy of a key, we'll get the node, remove it from its current frequency linked list
          by joining it's prevNode and nextNode (This is why we're using DoublyLinkedList. No need to find a node by traversing
          to remove it. If we have the node, we can just join its previous and next node to remove it.). Then add the node to
          the linkedlist of the new (incremented) frequency.
          Thus, the frequency ranking management will be done in O(1) time.

    Complexity analysis: Time: O(1), Space: O(n).

    */

import java.util.HashMap;

public class _13_LFUCache {

    int capacity;
    HashMap<Integer, Node> keyNodeMap = new HashMap<>();
    HashMap<Integer, NodeDLinkedList> freqNodeDLLMap = new HashMap<>();
    int minimumFrequency = 1;

    public _13_LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = keyNodeMap.get(key);
        if(node != null){   //Item exists
            incrementFrequency(node); //Increment frequency
            return node.value;  //Return value
        }
        else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(capacity <= 0) {
            return;
        }
        //Item exists
        if(keyNodeMap.containsKey(key)){
            Node node = keyNodeMap.get(key); //Get old node
            node.value = value;              //Update with new value
            incrementFrequency(node);        //Increment frequency
            keyNodeMap.put(key, node);        //Put (update) in cache
        }
        //Item doesn't exist
        else{
            Node node = new Node(key, value);  //Create new node

            if(keyNodeMap.size() == capacity){   //Cache is full
                Node removedLastNode = freqNodeDLLMap.get(minimumFrequency).removeLastNode(); //Remove LFU node from removedLastNode
                keyNodeMap.remove(removedLastNode.key); //Remove LFU node from cache
            }

            incrementFrequency(node);  //Add to frequency map
            keyNodeMap.put(key, node);  //Add to cache

            minimumFrequency = 1;  //Since new node is having frequency as 1, update minimumFrequency to be 1
        }

    }


    private void incrementFrequency(Node node){

        int oldFrequency = node.frequency;

        if(freqNodeDLLMap.containsKey(oldFrequency)){   //Frequency already exists
            NodeDLinkedList oldNodeDLinkedList = freqNodeDLLMap.get(oldFrequency);  //Get frequency linked-list
            oldNodeDLinkedList.remove(node);   //Remove current node
            if(node.frequency == minimumFrequency && oldNodeDLinkedList.length == 0){
                minimumFrequency++; //If this frequency was the minimum freq. and no node is having this freq anymore, Increment minumum frequency
            }
        }

        int newFrequency = oldFrequency + 1; //Increment frequency
        node.frequency = newFrequency;   //Set new frequency to node
        NodeDLinkedList newNodeDLinkedList = freqNodeDLLMap.getOrDefault(newFrequency, new NodeDLinkedList()); //Get or create the LinkedList for this new frequency
        newNodeDLinkedList.add(node);  //Add node to the freq linked-list
        freqNodeDLLMap.put(newFrequency, newNodeDLinkedList); //Put freq linked-list to freqNodeDLLMap
    }


    private class Node{
        int key;
        int value;
        int frequency;
        Node prev;
        Node next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

    private class NodeDLinkedList{
        Node head, tail;
        int length;

        //Add a node to top
        void add(Node node){

            //Remove old pointers
            node.prev = null;
            node.next = null;

            if(head == null){  //Empty list
                head = node;
                tail = node;
            }
            else{
                node.next = head;     //Forward linking
                head.prev = node;     //Backward linking
                head = node;
            }

            length++;
        }

        //Remove a node
        void remove(Node node){

            if(node == head){  //Need to remove head node
                if(node == tail){ //Tail node is the same (list size = 1)
                    tail = null;  //Make tail null
                }
                head = head.next;  //Make head point to the next node
            }
            else{    //Need to remove later node
                node.prev.next = node.next;  //Forward linking

                if(node == tail){   //Need to remove tail node
                    tail = node.prev; //Point tail to prev node
                }
                else{
                    node.next.prev = node.prev; //Backward linking
                }
            }

            length--;

        }

        //Remove last node
        Node removeLastNode(){

            Node tailNode = tail;

            if(tailNode != null){  //LastNode exists
                remove(tailNode);
            }
            return tailNode;
        }
    }
}
