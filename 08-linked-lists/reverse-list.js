class LinkedListNode {
    constructor(value) {
      this.value = value;
      this.next = null;
    }
}

function swap(node1, node2) {
    let tmp = node1.value;
    node1.value = node2.value;
    node2.value = tmp;
}

function traverseN(headOfList, n) {
    let currentNode = headOfList;
    for (let i = 0; i < n; i++) {
        currentNode = currentNode.next;
    }
    return currentNode;
}
  
// Reverse the linked list in place
function reverse(headOfList) {
    if (headOfList === null || headOfList.next === null) {
        return headOfList;
    }

    // Doing it in-place means they probably want O(1) space complexity.
    // Is it possible to do in O(N)? well, we can spend O(N) to get to the last node, and then swap it with head node. But we'd need to do that for each node, which would be O(N^2).
    
    // // Step 1: determine N
    // currentNode = headOfList;
    // let n = 0;
    // while (currentNode != null) {
    //     currentNode = currentNode.next;
    //     n++;
    // }
    // // Step 2: reverse by swapping
    // for (let i = 0; i < n / 2; i++) {
    //     swap(traverseN(headOfList, i), traverseN(headOfList, n - i - 1))
    // }

    // the above approach only swaps values, not reversing the actual nodes. the site also hinted at an O(N) solution, so now I'm thinking greedy.
    // we can do it by maintaining a reference to the previous one, and then pointing our current node backward to the previous node.
    let currentNode = headOfList;
    let previousNode = null;
    while (currentNode !== null) {
        const next = currentNode.next;
        currentNode.next = previousNode;
        previousNode = currentNode;
        currentNode = next;
    }
    return previousNode;
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'short linked list';
  let nodes = valuesToLinkedListNodes([1, 2]);
  let reversedList = reverse(nodes[0]);
  assertEquals(isListReversed(reversedList, nodes), true, desc);
  
  desc = 'long linked list';
  nodes = valuesToLinkedListNodes([1, 2, 3, 4, 5, 6]);
  reversedList = reverse(nodes[0]);
  assertEquals(isListReversed(reversedList, nodes), true, desc);
  
  desc = 'one element linked list';
  const node = new LinkedListNode(1);
  reversedList = reverse(node);
  assertEquals(node.value === reversedList.value && node.next === reversedList.next, true, desc);
  
  desc = 'empty linked list';
  reversedList = reverse(null);
  assertEquals(reversedList, null, desc);
  
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
  
  function isListReversed(list, originalNodes) {
    let i = originalNodes.length - 1;
    while (list != null && i >= 0) {
      if (originalNodes[i] != list) {
        return false;
      }
      list = list.next;
      i--;
    }
    return list == null;
  }
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }