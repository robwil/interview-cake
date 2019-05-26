function getRandom(floor, ceiling) {
    return Math.floor(Math.random() * (ceiling - floor + 1)) + floor;
}

// original naive approach, but this doesn't produce uniform distribution
function shuffle0(arr) {
    const N = arr.length;
    for (let i = 0; i < N; i++) {
        r = getRandom(0, N-1);
        swap(arr, i, r);
    }
}

function swap(arr, i, j) {
    tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

// another approach, what if we walked through array, and in each slot randomly choose one of the numbers from the original array, and do this N times
// Note: this is not in-place as the problem asks for
function shuffle1(arr) {
    const N = arr.length;
    let arr1 = [...arr];
    let arr2 = [];
    // construct new array by randomly choosing elements from last array
    for (let i = 0; i < N; i++) {
        const r = getRandom(0, arr1.length-1);
        arr2[i] = arr1[r];
        arr1.splice(r, 1);
    }
    // copy back into original array to simulate in-place
    for (let i = 0; i < N; i++) {
        arr[i] = arr2[i];
    }
}

// how do we do this in-place? one option is to swap each element within a decreasing subset of the array
function shuffle2(arr) {
    const N = arr.length;
    for (let i = 0; i < N; i++) {
        r = getRandom(i, N-1);
        swap(arr, i, r);
    }
}

function test(shuffleAlgo) {
    const sample1 = [1, 2, 3, 4, 5];
    console.log('Initial array: ', sample1);
    shuffleAlgo(sample1);
    console.log('Shuffled array: ', sample1);

    const numTrials = 100000;
    let sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0;
    for (let i = 0; i < numTrials; i++) {
        const sample = [1, 2, 3];
        shuffleAlgo(sample);
        if (arrayEqual(sample, [1,2,3])) {
            sum1++;
        } else if (arrayEqual(sample, [1,3,2])) {
            sum2++;
        } else if (arrayEqual(sample, [2,1,3])) {
            sum3++;
        } else if (arrayEqual(sample, [2,3,1])) {
            sum4++;
        } else if (arrayEqual(sample, [3,1,2])) {
            sum5++;
        } else if (arrayEqual(sample, [3,2,1])) {
            sum6++;
        }
    }
    console.log(sum1/numTrials, sum2/numTrials, sum3/numTrials, sum4/numTrials, sum5/numTrials, sum6/numTrials);
}

function arrayEqual(arr1, arr2) {
    if (arr1.length != arr2.length) {
        return false;
    }
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] != arr2[i]) {
            return false;
        }
    }
    return true;
}
  
test(shuffle0);
test(shuffle1);
test(shuffle2);