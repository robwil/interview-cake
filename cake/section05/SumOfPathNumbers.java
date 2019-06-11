package cake.section05;

class SumOfPathNumbers {
    private class Sum {
        int sum;
    }
    private void findSumOfPathNumbersRecurse(TreeNode root, int currentPath, Sum sum) {
        currentPath = 10 * currentPath + root.val;
        if (root.left == null && root.right == null) {
            // at leaf node, so convert string of path into number and add to sum
            sum.sum += currentPath;
        }
        if (root.left != null) {
            findSumOfPathNumbersRecurse(root.left, currentPath, sum);
        }
        if (root.right != null) {
            findSumOfPathNumbersRecurse(root.right, currentPath, sum);
        }
    }
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Sum sum = new Sum();
        findSumOfPathNumbersRecurse(root, 0, sum);
        return sum.sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + new SumOfPathNumbers().sumNumbers(root));
    }
}
