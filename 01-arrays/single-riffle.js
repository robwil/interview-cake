function isSingleRiffle(half1, half2, shuffledDeck) {
    // Check if the shuffled deck is a single riffle of the halves
    
    // Insight: it seems to me that a single-riffle deck will preserve the order from half1 and half2
    // We can therefore walk through the three arrays at the same time, with 3 indexes, to confirm order.
    // This is quite similar in nature to the mergeArrays function.

    let index1 = 0;
    let index2 = 0;
    let i = 0;
    while (index1 < half1.length || index2 < half2.length) {
        if (index1 < half1.length && shuffledDeck[i] === half1[index1]) {
            index1++;
        } else if (index2 < half2.length && shuffledDeck[i] == half2[index2]) {
            index2++;
        } else {
            return false;
        }
        i++;
    }
    return i == shuffledDeck.length;
  }
  
  
  // Tests
  
  let desc = 'both halves are the same length';
  let actual = isSingleRiffle([1, 4, 5], [2, 3, 6], [1, 2, 3, 4, 5, 6]);
  assertEquals(actual, true, desc);
  
  desc = 'halves are different lengths';
  actual = isSingleRiffle([1, 5], [2, 3, 6], [1, 2, 6, 3, 5]);
  assertEquals(actual, false, desc);
  
  desc = 'one half is empty';
  actual = isSingleRiffle([], [2, 3, 6], [2, 3, 6]);
  assertEquals(actual, true, desc);
  
  desc = 'shuffled deck is missing cards';
  actual = isSingleRiffle([1, 5], [2, 3, 6], [1, 6, 3, 5]);
  assertEquals(actual, false, desc);
  
  desc = 'shuffled deck has extra cards';
  actual = isSingleRiffle([1, 5], [2, 3, 6], [1, 2, 3, 5, 6, 8]);
  assertEquals(actual, false, desc);
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }