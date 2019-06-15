package leetcode;

import java.util.*;

class LRUCache {

    class DLinkedNode {
        public DLinkedNode prev;
        public DLinkedNode next;
        public int key;
        public int value;

        public DLinkedNode() {

        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> map;
    private DLinkedNode head, tail;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    private void addNodeToHead(DLinkedNode node) {
        DLinkedNode currentNext = head.next;
        head.next = node;
        node.next = currentNext;
        node.prev = head;
        currentNext.prev = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
        node.next = node.prev = null;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNodeToHead(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode result = tail.prev;
        tail.prev = result.prev;
        tail.prev.next = tail;
        return result;
    }

    private void printList() {
        DLinkedNode current = head.next;
        while (current != tail) {
            System.out.print(current.key + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int get(int key) {
        printList();
        DLinkedNode result = map.get(key);
        if (result == null) {
            return -1;
        }
        moveToHead(result);
        return result.value;
    }

    public void put(int key, int value) {
        printList();
        DLinkedNode currentNode = map.get(key);
        if (currentNode == null) {
            // create new node
            DLinkedNode node = new DLinkedNode(key, value);
            // add to front of list
            addNodeToHead(node);
            // add node to map
            map.put(key, node);
            // check map size
            while (map.size() > capacity) { // should only have one iteration, but doing loop jic
                // remove least recently used items, which are the tails of the doubly linked list
                DLinkedNode toRemove = popTail();
                map.remove(toRemove.key);
            }
        } else {
            // update existing value
            currentNode.value = value;
            // move node to head
            moveToHead(currentNode);
        }
    }

    public void remove(int key) {
        DLinkedNode node = map.get(key);
        map.remove(key);
        removeNode(node);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("expected 1: " + cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println("expected -1: " + cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println("expected -1: " + cache.get(1));       // returns -1 (not found)
        System.out.println("expected 3: " + cache.get(3));       // returns 3
        System.out.println("expected 4: " + cache.get(4));       // returns 4
        cache.remove(4);
        cache.put(5, 5);
        System.out.println("expected 3: " + cache.get(3));

        System.out.println("--------");
        List<Integer> output = new LinkedList<>();
        cache = new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        output.add(cache.get(4));
        output.add(cache.get(3));
        output.add(cache.get(2));
        output.add(cache.get(1));
        cache.put(5,5);
        output.add(cache.get(1));
        output.add(cache.get(2));
        output.add(cache.get(3));
        output.add(cache.get(4));
        output.add(cache.get(5));
        System.out.println(output);
    }
}