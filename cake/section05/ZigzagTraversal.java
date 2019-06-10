package cake.section05;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//
//        For example:
//        Given binary tree [3,9,20,null,null,15,7],
//          3
//         / \
//        9   20
//           /  \
//          15   7
//        return its zigzag level order traversal as:
//        [
//        [3],
//        [20,9],
//        [15,7]
//        ]

class ZigzagTraversal {
    private class QueueItem {
        private TreeNode node;
        private int level;

        public QueueItem(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }

        public TreeNode getNode() {
            return node;
        }

        public int getLevel() {
            return level;
        }
    }

    // Basic approach here is to do a BFS, but reverse the direction at each level.
    // Will first implement a level order traversal and then see how we might reverse without extra space.
    // This is  O(N) time and O(N+W) space where W is width of widest level.
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> output = new LinkedList<>();
        if (root == null) {
            // short-circuit to handle empty tree case
            return output;
        }
        List<Integer> currentLevelOutput = new LinkedList<>();
        int currentLevel = 1;
        Queue<QueueItem> queue = new LinkedList<>();
        queue.offer(new QueueItem(root, 1));
        while (!queue.isEmpty()) {
            QueueItem item = queue.remove();
            if (item.getLevel() != currentLevel) {
                output.add(currentLevelOutput);
                currentLevel++;
                currentLevelOutput = new LinkedList<>();
            }
            currentLevelOutput.add(item.getNode().val);
            if (item.getNode().left != null) {
                queue.offer(new QueueItem(item.getNode().left, item.getLevel()+1));
            }
            if (item.getNode().right != null) {
                queue.offer(new QueueItem(item.getNode().right, item.getLevel()+1));
            }
        }
        if (!currentLevelOutput.isEmpty()) {
            output.add(currentLevelOutput);
        }
        return output;
    }

    // Same implementation as levelOrder but we prepend instead of append currentLevels
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> output = new LinkedList<>();
        if (root == null) {
            // short-circuit to handle empty tree case
            return output;
        }
        List<Integer> currentLevelOutput = new LinkedList<>();
        int currentLevel = 1;
        Queue<QueueItem> queue = new LinkedList<>();
        queue.offer(new QueueItem(root, 1));
        while (!queue.isEmpty()) {
            QueueItem item = queue.remove();
            if (item.getLevel() != currentLevel) {
                output.addFirst(currentLevelOutput);
                currentLevel++;
                currentLevelOutput = new LinkedList<>();
            }
            currentLevelOutput.add(item.getNode().val);
            if (item.getNode().left != null) {
                queue.offer(new QueueItem(item.getNode().left, item.getLevel()+1));
            }
            if (item.getNode().right != null) {
                queue.offer(new QueueItem(item.getNode().right, item.getLevel()+1));
            }
        }
        if (!currentLevelOutput.isEmpty()) {
            output.addFirst(currentLevelOutput);
        }
        return output;
    }

    // One approach is that we can reverse every other list after we create it, which will still
    // lead to an overall time complexity of O(N). But cleaner approach of doing it in one pass
    // will be to add items to currentLevelOutput with either append or prepend depending on level.
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new LinkedList<>();
        if (root == null) {
            // short-circuit to handle empty tree case
            return output;
        }
        LinkedList<Integer> currentLevelOutput = new LinkedList<>();
        int currentLevel = 1;
        Queue<QueueItem> queue = new LinkedList<>();
        queue.offer(new QueueItem(root, 1));
        while (!queue.isEmpty()) {
            QueueItem item = queue.remove();
            if (item.getLevel() != currentLevel) {
                output.add(currentLevelOutput);
                currentLevel++;
                currentLevelOutput = new LinkedList<>();
            }
            if (currentLevel % 2 == 0) {
                // even levels are the ones that get reverse order, so prepend instead of append
                currentLevelOutput.addFirst(item.getNode().val);
            } else {
                currentLevelOutput.add(item.getNode().val);
            }
            if (item.getNode().left != null) {
                queue.offer(new QueueItem(item.getNode().left, item.getLevel()+1));
            }
            if (item.getNode().right != null) {
                queue.offer(new QueueItem(item.getNode().right, item.getLevel()+1));
            }
        }
        if (!currentLevelOutput.isEmpty()) {
            output.add(currentLevelOutput);
        }
        return output;
    }

    public static void main(String[] arg) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = new ZigzagTraversal().levelOrder(root);
        System.out.println("Level order traversal: " + result);
        result = new ZigzagTraversal().zigzagLevelOrder(root);
        System.out.println("Zigzag order traversal: " + result);

    }
}