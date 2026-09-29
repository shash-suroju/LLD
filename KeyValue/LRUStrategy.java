/**
Qn:: key-value store

LRU, LFU, FIFO...
// strategy pattern for extensibility

size - N
if size reaches N remove the least recently used


LRU -> doubly linkedlist
Head<->Node1......Noden<->tail
Node1 is the most recently accessed node next to the node

Node {
    next Node
    prev Node
    value String
    key String
}

1. addNode() -> adds the node next to the head in O(1) time

2. deleteNode() -> deletes the given node from the list in O(1) time


KeyValue functionalities:
1. getKey() => returns value in O(1);
    -> update the key's position to next of the head

2. putKey(key) => adds new node to the doubly LL after head

3. deleteKey(key) => deletes the key-value pair.

Map<String, Node> => {map of keys & its node value}
Node head, tail => references for our DLL;

LIFO -> store the frequenciess too.. -> mapped
*/


import java.util.*;


public class LRUStrategy implements KeyValueStrategy {
    
    static class Node {
        String key;
        String value;
        Node prev;
        Node next;
        
        Node (String key, String value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    
    
    private void addNode(Node temp) {
        Node hNext = head.next;
        
        head.next = temp;
        hNext.prev = temp;
        temp.next = hNext;
        temp.prev = head;
    }
    
    
    private void deleteNode(Node temp) {
        Node prev = temp.prev;
        Node next = temp.next;
        
        prev.next = next;
        next.prev = prev;
    }
    
    
    Map<String, Node> map;
    
    int capacity;
    
    Node head, tail;
    
    public LRUStrategy(int size) {
        head = new Node("head", "v1");
        tail = new Node("tail", "v2");
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        capacity = size;
    }
    
    @Override
    public String getKey(String key) {
        if(map.containsKey(key)) {
            Node temp = map.get(key);
            //updating the position
            deleteNode(temp);
            addNode(temp);
            
            return temp.value;
        }
        return "Key not found";
    }
    
    @Override
    public void putKey(String key, String value) {
        
        if(map.containsKey(key)) {
            deleteNode(map.get(key));
            map.remove(key);
        }

        if(map.size() == capacity) {
            Node tailPrev = tail.prev;
            map.remove(tailPrev.key);
            deleteNode(tailPrev);
        }

        Node temp = new Node(key, value);
        addNode(temp);
        
        map.put(key, temp);
    }
    
    @Override
    public void deleteKey(String key) {
        Node temp = map.get(key);
        map.remove(key);
        
        deleteNode(temp);
        
    }
    
}


