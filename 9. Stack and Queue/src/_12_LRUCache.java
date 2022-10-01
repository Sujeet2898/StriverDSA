/*
Question: LRU Cache
-------------------
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
-------------------------------------------------------------------------
The functions get and put must each run in O(1) average time complexity.
-------------------------------------------------------------------------
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 */

import java.util.HashMap;

public class _12_LRUCache {

    class Node{
        Node prev, next;
        int key, value;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    HashMap<Integer,Node> map = new HashMap();
    int capacity;

    public _12_LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // If key exist
        if(map.containsKey(key)) {

            // Get the address of that particular node from map
            Node node = map.get(key);

            // Remove that particular node from LinkedList because this is the Last Recently Used node
            remove(node);

            // Insert that particular node, right after head
            insert(node);

            // Return the value of that particular node
            return node.value;
        }
        // If key doesn't exist, return -1
        else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // Firstly check if the map contain key of that particular node
        if(map.containsKey(key)) {

            // Remove that key from map because new value has come in place of previous value of that key
            remove(map.get(key));
        }

        // If the size of map is full, remove the previous node of tail
        if(map.size() == capacity) {
            remove(tail.prev);
        }

        // Insert that removed node, right after head because that is the last recently used node
        insert(new Node(key, value));
    }

    private void remove(Node node) {
        // Firstly remove the node from map
        map.remove(node.key);

        // Rebuilding connection between node.prev and node.next in order to delete this node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node){
        // Firstly put the node into the map
        map.put(node.key, node);

        // Rebuilding the connection among three nodes: 1. head  2. node(node to be inserted)  3. headNext

        // Connection between 2 and 3
        node.next = head.next;
        node.next.prev = node;

        // Connection between 1 and 2
        head.next = node;
        node.prev = head;
    }
}

