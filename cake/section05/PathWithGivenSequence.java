package cake.section05;

import java.util.Arrays;

class PathWithGivenSequence {

    public static boolean findPath(TreeNode root, int[] sequence) {
//        System.out.println("at " + root.val + " looking for " + Arrays.toString(sequence));
        if (root.left == null && root.right == null) {
            return sequence[0] == root.val;
        }
        if (root.val == sequence[0]) {
            int[] subsequence = Arrays.copyOfRange(sequence, 1, sequence.length);
            if (root.left != null && findPath(root.left, subsequence)) {
                return true;
            }
            if (root.right != null && findPath(root.right, subsequence)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[]{1, 0, 7}));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[]{1, 1, 6}));
    }
}
