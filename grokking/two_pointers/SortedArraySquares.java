package grokking.two_pointers;

class SortedArraySquares {

    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];

        // first need to find first positive number
        int i = 0;
        for (; i < arr.length && arr[i] < 0; i++);
        int j = i - 1;
        for (int k = 0; k < squares.length; k++) {
            if (j >= 0 && (i >= squares.length || Math.abs(arr[j]) < arr[i])) {
                squares[k] = arr[j] * arr[j];
                j--;
            } else {
                squares[k] = arr[i] * arr[i];
                i++;
            }
        }
        return squares;
    }

    public static void main(String[] args) {

        int[] result = SortedArraySquares.makeSquares(new int[]{-2, -1, 0, 2, 3});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = SortedArraySquares.makeSquares(new int[]{-3, -1, 0, 1, 2});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }
}