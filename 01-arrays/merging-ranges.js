function mergeRanges(meetings) {
    // Merge meetings ranges
    return mergeRangesPresortGreedy(meetings);
}

// First "Brute force" method that comes to mind is using an array representing the full time range
// and then setting each to true/false as we come across meetings in those ranges.
// This implementation does not even fully work, because it doesn't account for the fact that 1-3 and 4-8 are disjoint (merges them to 1-8).
// But I gave up on it because I realized this approach is not great, and not even worth optimizing (e.g. using bitmaps instead of array to save space)
function mergeRangesBruteForce(meetings) {
    if (meetings.length <= 0) {
        return meetings;
    }

    // Step 1: calculate min startTime and max endTime
    var minStartTime = meetings[0].startTime;
    var maxEndTime = meetings[0].endTime;
    for (var i = 1; i < meetings.length; i++) {
        if (meetings[i].startTime < minStartTime) {
            minStartTime = meetings[i].startTime;
        }
        if (meetings[i].endTime > maxEndTime) {
            maxEndTime = meetings[i].endTime;
        }
    }

    // Step 2: allocate array representing the full day, then fill in which times are full
    var fullDay = [];
    for (var i = 0; i < meetings.length; i++) {
        for (var j = meetings[i].startTime; j <= meetings[i].endTime; j++) {
            fullDay[j - minStartTime] = true;
        }
    }
    
    // Step 3: perform actual "merging" by outputting new meeting blocks
    var outputMeetings = [];
    var startTime = minStartTime;
    var endTime = minStartTime;
    for (var i = minStartTime; i <= maxEndTime; i++) {
        if (!fullDay[i - minStartTime]) {
            if (startTime != endTime) {
                outputMeetings.push({startTime: startTime, endTime: endTime});
            }
            if (i + 1 > maxEndTime) {
                break;
            }
            startTime = i + 1;
            endTime = i + 1;
            i = i + 1;
        }
        endTime = i;
    }
    if (startTime != endTime) {
        outputMeetings.push({startTime: startTime, endTime: endTime});
    }
    return outputMeetings;
}

// Next approach I thought of is whether it's possible to merge ranges with one pass O(n),
// using an object/map to store the startTime as key and the max endTime as value.
// The issue here is we'll need to merge later to handle overlaps, e.g. where startTime 2 -> 4 and startTime 1 -> 3
function mergeRangesWithHash(meetings) {
    var startTimes = {};
    for (var i = 1; i < meetings.length; i++) {
        // extend max endTime seen for the startTime
        if (startTimes[meetings[i].startTime] === undefined || meetings[i].endTime > startTimes[meetings[i].startTime]) {
            startTimes[meetings[i].startTime] = meetings[i].endTime;
        }
    }
    // e.g. time
    // 1 -> 3, 2 -> 4 will lead to {1:3, 2:4}
    // 5 -> 6, 6 -> 8 will lead to {5:6, 6:8}
    // so this doesn't really buy us anything. I was hoping the hash table's easy lookup of startTime would help, but it doesn't allow us to easily see the ranges we have.
}

// Abandoning hash table approach, I'm considering instead if we do an actual merge concept: start creating the outputMeetings and then merge new information in as we get it.
// This is sort of a "greedy" algorithm where we try to make the answer as we go.
// This approach works, but it doesn't inherently sort the arrays, requiring a sort at the end, and it winds up requiring a final pass through to fix things (likely because we aren't sorting).
// Furthermore, this is O(N^2) in the worst case, which is when we have N disjoint meetings but wind up comparing to all previous ones as we iterate.
function mergeRangesWithGreedy(meetings) {
    if (meetings.length <= 1) {
        return meetings;
    }
    var outputMeetings = [meetings[0]];
    for (var i = 1; i < meetings.length; i++) {
        var added = false;
        for (var j = 0; j < outputMeetings.length; j++) {
            // case where new meeting can be included in existing meeting due to startTime
            // [0 1 2]
            //   [1 2 3]
            if (meetings[i].startTime >= outputMeetings[j].startTime && meetings[i].startTime <= outputMeetings[j].endTime) {
                // only extend endTime if it actually gets bigger
                if (meetings[i].endTime > outputMeetings[j].endTime) {
                    outputMeetings[j].endTime = meetings[i].endTime;
                }
                added = true;
            }
            // case where new meeting can be included in existing meeting due to endTime
            //   [ 1 2 3 ]
            // [ 0 1 2 ]
            if (meetings[i].endTime <= outputMeetings[j].endTime && meetings[i].endTime >= outputMeetings[j].startTime) {
                // only extend startTime if it actually gets bigger
                if (meetings[i].startTime < outputMeetings[j].startTime) {
                    outputMeetings[j].startTime = meetings[i].startTime;
                }
                added = true;
            }
        }
        if (!added) {
            outputMeetings.push(meetings[i]);
        }
    }
    // also must sort output
    outputMeetings.sort(function(a, b) {
        return a.startTime - b.startTime;
    });
    // final condensing of output, to handle cases where later edits to outputMeetings affect optimal merging
    for (var i = 0; i < outputMeetings.length; i++) {
        if (i + 1 < outputMeetings.length && outputMeetings[i].endTime >= outputMeetings[i+1].startTime) {
            outputMeetings[i].endTime = outputMeetings[i+1].endTime;
            outputMeetings.splice(i+1, 1);
        }
    }
    return outputMeetings;
}

// Given the need to sort anyway, and the advice that O(N lg N) is best possible, I realized that if we just sort first, the problems with previous solution go away.
function mergeRangesPresortGreedy(meetings) {
    let outputMeetings = [...meetings];
    if (meetings.length <= 1) {
        return outputMeetings;
    }
    outputMeetings.sort(function(a, b) {
        return a.startTime - b.startTime;
    });
    for (var i = 0; i+1 < outputMeetings.length; i++) {
        // case where new meeting can be included in existing meeting due to startTime
        // [0 1 2]
        //   [1 2 3]
        if (outputMeetings[i+1].startTime >= outputMeetings[i].startTime && outputMeetings[i+1].startTime <= outputMeetings[i].endTime) {
            // only extend endTime if it actually gets bigger
            if (outputMeetings[i+1].endTime > outputMeetings[i].endTime) {
                outputMeetings[i].endTime = outputMeetings[i+1].endTime;
            }
            // regardless, delete meeting at i+1 spot
            outputMeetings.splice(i+1, 1);
            i--; // re-run current check to merge next element into the one that was just merged
        }
    }
    return outputMeetings;
}
  
  
  // Tests
  
  let desc = 'meetings overlap';
  let actual = mergeRanges([{ startTime: 1, endTime: 3 }, { startTime: 2, endTime: 4 }]);
  let expected = [{ startTime: 1, endTime: 4 }];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'meetings touch';
  actual = mergeRanges([{ startTime: 5, endTime: 6 }, { startTime: 6, endTime: 8 }]);
  expected = [{ startTime: 5, endTime: 8 }];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'meeting contains other meeting';
  actual = mergeRanges([{ startTime: 1, endTime: 8 }, { startTime: 2, endTime: 5 }]);
  expected = [{ startTime: 1, endTime: 8 }];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'meetings stay separate';
  actual = mergeRanges([{ startTime: 1, endTime: 3 }, { startTime: 4, endTime: 8 }]);
  expected = [{ startTime: 1, endTime: 3 }, { startTime: 4, endTime: 8 }];
  assertArrayEquals(actual, expected, desc);

  desc = 'third meeting joins previous two meetings';
  actual = mergeRanges([{ startTime: 0, endTime: 1 }, { startTime: 3, endTime: 4 }, { startTime: 1, endTime: 3 }]);
  expected = [{ startTime: 0, endTime: 4 }];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'multiple merged meetings';
  actual = mergeRanges([
    { startTime: 1, endTime: 4 },
    { startTime: 2, endTime: 5 },
    { startTime: 5, endTime: 8 },
  ]);
  expected = [{ startTime: 1, endTime: 8 }];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'meetings not sorted';
  actual = mergeRanges([
    { startTime: 5, endTime: 8 },
    { startTime: 1, endTime: 4 },
    { startTime: 6, endTime: 8 },
  ]);
  expected = [{ startTime: 1, endTime: 4 }, { startTime: 5, endTime: 8 }];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'oneLongMeetingContainsSmallerMeetings';
  actual = mergeRanges([
    { startTime: 1, endTime: 10 },
    { startTime: 2, endTime: 5 },
    { startTime: 6, endTime: 8 },
    { startTime: 9, endTime: 10 },
    { startTime: 10, endTime: 12 }
  ]);
  expected = [
    { startTime: 1, endTime: 12 }
  ];
  assertArrayEquals(actual, expected, desc);
  
  desc = 'sample input';
  actual = mergeRanges([
    { startTime: 0, endTime: 1 },
    { startTime: 3, endTime: 5 },
    { startTime: 4, endTime: 8 },
    { startTime: 10, endTime: 12 },
    { startTime: 9, endTime: 10 },
  ]);
  expected = [
    { startTime: 0, endTime: 1 },
    { startTime: 3, endTime: 8 },
    { startTime: 9, endTime: 12 },
  ];
  assertArrayEquals(actual, expected, desc);
  
  function assertArrayEquals(a, b, desc) {
    const arrayA = JSON.stringify(a);
    const arrayB = JSON.stringify(b);
    if (arrayA !== arrayB) {
      console.log(`${desc} ... FAIL: ${arrayA} != ${arrayB}`)
    } else {
      console.log(`${desc} ... PASS`);
    }
  }