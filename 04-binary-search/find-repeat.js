function findRepeat(arr) {

    // Find a number that appears more than once
    
    // naive solution to get O(1) space is to do O(N^2)
    // for i ; for j; if arr[i]==arr[j] && i != j return arr[i];

    // can we do better?
    // There's no way to do O(N) time without O(N) space, since we'd need to store arbitrary amount of numbers.
    
    // since we know some number appears at least twice, we can take half the array and compare it to half of the remaining?
    // That doesn't work. Eventually, I gave up and the answer is something I never would have gotten; it exploits a property of the solution that wasn't clear from the example test cases given :(

    // And then they talk about a way to do it with O(N) space that is totally insane/beautiful, and I followed the description well enough to want to implement it.

    // We know the position of a node with multiple incoming pointers is a duplicate in our array because the nodes that pointed to it must have the same value.
    // We find a node with multiple incoming pointers by finding the first node in a cycle.
    // We find the first node in a cycle by finding the length of the cycle and advancing two pointers: one starting at the head of the linked list, and the other starting ahead as many steps as there are steps in the cycle. The pointers will meet at the first node in the cycle.
    // We find the length of a cycle by remembering a position inside the cycle and counting the number of steps it takes to get back to that position.
    // We get inside a cycle by starting at the head and walking nn steps. We know the head of the list is at position n + 1n+1.

    // Step 1: Get inside the cycle by walking N steps
    const N = arr.length;
    const headIndex = N - 1;
    let currentIndex = headIndex;
    for (let i = 0; i < N; i++) {
        currentIndex = arr[currentIndex] - 1;
    }

    // Step 2: determine the length of the cycle, by walking linked list until we get back to original index
    let originalIndex = currentIndex;
    currentIndex = arr[originalIndex] - 1;
    let cycleLength = 1;
    while (currentIndex != originalIndex) {
        currentIndex = arr[currentIndex] - 1;
        cycleLength++;
    }

    // Step 3: Find cycle by starting at head and using "stick" approach
    let otherIndex = headIndex;
    for (let i = 0; i < cycleLength; i++) {
        otherIndex = arr[otherIndex] - 1;
    }
    currentIndex = headIndex;
    while (currentIndex != otherIndex) {
        currentIndex = arr[currentIndex] - 1;
        otherIndex = arr[otherIndex] - 1;
    }
    
    return currentIndex + 1;
}

  
  
  
  // Tests
  
  let desc = 'just the repeated number';
  let actual = findRepeat([1, 1]);
  let expected = 1;
  assertEqual(actual, expected, desc);
  
  desc = 'short array';
  actual = findRepeat([1, 2, 3, 2]);
  expected = 2;
  assertEqual(actual, expected, desc);
  
  desc = 'medium array';
  actual = findRepeat([1, 2, 5, 5, 5, 5]);
  expected = 5;
  assertEqual(actual, expected, desc);

  desc = 'more interesting medium array';
  actual = findRepeat([1, 2, 5, 5, 5, 3]);
  expected = 5;
  assertEqual(actual, expected, desc);
  
  desc = 'long array';
  actual = findRepeat([4, 1, 4, 8, 3, 2, 7, 6, 5]);
  expected = 4;
  assertEqual(actual, expected, desc);
  
  function assertEqual(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }