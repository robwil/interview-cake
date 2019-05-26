function findRepeat(numbers) {

    // Find a number that appears more than once
    
    // naive solution to get O(1) space is to do O(N^2)
    // for i ; for j; if arr[i]==arr[j] && i != j return arr[i];

    // can we do better?
    // There's no way to do O(N) time without O(N) space, since we'd need to store arbitrary amount of numbers.
    
    // since we know some number appears at least twice, we can take half the array and compare it to half of the remaining?
    // That doesn't work. Eventually, I gave up and the answer is something I never would have gotten; it exploits a property of the solution that wasn't clear from the example test cases given :(
    
    return 0;
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