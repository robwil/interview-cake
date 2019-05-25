class WordCloudData {
    constructor(inputString) {
      this.wordsToCounts = new Map();
      this.populateWordsToCounts(inputString);
    }

    addWord(inputString, wordStart, wordEnd) {
        let word = inputString.slice(wordStart, wordEnd).toLowerCase();
        if (word.length === 0) {
            return;
        }
        if (this.wordsToCounts.has(word)) {
            this.wordsToCounts.set(word, this.wordsToCounts.get(word) + 1);
        } else {
            this.wordsToCounts.set(word, 1);
        }
    }
  
    populateWordsToCounts(inputString) {
        // This problem basically gave away its answer by saying to use a Map
        // So the only thing to do is figure out how to split words and handle case insensitivity.
        // We can do both in a single pass O(N) time.

        let wordStart = 0;
        for (let i = 0; i < inputString.length; i++) {
            switch (inputString[i]) {
                case ' ':
                case '.':
                case '!':
                case '?':
                case ';':
                case ':':
                case ',':
                case '&':
                case '(':
                case ')':
                case '-':
                    if (inputString[i] === '-' && inputString[i-1] !== ' ') {
                        // handle case for hyphenated word
                        continue;
                    }
                    this.addWord(inputString, wordStart, i);
                    wordStart = i + 1;
                    break;
                default:
            }
        }
        // add last word
        this.addWord(inputString, wordStart, inputString.length);
    }
  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  // There are lots of valid solutions for this one. You
  // might have to edit some of these tests if you made
  // different design decisions in your solution.
  
  let desc = 'simple sentence';
  let actual = new WordCloudData('I like cake').wordsToCounts;
  let expected = new Map([['i', 1], ['like', 1], ['cake', 1]]);
  assert(isMapsEqual(actual, expected), desc);
  
  desc = 'longer sentence';
  actual = new WordCloudData('Chocolate cake for dinner and pound cake for dessert').wordsToCounts;
  expected = new Map([['and', 1], ['pound', 1], ['for', 2], ['dessert', 1],
    ['chocolate', 1], ['dinner', 1], ['cake', 2]]);
  assert(isMapsEqual(actual, expected), desc);
  
  desc = 'punctuation';
  actual = new WordCloudData('strawberry short cake? yum!').wordsToCounts;
  expected = new Map([['cake', 1], ['strawberry', 1], ['short', 1], ['yum', 1]]);
  assert(isMapsEqual(actual, expected), desc);
  
  desc = 'hyphenated Words';
  actual = new WordCloudData('Dessert - mille-feuille cake').wordsToCounts;
  expected = new Map([['cake', 1], ['dessert', 1], ['mille-feuille', 1]]);
  assert(isMapsEqual(actual, expected), desc);
  
  desc = 'ellipses between words';
  actual = new WordCloudData('Mmm...mmm...decisions...decisions').wordsToCounts;
  expected = new Map([['mmm', 2], ['decisions', 2]]);
  assert(isMapsEqual(actual, expected), desc);
  
  desc = 'apostrophes';
  actual = new WordCloudData("Allie's Bakery: Sasha's Cakes").wordsToCounts;
  expected = new Map([['bakery', 1], ['cakes', 1], ["allie's", 1], ["sasha's", 1]]);
  assert(isMapsEqual(actual, expected), desc);
  
  function isMapsEqual(map1, map2) {
    if (map1.size !== map2.size) {
        console.log(map1);
        console.log(map2);
      return false;
    }
    for (let [key, val] of map1) {
      const testVal = map2.get(key);
  
      // In cases of an undefined value, make sure the key
      // actually exists on the object so there are no false positives
      if (testVal !== val || (testVal === undefined && !map2.has(key))) {
          console.log(map1);
          console.log(map2);
        return false;
      }
    }
    return true;
  }
  
  function assert(condition, desc) {
    if (condition) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL`);
    }
  }