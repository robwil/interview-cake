function findUniqueDeliveryId(deliveryIds) {

    // Find the one unique ID in the array

    // We could obviously do this in O(N) time and O(N) space by using a hash table.
    // But this is the problem where we can use the bitwise trick, requiring only O(1) space in the form of an int.

    let x = 0;
    const N = deliveryIds.length;
    for (let i = 0; i < N; i++) {
        // XOR every number together, and the one non-duplicated number will be remaining.
        x ^= deliveryIds[i];
    }
  
    return x;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'one drone';
  let actual = findUniqueDeliveryId([1]);
  let expected = 1;
  assertEquals(actual, expected, desc);
  
  desc = 'unique ID comes first';
  actual = findUniqueDeliveryId([1, 2, 2]);
  expected = 1;
  assertEquals(actual, expected, desc);
  
  desc = 'unique ID comes last';
  actual = findUniqueDeliveryId([3, 3, 2, 2, 1]);
  expected = 1;
  assertEquals(actual, expected, desc);
  
  desc = 'unique ID in middle';
  actual = findUniqueDeliveryId([3, 2, 1, 2, 3]);
  expected = 1;
  assertEquals(actual, expected, desc);
  
  desc = 'many drones';
  actual = findUniqueDeliveryId([2, 5, 4, 8, 6, 3, 1, 4, 2, 3, 6, 5, 1]);
  expected = 8;
  assertEquals(actual, expected, desc);
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }