package cake.section05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeBoundary {
    // Note: Educative course and Leetcode had different definitions of this problem.
    // Educative was left view + leaves + right view, which the below implements.
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // BFS level-order traversal. O(N) time
        List<Integer> leftView = new ArrayList<>();
        LinkedList<Integer> rightView = new LinkedList<>();
        List<Integer> leaves = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (i == 0) {
                    leftView.add(current.val);
                } else if (current.left == null && current.right == null) {
                    leaves.add(current.val);
                } else if (i == levelSize - 1) {
                    rightView.addFirst(current.val);
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        // merge lists into result
        result.addAll(leftView);
        result.addAll(leaves);
        result.addAll(rightView);
        return result;
    }

    // Leetcode defines tree boundary as left boundary + leaves + right boundary
    // They attempt to explain what they mean by left boundary, but their explanation makes no sense.
    // I tried figuring out how to do this with level-order, but it doesn't seem to work.
    // Instead, I'd just do it with DFS.
    private void boundaryOfBindaryTreeRecurse(TreeNode pre, TreeNode root, boolean leftMost, boolean rightMost, List<Integer> result) {
        // Found leaf node or leftmost, so add.
        boolean alreadyAdded = false;
        if ((root.left == null && root.right == null) || leftMost) {
            result.add(root.val);
            alreadyAdded = true;
        }
        // Go searching for leftmost node
        if (root.left != null) {
            boundaryOfBindaryTreeRecurse(root, root.left, leftMost, (pre != null && root.right == null) ? rightMost : false, result);
        }
        if (root.right != null) {
            boundaryOfBindaryTreeRecurse(root, root.right, (pre != null && root.left == null) ? leftMost : false, rightMost, result);
        }
        if (rightMost && !alreadyAdded) {
            result.add(root.val);
        }

    }
    public List<Integer> boundaryOfBinaryTree2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boundaryOfBindaryTreeRecurse(null, root, true, true, result);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(7);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(6);
        List<Integer> result = new TreeBoundary().boundaryOfBinaryTree2(root);
        for (Integer val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        result = new TreeBoundary().boundaryOfBinaryTree2(root);
        for (Integer val : result) {
            System.out.print(val + " ");
        }
    }
}