package grokking.two_pointers;

import java.util.Arrays;

class RemoveDuplicates {

    public static int remove(int[] arr) {
        int nextNonDuplicate = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr[nextNonDuplicate - 1]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }
        return nextNonDuplicate;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 3, 3, 6, 9, 9};
        int newLen = RemoveDuplicates.remove(arr);
        System.out.println(newLen);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, newLen)));

        arr = new int[]{2, 2, 2, 11};
        newLen = RemoveDuplicates.remove(arr);
        System.out.println(newLen);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, newLen)));
    }
}