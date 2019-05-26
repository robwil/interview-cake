function getProductsOfAllIntsExceptAtIndex(arr) {
    const N = arr.length;
    if (N < 2) {
        throw new Error('must have at least 2 elements');
    }
    let answer = [];
    // // Naive approach is O(N^2)
    // for (let i = 0; i < N; i++) {
    //     for (let j = 0; j < N; j++) {
    //         if (i == j) {
    //             continue; // don't multiply self
    //         }
    //         if (answer[j] === undefined) {
    //             answer[j] = 1; // initialize to 1
    //         }
    //         answer[j] *= arr[i];
    //     }
    // }

    // How to do better? We can keep a running product of the whole thing, and then set that at the end. Problem is we can't use division to "fix" each answer on the way out.
    // So how do we get around that?

    // Greedy approach, let's go through and calculate all products _before_ each index, and all the products _after_ each index (requires two passes going in diff directions).
    // But as we do this, we can multiple those together in the answer array.
    let product = 1;
    for (let i = 0; i < N; i++) {
        if (answer[i] === undefined) {
            answer[i] = 1; // initialize to 1
        }
        answer[i] *= product; // multiply first so we don't include arr[i] in the product
        product *= arr[i];
    }
    product = 1;
    for (let i = N - 1; i >= 0; i--) {
        answer[i] *= product; // multiply first so we don't include arr[i] in the product
        product *= arr[i];
    }
    return answer;
  }
  
  // Tests
  
  let desc = 'short array';
  let actual = getProductsOfAllIntsExceptAtIndex([1, 2, 3]);
  let expected = [6, 3, 2];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'longer array',
  actual = getProductsOfAllIntsExceptAtIndex([8, 2, 4, 3, 1, 5]);
  expected = [120, 480, 240, 320, 960, 192];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'array has one zero',
  actual = getProductsOfAllIntsExceptAtIndex([6, 2, 0, 3]);
  expected = [0, 0, 36, 0];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'array has two zeros';
  actual = getProductsOfAllIntsExceptAtIndex([4, 0, 9, 1, 0]);
  expected = [0, 0, 0, 0, 0];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'one negative number';
  actual = getProductsOfAllIntsExceptAtIndex([-3, 8, 4]);
  expected = [32, -12, -24];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'all negative numbers';
  actual = getProductsOfAllIntsExceptAtIndex([-7, -1, -4, -2]);
  expected = [-8, -56, -14, -28];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'error with empty array';
  const emptyArray = () => (getProductsOfAllIntsExceptAtIndex([]));
  assertThrowsError(emptyArray, desc);
  
  desc = 'error with one number';
  const oneNumber = () => (getProductsOfAllIntsExceptAtIndex([1]));
  assertThrowsError(oneNumber, desc);
  
  function assertArrayEquals(a, b, desc) {
    const arrayA = JSON.stringify(a);
    const arrayB = JSON.stringify(b);
    if (arrayA !== arrayB) {
      console.log(`${desc} ... FAIL: ${arrayA} != ${arrayB}`)
    } else {
      console.log(`${desc} ... PASS`);
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