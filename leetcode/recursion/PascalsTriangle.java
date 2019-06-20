package leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/1659/
 * Input: 5
 * Output:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> output = new ArrayList<>(numRows);
        if (numRows <= 0) {
            return output;
        }
        generateRow(output, 1, numRows);
        return output;
    }

    private void generateRow(List<List<Integer>> currentTriangle, int currentRow, int totalRows) {
        if (currentRow == 1) {
            currentTriangle.add(Collections.singletonList(1));
        } else if (currentRow == 2) {
            currentTriangle.add(Arrays.asList(1, 1));
        } else {
            List<Integer> row = new ArrayList<>(currentRow);
            for (int i = 0; i < currentRow; i++) {
                if (i == 0 || i == currentRow - 1) {
                    row.add(1);
                } else {
                    row.add(currentTriangle.get(currentRow - 1 - 1).get(i - 1) + currentTriangle.get(currentRow - 1 - 1).get(i));
                }
            }
            currentTriangle.add(row);
        }
        if (currentRow < totalRows) {
            generateRow(currentTriangle, currentRow + 1, totalRows);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangle().generate(5));
    }
}
