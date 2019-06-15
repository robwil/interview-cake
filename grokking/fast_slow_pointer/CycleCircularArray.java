package grokking.fast_slow_pointer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CycleCircularArray {
    public boolean circularArrayLoop(int[] nums) {
        // use fast pointer and slow pointer, starting at each location.
        // but we know that any location checked for cycle can't be part of a cycle, so don't need to check them again
        Set<Integer> alreadyChecked = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (alreadyChecked.contains(i)) {
                continue;
            }
            int fast = i;
            int slow = i;
            List<Integer> checked = new LinkedList<>();
            checked.add(i);
            boolean goingForward = nums[i] > 0;
            do {
                slow = findNextIndex(nums, slow, goingForward);
                checked.add(slow);
                if (alreadyChecked.contains(slow)) {
                    slow = -1;
                    break;
                }
                fast = findNextIndex(nums, fast, goingForward);
                if (alreadyChecked.contains(fast)) {
                    fast = -1;
                    break;
                }
                if (fast != -1) {
                    fast = findNextIndex(nums, fast, goingForward);
                }
            } while (fast != -1 && slow != -1 && slow != fast);
            if (slow != -1 && slow == fast) {
                return true; // found cycle
            }
            // haven't found cycle yet, but we can exclude all previously checked indices
            alreadyChecked.addAll(checked);
        }
        return false;
    }

    private int findNextIndex(int[] nums, int currentIndex, boolean goingForward) {
        int nextIndex = (currentIndex + nums[currentIndex]) % nums.length;
        if (nextIndex < 0) {
            nextIndex += nums.length;
        }
        if ((nums[nextIndex] > 0) != goingForward) { // changing direction is not a cycle
            return -1;
        }
        if (nextIndex == currentIndex) { // 1 element cycle, not considered cycle
            return -1;
        }
        return nextIndex;
    }

    public static void main(String[] args) {
        System.out.println(new CycleCircularArray().circularArrayLoop(new int[]{1, 2, -1, 2, 2}));
        System.out.println(new CycleCircularArray().circularArrayLoop(new int[]{2, 2, -1, 2}));
        System.out.println(new CycleCircularArray().circularArrayLoop(new int[]{2, 1, -1, -2}));
    }

}
