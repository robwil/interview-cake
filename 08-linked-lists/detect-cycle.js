class LinkedListNode {
    constructor(value) {
      this.value = value;
      this.next = null;
    }
}
  
function containsCycle(firstNode) {
    if (firstNode === null || firstNode.next === null) {
        return false;
    }
    // detecting a cycle requires detecting whether we've been to a previous node before
    
    // One way to do this is with a hash-map. Traverse the linked list, storing each node in a Map, and return true when we see a previously visited node. A non-cycle list would eventually terminate and return false.
    // That would be O(N) time, O(N) space.

    // Is there a way to do it without extra space? We could use two pointers moving at different speeds. They'll eventually meet if there's a cycle.
    // That would be O(N) time, O(1) space.
    let currentNode = firstNode;
    let otherNode = firstNode.next;
    while (currentNode != null && otherNode != null) {
        currentNode = currentNode.next;
        otherNode = otherNode.next;
        if (otherNode != null) {
            otherNode = otherNode.next;
        }
        if (otherNode == currentNode) {
            return true;
        }
    }
    return false;
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'linked list with no cycle';
  let nodes = valuesToLinkedListNodes([1, 2, 3, 4]);
  assertEquals(containsCycle(nodes[0]), false, desc);
  
  desc = 'cycle loops to beginning';
  nodes = valuesToLinkedListNodes([1, 2, 3, 4]);
  nodes[3].next = nodes[0];
  assertEquals(containsCycle(nodes[0]), true, desc);
  
  desc = 'cycle loops to middle';
  nodes = valuesToLinkedListNodes([1, 2, 3, 4, 5]);
  nodes[4].next = nodes[2];
  assertEquals(containsCycle(nodes[0]), true, desc);
  
  desc = 'two node cycle at end';
  nodes = valuesToLinkedListNodes([1, 2, 3, 4, 5]);
  nodes[4].next = nodes[3];
  assertEquals(containsCycle(nodes[0]), true, desc);
  
  desc = 'empty list';
  assertEquals(containsCycle(null), false, desc);
  
  desc = 'one element linked list no cycle';
  let firstNode = new LinkedListNode(1);
  assertEquals(containsCycle(firstNode), false, desc);
  
  desc = 'one element linked list cycle';
  firstNode = new LinkedListNode(1);
  firstNode.next = firstNode;
  assertEquals(containsCycle(firstNode), true, desc);
  
  function valuesToLinkedListNodes(values) {
    const nodes = [];
    for (let i = 0; i < values.length; i++) {
      const node = new LinkedListNode(values[i]);
      if (i > 0) {
        nodes[i - 1].next = node;
      }
      nodes.push(node);
    }
    return nodes;
  }
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }