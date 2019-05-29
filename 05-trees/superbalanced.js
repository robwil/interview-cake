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

function recurseDFS(treeRoot, depth, globals) {
    if (treeRoot === null) {
        return;
    }
    if (treeRoot.left === null && treeRoot.right === null) {
        // found leaf node, so compare its depth with global min/max
        if (depth < globals.minLeafDepth) {
            globals.minLeafDepth = depth;
        }
        if (depth > globals.maxLeafDepth) {
            globals.maxLeafDepth = depth;
        }
    } else {
        recurseDFS(treeRoot.left, depth + 1, globals);
        recurseDFS(treeRoot.right, depth + 1, globals);
    }
}

function isBalanced(treeRoot) {

    // Determine if the tree is superbalanced.
    // A tree is "superbalanced" if the difference between the depths of any two leaf nodes ↴ is no greater than one.

    // RW: Can we do better than O(N)? No, because we must visit every node, and since the tree coudl be very unbalanced, we can't skip to bottom level or anything.
    // So I think we just do a DFS, keeping track of our depth as we go. We can then keep track of a global min and global max of depth on any leaf nodes found, comparing them at the end.

    // Originally, I did this as recursive DFS, but then decided to do iterative.
    // let globals = {minLeafDepth: Infinity, maxLeafDepth: -Infinity};
    // recurseDFS(treeRoot, 0, globals);
    // return globals.maxLeafDepth - globals.minLeafDepth <= 1;

    if (treeRoot === null) { // if tree is empty, I guess it is superbalanced
        return true;
    }

    let stack = [{node: treeRoot, depth: 0}];
    let maxLeafDepth = -Infinity;
    let minLeafDepth = Infinity;
    while (stack.length > 0) {
        const item = stack.pop();
        if (item.node === null) {
            continue;
        }
        if (item.node.left === null && item.node.right === null) {
            // found leaf node, so compare its depth with global min/max
            if (item.depth < minLeafDepth) {
                minLeafDepth = item.depth;
            }
            if (item.depth > maxLeafDepth) {
                maxLeafDepth = item.depth;
            }
        } else {
            stack.push({node: item.node.left, depth: item.depth + 1});
            stack.push({node: item.node.right, depth: item.depth + 1});
        }
    }

    return maxLeafDepth - minLeafDepth <= 1;
}

// Note: After comparing my solution with the site, I think mine is simpler to understand but theirs is better because it short-circuits early.

  // Tests

  let desc = 'full tree';
  let treeRoot = new BinaryTreeNode(5);
  let leftNode = treeRoot.insertLeft(8);
  leftNode.insertLeft(1);
  leftNode.insertRight(2);
  let rightNode = treeRoot.insertRight(6);
  rightNode.insertLeft(3);
  rightNode.insertRight(4);
  assertEquals(isBalanced(treeRoot), true, desc);

  desc = 'both leaves at the same depth';
  treeRoot = new BinaryTreeNode(3);
  leftNode = treeRoot.insertLeft(4);
  leftNode.insertLeft(1);
  rightNode = treeRoot.insertRight(6);
  rightNode.insertRight(9);
  assertEquals(isBalanced(treeRoot), true, desc);

  desc = 'leaf heights differ by one';
  treeRoot = new BinaryTreeNode(6);
  leftNode = treeRoot.insertLeft(1);
  rightNode = treeRoot.insertRight(0);
  rightNode.insertRight(7);
  assertEquals(isBalanced(treeRoot), true, desc);

  desc = 'leaf heights differ by two';
  treeRoot = new BinaryTreeNode(6);
  leftNode = treeRoot.insertLeft(1);
  rightNode = treeRoot.insertRight(0);
  rightNode.insertRight(7).insertRight(8);
  assertEquals(isBalanced(treeRoot), false, desc);

  desc = 'three leaves total';
  treeRoot = new BinaryTreeNode(1);
  leftNode = treeRoot.insertLeft(5);
  rightNode = treeRoot.insertRight(9);
  rightNode.insertLeft(8);
  rightNode.insertRight(5);
  assertEquals(isBalanced(treeRoot), true, desc);

  desc = 'both subtrees superbalanced';
  treeRoot = new BinaryTreeNode(1);
  leftNode = treeRoot.insertLeft(5);
  rightNode = treeRoot.insertRight(9);
  rightNode.insertLeft(8).insertLeft(7);
  rightNode.insertRight(5);
  assertEquals(isBalanced(treeRoot), false, desc);

  desc = 'both subtrees superbalanced two';
  treeRoot = new BinaryTreeNode(1);
  leftNode = treeRoot.insertLeft(2);
  leftNode.insertLeft(3);
  leftNode.insertRight(7).insertRight(8);
  treeRoot.insertRight(4).insertRight(5).insertRight(6).insertRight(9);
  assertEquals(isBalanced(treeRoot), false, desc);

  desc = 'only one node';
  treeRoot = new BinaryTreeNode(1);
  assertEquals(isBalanced(treeRoot), true, desc);

  desc = 'linked list tree';
  treeRoot = new BinaryTreeNode(1);
  treeRoot.insertRight(2).insertRight(3).insertRight(4);
  assertEquals(isBalanced(treeRoot), true, desc);

  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`)
    }
  }