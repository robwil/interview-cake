function mergeArrays(arr1, arr2) {
    let outputArr = [];
    let index1 = 0;
    let index2 = 0;
    while (index1 < arr1.length && index2 < arr2.length) {
        if (arr1[index1] < arr2[index2]) {
            outputArr.push(arr1[index1]);
            index1++;
        } else {
            outputArr.push(arr2[index2]);
            index2++;
        }
    }
    while (index1 < arr1.length) {
        outputArr.push(arr1[index1]);
        index1++;
    }
    while (index2 < arr2.length) {
        outputArr.push(arr2[index2]);
        index2++;
    }
    return outputArr;
}
  
  // Tests
  
  let desc = 'both arrays are empty';
  let actual = mergeArrays([], []);
  let expected = [];
  assertDeepEqual(actual, expected, desc);
  
  desc = 'first array is empty';
  actual = mergeArrays([], [1, 2, 3]);
  expected = [1, 2, 3];
  assertDeepEqual(actual, expected, desc);
  
  desc = 'second array is empty';
  actual = mergeArrays([5, 6, 7], []);
  expected = [5, 6, 7];
  assertDeepEqual(actual, expected, desc);
  
  desc = 'both arrays have some numbers';
  actual = mergeArrays([2, 4, 6], [1, 3, 7]);
  expected = [1, 2, 3, 4, 6, 7];
  assertDeepEqual(actual, expected, desc);
  
  desc = 'arrays are different lengths';
  actual = mergeArrays([2, 4, 6, 8], [1, 7]);
  expected = [1, 2, 4, 6, 7, 8];
  assertDeepEqual(actual, expected, desc);
  
  function assertDeepEqual(a, b, desc) {
    const aStr = JSON.stringify(a);
    const bStr = JSON.stringify(b);
    if (aStr !== bStr) {
      console.log(`${desc} ... FAIL: ${aStr} != ${bStr}`);
    } else {
      console.log(`${desc} ... PASS`);
    }
  }