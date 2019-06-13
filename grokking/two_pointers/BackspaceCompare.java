package grokking.two_pointers;

class BackspaceCompare {

    private static int handleBackspace(String str, int i) {
        int backspaceCount = 0;
        while (i >= 0 && (str.charAt(i) == '#' || backspaceCount > 0)) {
            if (str.charAt(i) == '#') {
                backspaceCount++;
            } else {
                backspaceCount--;
            }
            i--;
        }
        return i;
    }

    public static boolean compare(String str1, String str2) {
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        while (i >= 0 && j >= 0) {
            i = handleBackspace(str1, i);
            j = handleBackspace(str2, j);
            if (i < 0 || j < 0) {
                break;
            }
            if (str1.charAt(i) != str2.charAt(j)) {
                return false;
            }
            i--;
            j--;
        }
        // at this point, one string has been reduced to empty string.
        // but there might be leftovers in other string, so let's so one final handling of backspaces
        i = handleBackspace(str1, i);
        j = handleBackspace(str2, j);
        return i == j;
    }

    public static void main(String[] args) {
        System.out.println("expected true = " + BackspaceCompare.compare("xy#z", "xzz#"));
        // true: After applying backspaces the strings become "xz" and "xz" respectively.
        System.out.println("expected false = " + BackspaceCompare.compare("xy#z", "xyz#"));
        // false: After applying backspaces the strings become "xz" and "xy" respectively.
        System.out.println("expected true = " + BackspaceCompare.compare("xp#", "xyz##"));
        System.out.println("expected true = " + BackspaceCompare.compare("xywrrmp", "xywrrmu#p"));
        System.out.println("expected true = " + BackspaceCompare.compare("bxj##tw", "bxo#j##tw"));
        System.out.println("expected true = " + BackspaceCompare.compare("ab##", "c#d#"));
        System.out.println("expected false = " + BackspaceCompare.compare("bxj##", "bxj###"));
        System.out.println("expected true = " + BackspaceCompare.compare("nzp#o#g", "b#nzp#o#g"));
        System.out.println("expected true = " + BackspaceCompare.compare("r", "#r"));
    }
}