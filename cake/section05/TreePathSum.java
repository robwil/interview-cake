package cake.section05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


class TreePathSum {
    /**
     * Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ‘S’.
     */
    public static boolean hasPath(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        boolean result1 = false, result2 = false;
        int targetSum = sum - root.val;
        if (root.left != null) {
            result1 = hasPath(root.left, targetSum);
        }
        if (root.right != null) {
            result2 = hasPath(root.right, targetSum);
        }
        return result1 || result2;
    }

    /**
     * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
     */
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        findPaths(root, sum, new LinkedList<>(), allPaths);

        return allPaths;
    }

    private static void findPaths(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (root == null) {
            return;
        }

        // Base case: leaf node, check sum
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                currentPath.add(root.val);
                allPaths.add(currentPath);
                return;
            }
        }

        // Recursion
        List<Integer> newPath = new LinkedList<>(currentPath);
        newPath.add(root.val);
        int newSum = sum - root.val;
        if (root.left != null) {
            findPaths(root.left, newSum, newPath, allPaths);
        }
        if (root.right != null) {
            findPaths(root.right, newSum, newPath, allPaths);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 23));

        root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = TreePathSum.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);

    }
}
