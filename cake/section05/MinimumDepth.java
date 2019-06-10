package cake.section05;

import java.util.LinkedList;
import java.util.Queue;

class TreeDepth {
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Perform BFS, keeping track of depth as we go using levelOrder traversal trick.
        // Can short-circuit and stop as soon as we find leaf node
        // O(N) runtime - worst case is leaf at bottom level
        // O(N) space for queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentLevel = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
                if (current.left == null && current.right == null) {
                    // found first leaf node
                    return currentLevel;
                }
            }
            currentLevel++;
        }
        // should never get here
        return -1;
    }

    // maxDepth is the exact same thing, except we actually maintain a max instead of short-circuiting
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Perform BFS, keeping track of depth as we go using levelOrder traversal trick.
        // Can short-circuit and stop as soon as we find leaf node
        // O(N) runtime - worst case is leaf at bottom level
        // O(N) space for queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentLevel = 1;
        int maxLevel = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
                if (current.left == null && current.right == null && currentLevel > maxLevel) {
                    // found new max leaf node
                    maxLevel = currentLevel;
                }
            }
            currentLevel++;
        }
        return maxLevel;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree Minimum Depth: " + TreeDepth.minDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + new TreeDepth().maxDepth(root));
    }
}