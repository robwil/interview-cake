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

function recurseFindSecondLargest(treeRoot) {
    if (treeRoot === null) {
        throw new Error("no second largest element in empty tree");
    }
    if (treeRoot.left === null && treeRoot.right === null) {
        throw new Error("no second largest element in single node tree");
    }
    if (treeRoot.right !== null) {
        // either I am second largest (if right tree is only one element), or second largest is in right tree.
        // In this case, we can ignore whole left tree, recurse into right tree, and then compare result with ourselves.
        try {
            const secondLargest = recurseFindSecondLargest(treeRoot.right);
            return secondLargest;
        } catch (e) {
            // right tree must have only one element, so I am second largest.
            return treeRoot.value;
        }
    }
    // Only case left now is if right tree is empty and we have only left tree.
    // In that case, the rightmost item of left tree is the second largest.
    let currentNode = treeRoot.left;
    while (currentNode.right !== null) {
        currentNode = currentNode.right;
    }
    return currentNode.value;
}

function findSecondLargest(treeRoot) {

    // Find the second largest item in the binary search tree.

    // First thought that comes to mind is do an in-order walk, keeping track of previous node. And when we're done we just return previous node.
    // That would be O(N) time with O(N) space for the stack.
    
    // But since this is BST, we can likely do O(lg N) since at each iteration we can ignore the whole left subtree (unless the right subtree is empty?)
    //    4
    //   /    a tree that looks like this will be tough to handle. But that's sort of a special case ... like part of the base case.
    //  2

    // Thinking of doing this recursively first, then we can worry about iterative.
    // The recursive solution winds up being O(lg N) time in worst case, where we need to keep going right in a full BST. Space is O(lg N) also because of call stack.
    // return recurseFindSecondLargest(treeRoot);

    // The site then says we can achieve O(h) -- which is same as O(lg N) for balanced tree... but with O(1) space. That means we need to do it iteratively.
    // I think we can get away with avoiding stack since we don't need DFS or in-order here.

    if (treeRoot === null) {
        throw new Error("no second largest element in empty tree");
    }
    if (treeRoot.left === null && treeRoot.right === null) {
        throw new Error("no second largest element in single node tree");
    }
    let currentNode = treeRoot;
    let alreadyKnowLargest = false;
    while (currentNode !== null) {
        // 1) Case where a right subtree exists
        if (currentNode.right !== null) {
            // either I am second largest (if right tree is only one element), or second largest is in right tree.
            if (currentNode.right.right === null && currentNode.right.left === null && !alreadyKnowLargest) {
                // I am second largest, since right tree is only 1 element.
                return currentNode.value;
            }
            // Otherwise, we can ignore whole left tree, moving into right tree.
            currentNode = currentNode.right;
            continue;
        }
        // 2) Case where only left subtree (or no subtree) exists
        if (alreadyKnowLargest) {
            // If we already know largest, we must be in a left subtree, so this node without a right subtree must be second largest
            return currentNode.value;
        }
        // Only case left now is if right tree is empty and we have only left tree.
        // In that case, the rightmost item of left tree is the second largest.
        currentNode = currentNode.left;
        alreadyKnowLargest = true;
    }
    throw new Error("should not have gotten here");
}
  
  // Tests
  
  let desc = 'full tree';
  let treeRoot = new BinaryTreeNode(50);
  let leftNode = treeRoot.insertLeft(30);
  leftNode.insertLeft(10);
  leftNode.insertRight(40);
  let rightNode = treeRoot.insertRight(70);
  rightNode.insertLeft(60);
  rightNode.insertRight(80);
  assertEquals(findSecondLargest(treeRoot), 70, desc);
  
  desc = 'largest has a left child';
  treeRoot = new BinaryTreeNode(50);
  leftNode = treeRoot.insertLeft(30);
  leftNode.insertLeft(10);
  leftNode.insertRight(40);
  rightNode = treeRoot.insertRight(70);
  rightNode.insertLeft(60);
  assertEquals(findSecondLargest(treeRoot), 60, desc);
  
  desc = 'largest has a left subtree';
  treeRoot = new BinaryTreeNode(50);
  leftNode = treeRoot.insertLeft(30);
  leftNode.insertLeft(10);
  leftNode.insertRight(40);
  rightNode = treeRoot.insertRight(70);
  leftNode = rightNode.insertLeft(60);
  leftNode.insertRight(65);
  leftNode = leftNode.insertLeft(55);
  leftNode.insertRight(58);
  assertEquals(findSecondLargest(treeRoot), 65, desc);
  
  desc = 'second largest is root node';
  treeRoot = new BinaryTreeNode(50);
  leftNode = treeRoot.insertLeft(30);
  leftNode.insertLeft(10);
  leftNode.insertRight(40);
  rightNode = treeRoot.insertRight(70);
  assertEquals(findSecondLargest(treeRoot), 50, desc);
  
  desc = 'descending linked list';
  treeRoot = new BinaryTreeNode(50);
  leftNode = treeRoot.insertLeft(40);
  leftNode = leftNode.insertLeft(30);
  leftNode = leftNode.insertLeft(20);
  leftNode = leftNode.insertLeft(10);
  assertEquals(findSecondLargest(treeRoot), 40, desc);
  
  desc = 'ascending linked list';
  treeRoot = new BinaryTreeNode(50);
  rightNode = treeRoot.insertRight(60);
  rightNode = rightNode.insertRight(70);
  rightNode = rightNode.insertRight(80);
  assertEquals(findSecondLargest(treeRoot), 70, desc);
  
  desc = 'one node tree';
  treeRoot = new BinaryTreeNode(50);
  assertThrowsError(() => findSecondLargest(treeRoot), desc);
  
  desc = 'when tree is empty';
  treeRoot = null;
  assertThrowsError(() => findSecondLargest(treeRoot), desc);
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`)
    }
  }
  
  function assertThrowsError(func, desc) {
    try {
      func();
      console.log(`${desc} ... FAIL`);
    } catch (e) {
      console.log(`${desc} ... PASS`);
    }
  }