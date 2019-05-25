function hasPalindromePermutation(theString) {

    // Check if any permutation of the input is a palindrome
    
    // Naive solution would be to calculate all permutations (which would be N! space) and then check if they are palindromes (N time each)
    // Insight: What does a set of string needs to be able to make a palindrome? It needs an even number of occurrences of each character for even length words,
    //          and for odd length words it's the same but we can have one letter with an odd length.

    // e.g. CAT can't be palindromed. All letters have odd lengths (C=1, A=1, T=1)
    //      tacocat can be. T=2,A=2,C=2,O=1
    //      eve can be. E=2, V=1
    //      eevee can be. E=4, V=1
    //      eevvee can be. E=4, V=2
    //      eevxvee can be. E=4, V=2, X=1

    // This winds up with time O(N) and space O(k) where k is the number of distinct characters in the input string.

    // To efficiently count letters, we use a hash map.
    let charCounts = {};
    for (let i = 0; i < theString.length; i++) {
        const char = theString[i];
        if (charCounts[char] === undefined) {
            charCounts[char] = 1;
        } else {
            charCounts[char]++;
        }
    }
    // Next let's do a single loop through the object, in order to check palindome invariant.
    const isOdd = theString.length % 2 === 1;
    let numOdd = 0;
    Object.keys(charCounts).forEach((char) => {
        if (charCounts[char] % 2 == 1) {
            numOdd++;
        }
    });
    return (isOdd && numOdd === 1) || (!isOdd && numOdd === 0);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'permutation with odd number of chars';
  assertEqual(hasPalindromePermutation('aabcbcd'), true, desc);
  
  desc = 'permutation with even number of chars';
  assertEqual(hasPalindromePermutation('aabccbdd'), true, desc);
  
  desc = 'no permutation with odd number of chars';
  assertEqual(hasPalindromePermutation('aabcd'), false, desc);
  
  desc = 'no permutation with even number of chars';
  assertEqual(hasPalindromePermutation('aabbcd'), false, desc);
  
  desc = 'empty string';
  assertEqual(hasPalindromePermutation(''), true, desc);
  
  desc = 'one character string ';
  assertEqual(hasPalindromePermutation('a'), true, desc);
  
  function assertEqual(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }