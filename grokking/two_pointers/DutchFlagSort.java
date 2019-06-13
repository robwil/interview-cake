package grokking.two_pointers;

class DutchFlag {

    public static void sort(int[] arr) {
        // straight forward version
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++;
            }
            if (arr[i] == 1) {
                ones++;
            }
            if (arr[i] == 2) {
                twos++;
            }
        }
        int i = 0;
        while (zeros > 0) {
            arr[i++] = 0;
            zeros--;
        }
        while (ones > 0) {
            arr[i++] = 1;
            ones--;
        }
        while (twos > 0) {
            arr[i++] = 2;
            twos--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void sort2(int[] arr) {
        // two pointer approach
        int zeroSpot = 0;
        int twoSpot = arr.length - 1;

        for (int i = 0; i <= twoSpot; ) {
            if (arr[i] == 2) {
                swap(arr, i, twoSpot);
                twoSpot--;
            } else if (arr[i] == 0) {
                swap(arr, i, zeroSpot);
                zeroSpot++;
                i++;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 2, 1, 0};
        DutchFlag.sort2(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[]{2, 2, 0, 1, 2, 0};
        DutchFlag.sort2(arr);
        for (int num : arr)
            System.out.print(num + " ");
    }
}
