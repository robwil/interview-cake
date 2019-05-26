function highestProductOf3(arr) {
    if (arr.length < 3) {
        throw new Error("must have 3 elements");
    }
    // Calculate the highest product of three numbers
    
    // can't just take max 3 items because of potential for two large negative values that cancel each other's negative
    // but we could find top 3 max and top 2 min and go from there
    let max1 = -Infinity, max2 = -Infinity, max3 = -Infinity;
    let min1 = Infinity, min2 = Infinity;

    const N = arr.length;
    for (let i = 0; i < N; i++) {
        if (arr[i] > max1) {
            max3 = max2;
            max2 = max1;
            max1 = arr[i];
        } else if (arr[i] > max2) {
            max3 = max2;
            max2 = arr[i];
        } else if (arr[i] > max3) {
            max3 = arr[i];
        }
        if (arr[i] < min1) {
            min2 = min1;
            min1 = arr[i];
        } else if (arr[i] < min2) {
            min2 = arr[i];
        }
    }

    // case where two mins are negative numbers with greater product than two maxes
    // but only do this if maxes are non-negative (otherwise we'll wind up with wrong answer)
    if (min1 * min2 > max1 * max2 && max1 > 0) {
        return min1*min2*max1;
    }
    return max1*max2*max3;
}


// Bonus:
// What if we wanted the highest product of 4 items?
//   -- could do it the same way, just with 4 constants
// What if we wanted the highest product of k items?
//   -- would use 2 heaps to keep track of min/max, then at the end could walk heap to get k min and k max, using same logic as above.
// If our highest product is really big, it could overflow. ↴ How should we protect against this?
//   -- store it in a string or use Big Number libraries?
  
  
  // Tests
  
  let desc = 'short array';
  let actual = highestProductOf3([1, 2, 3, 4]);
  let expected = 24;
  assertEqual(actual, expected, desc);
  
  desc = 'longer array';
  actual = highestProductOf3([6, 1, 3, 5, 7, 8, 2]);
  expected = 336;
  assertEqual(actual, expected, desc);
  
  desc = 'array has one negative';
  actual = highestProductOf3([-5, 4, 8, 2, 3]);
  expected = 96;
  assertEqual(actual, expected, desc);
  
  desc = 'array has two negatives';
  actual = highestProductOf3([-10, 1, 3, 2, -10]);
  expected = 300;
  assertEqual(actual, expected, desc);
  
  desc = 'array is all negatives';
  actual = highestProductOf3([-5, -1, -3, -2]);
  expected = -6;
  assertEqual(actual, expected, desc);
  
  desc = 'error with empty array';
  const emptyArray = () => (highestProductOf3([]));
  assertThrowsError(emptyArray, desc);
  
  desc = 'error with one number';
  const oneNumber = () => (highestProductOf3([1]));
  assertThrowsError(oneNumber, desc);
  
  desc = 'error with two numbers';
  const twoNumber = () => (highestProductOf3([1, 1]));
  assertThrowsError(twoNumber, desc);
  
  function assertEqual(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`)
    }
  }
  
  function assertThrowsError(func, desc) {
    try {
      func();
      console.log(`${desc} ... FAIL`);
    } catch (e) {
      console.log(`${desc} ... PASS`);
    }
  }