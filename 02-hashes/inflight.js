// time O(N)
// space O(N)
function canTwoMoviesFillFlight(movieLengths, flightLength) {
    let lengthsSeenSoFar = {};
    for (let i = 0; i < movieLengths.length; i++) {
        if (lengthsSeenSoFar[flightLength - movieLengths[i]]) {
            return true;
        }
        lengthsSeenSoFar[movieLengths[i]] = true;
    }
    return false;
}
  
  
  
  
  // Tests
  
  let desc = 'short flight';
  let actual = canTwoMoviesFillFlight([2, 4], 1);
  let expected = false;
  assertEquals(actual, expected, desc);
  
  desc = 'long flight';
  actual = canTwoMoviesFillFlight([2, 4], 6);
  expected = true;
  assertEquals(actual, expected, desc);
  
  desc = 'one movie half flight length';
  actual = canTwoMoviesFillFlight([3, 8], 6);
  expected = false;
  assertEquals(actual, expected, desc);
  
  desc = 'two movies half flight length';
  actual = canTwoMoviesFillFlight([3, 8, 3], 6);
  expected = true;
  assertEquals(actual, expected, desc);
  
  desc = 'lots of possible pairs';
  actual = canTwoMoviesFillFlight([1, 2, 3, 4, 5, 6], 7);
  expected = true;
  assertEquals(actual, expected, desc);
  
  desc = 'not using first movie';
  actual = canTwoMoviesFillFlight([4, 3, 2], 5);
  expected = true;
  assertEquals(actual, expected, desc);
  
  desc = 'only one movie';
  actual = canTwoMoviesFillFlight([6], 6);
  expected = false;
  assertEquals(actual, expected, desc);
  
  desc = 'no movies';
  actual = canTwoMoviesFillFlight([], 2);
  expected = false;
  assertEquals(actual, expected, desc);
  
  function assertEquals(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }

// Bonus
// 1) What if we wanted the movie lengths to sum to something close to the flight length (say, within 20 minutes)?
//      - Depending on exactly what they wanted (+/- 20 minutes versus flightLength-20 target), could set all those targets in the hash as well.
//      - E.g. if a movie had length 120, we could set keys 100 - 120 as true. Then when searching, we'd check for 100-120.
//      - Worst case here is storing 20*N values, but that's still O(N) space.
//      - If space was a concern, O(N^2) runtime option might be better since that would allow more complex heuristics.
// 2) What if we wanted to fill the flight length as nicely as possible with any number of movies (not just 2)?
//      - This is the Array Sum problem, which requires recursion or dynamic programming and has very slow runtime.
// 3) What if we knew that movieLengths was sorted? Could we save some space and/or time?
//      - Yes. If it's sorted, we can do O(N) with no hashmap, by using two pointers at start/end and moving toward the middle until we are sure there is no sum.