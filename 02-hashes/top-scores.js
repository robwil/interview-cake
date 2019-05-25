function sortScores(unorderedScores, highestPossibleScore) {

    // Sort the scores in O(n) time

    // This is going to be counting sort. First we count all the scores. Then we iterate through all possible scores, outputting them the correct number of times.

    // Step 1: count
    let scoreCounts = new Map();
    for (let i = 0; i < unorderedScores.length; i++) {
        const score = unorderedScores[i];
        if (scoreCounts.has(score)) {
            scoreCounts.set(score, scoreCounts.get(score) + 1);
        } else {
            scoreCounts.set(score, 1);
        }
    }

    // Step 2: output sorted scores
    let orderedScores = [];
    for (let score = highestPossibleScore; score >= 0; score--) {
        const count = scoreCounts.get(score);
        for (let i = 0; i < count; i++) {
            orderedScores.push(score);
        }
    }
    return orderedScores;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'no scores';
  let actual = sortScores([], 100);
  let expected = [];
  assertEqual(JSON.stringify(actual), JSON.stringify(expected), desc);
  
  desc = 'one score';
  actual = sortScores([55], 100);
  expected = [55];
  assertEqual(JSON.stringify(actual), JSON.stringify(expected), desc);
  
  desc = 'two scores';
  actual = sortScores([30, 60], 100);
  expected = [60, 30];
  assertEqual(JSON.stringify(actual), JSON.stringify(expected), desc);
  
  desc = 'many scores';
  actual = sortScores([37, 89, 41, 65, 91, 53], 100);
  expected = [91, 89, 65, 53, 41, 37];
  assertEqual(JSON.stringify(actual), JSON.stringify(expected), desc);
  
  desc = 'repeated scores';
  actual = sortScores([20, 10, 30, 30, 10, 20], 100);
  expected = [30, 30, 20, 20, 10, 10];
  assertEqual(JSON.stringify(actual), JSON.stringify(expected), desc);
  
  function assertEqual(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }