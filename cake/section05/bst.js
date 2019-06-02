class BinaryTreeNode {
    constructor(value) {
      this.value = value;
      this.left  = null;
      this.right = null;
    }

    insertLeft(value) {
      this.left = new BinaryTreeNode(value);
      return this.left;
    }

    insertRight(value) {
      this.right = new BinaryTreeNode(value);
      return this.right;
    }
  }

function recurseInorder(currentNode, globals) {
    if (currentNode === null) {
        return true;
    }
    // First go left
    const result1 = recurseInorder(currentNode.left, globals);
    if (!result1) {
        return false;
    }
    // Actually visit current node, comparing against lastVisitedNode.
    if (globals.lastVisitedNode != null && currentNode.value < globals.lastVisitedNode.value) {
        return false;
    }
    globals.lastVisitedNode = currentNode;
    // Last go right
    const result2 = recurseInorder(currentNode.right, globals);
    if (!result2) {
        return false;
    }
    // Finally can return true if it passed all.
    return true;
}

  function isBinarySearchTree(treeRoot) {

    // Determine if the tree is a valid binary search tree

    // Many ways to do this.

    // One way is to do an in-order traversal, saving to an array. Then walk the array, stopping as soon as a value is less than previous value.
    // But that uses O(N) extra space for the array.

    // Another way to do this in O(N) time is to do an in-order traversal of all nodes, stopping as soon as currentNode < previousNode. More book-keeping, but it's O(1) extra space instead of O(N) for array.
    // We can do in-order traversal easily as recursive, so I'll implement that first.
    // return recurseInorder(treeRoot, {lastVisitedNode: null});

    // But let's do it iteratively to prevent stack overflow for big trees.
    let lastVisitedNode = null;
    let stack = [];
    let currentNode = treeRoot;
    while (stack.length > 0 || currentNode !== null) {
        while (currentNode !== null) {
            stack.push(currentNode);
            currentNode = currentNode.left;
        }
        currentNode = stack.pop();
        if (lastVisitedNode !== null && currentNode.value < lastVisitedNode.value) {
            return false;
        }
        lastVisitedNode = currentNode;
        currentNode = currentNode.right;
    }
    return true;
}


  // Tests

  let desc = 'valid full tree';
  let treeRoot = new BinaryTreeNode(50);
  let leftNode = treeRoot.insertLeft(30);
  leftNode.insertLeft(10);
  leftNode.insertRight(40);
  let rightNode = treeRoot.insertRight(70);
  rightNode.insertLeft(60);
  rightNode.insertRight(80);
  assertEquals(isBinarySearchTree(treeRoot), true, desc);

  desc = 'both subtrees valid';
  treeRoot = new BinaryTreeNode(50);
  leftNode = treeRoot.insertLeft(30);
  leftNode.insertLeft(20);
  leftNode.insertRight(60);
  rightNode = treeRoot.insertRight(80);
  rightNode.insertLeft(70);
  rightNode.insertRight(90);
  assertEquals(isBinarySearchTree(treeRoot), false, desc);

  desc = 'descending linked list';
  treeRoot = new BinaryTreeNode(50);
  leftNode = treeRoot.insertLeft(40);
  leftNode = leftNode.insertLeft(30);
  leftNode = leftNode.insertLeft(20);
  leftNode = leftNode.insertLeft(10);
  assertEquals(isBinarySearchTree(treeRoot), true, desc);

  desc = 'out of order linked list';
  treeRoot = new BinaryTreeNode(50);
  rightNode = treeRoot.insertRight(70);
  rightNode = rightNode.insertRight(60);
  rightNode = rightNode.insertRight(80);
  assertEquals(isBinarySearchTree(treeRoot), false, desc);

  desc = 'one node tree';
  treeRoot = new BinaryTreeNode(50);
  assertEquals(isBinarySearchTree(treeRoot), true, desc);

  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`)
    }
  }