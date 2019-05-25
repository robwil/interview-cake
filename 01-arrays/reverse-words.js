function reverseWordsFail(message) {
    // can we do this in a single pass? probably not exactly, but O(N) feels possible by doing single pass with backtracking

    // step 1: work backward, finding a word
    const N = message.length;
    let wordEnd = N - 1;
    let frontIndex = 0;
    let backIndex = N - 1;
    let tmp = '';
    for (; backIndex >= 0; backIndex--) {
        if (message[backIndex] === ' ') {
            // case 1: word we are trying to move is same size as destination word, in which case we do simple swap

            // case 2: word we are trying to move is bigger than destination word, in which case we move first word and keep partial other word

            // case 3: word we are trying to move is smaller than destination word, in which case we move second word and keep partial other word
            //let word = message.slice(i, wordEnd);
            for (let i = backIndex; i < wordEnd; i++) {
                //tmp = 
            }
        }
    }
}

// cat middle a
// a middle cat
// [a]at middle [t]
// [a] t middle[a]t
// [a] _ middl[c]at  |e|
// a _ middl[ ]cat |le|
// a _ midd[e] cat |dl|
// a _ mid[l]e cat |dd|
// a _ mi[d]le cat |

// b dog
// dog b

// okay, so after checking how to swap words, it becomes clear that it requires a lot of work shifting. O(N^2) in the worst case.
// and writing that code would be really messy.

// I was a bit stumped here, so I looked at the hints. It suggested reversing all the characters first, and then realizing that this gets us close to the answer, except that each _word_ is reversed.
// So can we re-reverse each word? I think so.


function reverseInPlace(arrayOfChars, startIndex, endIndex) {
    for (var i = 0; i < (endIndex-startIndex)/2; i++) {
        var tmp = arrayOfChars[startIndex + i];
        arrayOfChars[startIndex + i] = arrayOfChars[endIndex - i - 1];
        arrayOfChars[endIndex - i - 1] = tmp;
    }
}

function reverseWords(message) {
    // start by reversing entire string
    reverseInPlace(message, 0, message.length);
    // next, iterate through message, reversing each word in place
    let wordStart = 0;
    for (let i = 0; i < message.length; i++) {
        if (message[i] === ' ') {
            reverseInPlace(message, wordStart, i);
            wordStart = i + 1;
        }
    }
    // reverse final word (not covered in above loop)
    reverseInPlace(message, wordStart, message.length);
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Tests
  
  let desc = 'one word';
  let input = 'vault'.split('');
  reverseWords(input);
  let actual = input.join('');
  let expected = 'vault';
  assertEqual(actual, expected, desc);
  
  desc = 'two words';
  input = 'thief cake'.split('');
  reverseWords(input);
  actual = input.join('');
  expected = 'cake thief';
  assertEqual(actual, expected, desc);

  desc = 'reverseInPlace later';
  input = 'thief ekac'.split('');
  reverseInPlace(input, 6, input.length);
  actual = input.join('');
  expected = 'thief cake';
  assertEqual(actual, expected, desc);
  
  desc = 'three words';
  input = 'one another get'.split('');
  reverseWords(input);
  actual = input.join('');
  expected = 'get another one';
  assertEqual(actual, expected, desc);
  
  desc = 'multiple words same length';
  input = 'rat the ate cat the'.split('');
  reverseWords(input);
  actual = input.join('');
  expected = 'the cat ate the rat';
  assertEqual(actual, expected, desc);
  
  desc = 'multiple words different lengths';
  input = 'yummy is cake bundt chocolate'.split('');
  reverseWords(input);
  actual = input.join('');
  expected = 'chocolate bundt cake is yummy';
  assertEqual(actual, expected, desc);
  
  desc = 'empty string';
  input = ''.split('');
  reverseWords(input);
  actual = input.join('');
  expected = '';
  assertEqual(actual, expected, desc);
  
  function assertEqual(a, b, desc) {
    if (a === b) {
      console.log(`${desc} ... PASS`);
    } else {
      console.log(`${desc} ... FAIL: ${a} != ${b}`);
    }
  }