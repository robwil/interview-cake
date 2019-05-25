function reverse(arrayOfChars) {
    var N = arrayOfChars.length;
    for (var i = 0; i < N/2; i++) {
        var tmp = arrayOfChars[i];
        arrayOfChars[i] = arrayOfChars[N - i - 1];
        arrayOfChars[N - i - 1] = tmp;
    }
}

// A B C
// 0, 3-0-1(2)
// 1, 3-1-1(1) won't even run because N/2 (3/2) = 1, and 1 not < 1

// A B C D
// 0, 4-0-1(3)
// 1, 4-1-1(2)

  
  // Tests
  
  let desc = 'empty string';
  let input = ''.split('');
  reverse(input);
  let actual = input.join('');
  let expected = '';
  assertEqual(actual, expected, desc);
  
  desc = 'single character string';
  input = 'A'.split('');
  reverse(input);
  actual = input.join('');
  expected = 'A';
  assertEqual(actual, expected, desc);
  
  desc = 'longer string';
  input = 'ABCDE'.split('');
  reverse(input);
  actual = input.join('');
  expected = 'EDCBA';
  assertEqual(actual, expected, desc);
  
  function assertEqual(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }