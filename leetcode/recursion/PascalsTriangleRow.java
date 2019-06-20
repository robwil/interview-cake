package leetcode.recursion;

/**
 * /**
 * * https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/1660/
 * * Input: 5
 * * Output:
 * * [1,4,6,4,1]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangleRow {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        } else if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        }
        List<Integer> row = new ArrayList<>();
        List<Integer> prevRow = getRow(rowIndex - 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            if (i == 0 || i == rowIndex) {
                row.add(1);
            } else {
                row.add(prevRow.get(i - 1) + prevRow.get(i));
            }
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangleRow().getRow(4));
    }

}
