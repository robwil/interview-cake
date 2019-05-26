function findRotationPoint(words) {
    // despite the "rotation" description, this problem is really asking to find the min element. Naively, that is O(N).
    // But since it's semi-sorted, we can do better, likely O(lgN).
    // of course, if it's sorted we know the min is at index 0, but here we need to find that.
    // knowing that everything is in fact sorted, the way we find the min element is by comparing two elements.
    //    If element1 <= element2, the true min must be outside element1,element2.
    //    If element1 > element2, the true min must be inside (element1,element2]

    const N = words.length;
    
    let leftIndex = 0;
    let rightIndex = N - 1;
    while (rightIndex - leftIndex > 0) {
        if (words[leftIndex] >= words[rightIndex]) {
            leftIndex = Math.ceil((rightIndex - leftIndex) / 2) + leftIndex;
        } else {
            rightIndex = Math.floor((rightIndex - leftIndex) / 2) + leftIndex;
        }
    }
    return leftIndex;
}

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'small array';
  let actual = findRotationPoint(['cape', 'cake']);
  let expected = 1;
  assertEquals(actual, expected, desc);
  
  desc = 'medium array';
  actual = findRotationPoint(['grape', 'orange', 'plum', 'radish', 'apple']);
  expected = 4;
  assertEquals(actual, expected, desc);

  desc = 'already in sorted order';
  actual = findRotationPoint(['a', 'b', 'c', 'd', 'e']);
  expected = 0;
  assertEquals(actual, expected, desc);
  
  desc = 'large array';
  actual = findRotationPoint(['ptolemaic', 'retrograde', 'supplant',
    'undulate', 'xenoepist', 'asymptote',
    'babka', 'banoffee', 'engender',
    'karpatka', 'othellolagkage']);
  expected = 5;
  assertEquals(actual, expected, desc);
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }