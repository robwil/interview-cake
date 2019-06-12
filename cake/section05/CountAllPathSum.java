package cake.section05;

class CountAllPathSum {
    public static int countPathsRecurse(TreeNode root, int S, int originalSum) {
        if (root == null) {
            return 0;
        }
        int totalPaths = S == root.val ? 1 : 0;
        if (root.left != null) { // TODO: refactor to not need two calls.
            if (S - root.val > 0) {
                totalPaths += countPaths(root.left, S - root.val);
            }
            totalPaths += countPaths(root.left, originalSum);
        }
        if (root.right != null) {
            if (S - root.val > 0) {
                totalPaths += countPaths(root.right, S - root.val);
            }
            totalPaths += countPaths(root.right, originalSum);
        }
        return totalPaths;
    }
    public static int countPaths(TreeNode root, int S) {
        if (root == null) {
            return 0;
        }
        return countPathsRecurse(root, S, S);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));

        root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 12));

        root = new TreeNode("[10,5,-3,3,2,null,11,3,-2,null,1]");
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 8));
    }
}