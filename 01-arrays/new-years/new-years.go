// https://www.hackerrank.com/challenges/new-year-chaos/problem

package new_years

import (
	"bufio"
	"io"
	"os"
	"strconv"
	"strings"
	"fmt"
)

// Complete the minimumBribes function below.
func minimumBribes(q []int32) {
	// 1 2 3 4 5
	// 2 1 3 4 5 | 2=1
	// 2 3 1 4 5 | 2=1, 3=1
	// 2 3 1 5 4 | 2=1, 3=1, 5=1
	// 2 3 5 1 4 | 2=1, 3=1, 5=2

	// too chaotic?
	// 2 5 1 3 4
	// the way we detect this is by seeing that 5 is -3 from its start.
	// that can't ever happen. items can be at minimum -2 from their start
	// however, items can be +N from start because all the ones after them
	// could have bribed.

	// so just answering this as true/false we can check the -2 invariant
	// do we also need to check the +index case, or is the -index check enough?
	// trying to think of counterexample...
	// 2 3 4 1? none violate -index constraint. is it possible?
	//    1 2 3 4 -> 2 1 3 4 -> 2 3 1 4 -> 2 3 4 1
	//    Don't think so, so the -index check is sufficient.

	// var bribes int32
	// n := int32(len(q))
	// for i := int32(0); i < n; i += 1 {
	//     actualSticker := q[i]
	//     expectedSticker := i + 1 // if no one moved, expectedSticker is i + 1 (numbering starts at 1 while array index i starts at 0)
	//     if actualSticker == expectedSticker {
	//         // didn't move, so nothing to do
	//         continue
	//     }
	//     if actualSticker > expectedSticker && actualSticker - expectedSticker > 2 {
	//         fmt.Println("Too chaotic")
	//         return
	//     }
	//     if actualSticker < expectedSticker {
	//         bribes += expectedSticker - actualSticker
	//     }
	// }
	// fmt.Printf("%d\n", bribes)

	// the above solution worked but not for the following test case:

	// 1 2 5 3 7 8 6 4 | expected minBribes = 7, we get 6
	// 1 2 3 4 5 6 7 8 | 0
	// 1 2 3 4 5 7 6 8 | 1 (7)
	// 1 2 3 5 4 7 6 8 | 2 (7,5)
	// 1 2 5 3 4 7 6 8 | 3 (7,5,5)
	// 1 2 5 3 7 4 6 8 | 4 (7,5,5,7)
	// 1 2 5 3 7 6 4 8 | 5 (7,5,5,7,6)
	// 1 2 5 3 7 6 8 4 | 6 (7,5,5,7,6,8)
	// 1 2 5 3 7 8 6 4 | 7 (7,5,5,7,6,8,8)

	// 3 moved up 1
	// 6 moved up 1
	// 4 moved up 4 (there are 4 numbers ahead of 4, limited number of ways it could have moved)

	// 5 moved down 2
	// 7 moved down 2
	// 8 moved down 2

	// so using either the up diff or down diff, we still get 6 whereas 7 is correct

	// how do we get the true answer?
	// is there any way aside from simulating the actual movement?


	// how long would that take? for each N, there can be 0/1/2 options. so 3^N possibilities
	// but via backtracking we can eliminate many possibilities?
	// I started doing that below, to no avail... then eventually looked up answer and implemented below

	//bribes := make(map[int32]int8)
	//test := make([]int32, len(q))
	//for i := 0; i < len(q); i++ {
	//	test[i] = int32(i + 1)
	//}
	//min, result := minimumBribesRecurse(q, test, int32(len(q)-1), bribes)
	//if result {
	//	fmt.Printf("%d\n", min)
	//} else {
	//	fmt.Println("Too chaotic")
	//}

	var bribes int32
	n := int32(len(q))
	for i := n - 1; i >= 0; i-- {
	    actualSticker := q[i]
	    expectedSticker := i + 1 // if no one moved, expectedSticker is i + 1 (numbering starts at 1 while array index i starts at 0)
	    if actualSticker == expectedSticker {
	        // didn't move, so nothing to do
	        continue
	    }
	    if actualSticker > expectedSticker && actualSticker - expectedSticker > 2 {
	    	// moving >2 left is impossible, so fail fast
	        fmt.Println("Too chaotic")
	        return
	    }
	    // calculate how many people in front of me bribed to get me here
	    for j := max(0, actualSticker-2); j < i; j++ {
	    	if q[j] > actualSticker {
	    		bribes++
			}
		}
	}
	fmt.Printf("%d\n", bribes)
}

func max(i, j int32) int32 {
	if i > j {
		return i
	}
	return j
}

//func minimumBribesRecurse(original []int32, test []int32, i int32, bribes map[int32]int8) (int32, bool) {
//	if i == 0 {
//		return 0, equalArr(original, test)
//	}
//	min := int32(9999999)
//	var overallResult bool
//	// test case where we don't bribe
//	bribeCount1, result1 := minimumBribesRecurse(original, test, i-1, bribes)
//	if result1 && bribeCount1 < min {
//		min = bribeCount1
//		overallResult = true
//	}
//	// test case where we or someone after us bribes
//	for j := int32(len(original)) - 1; j >= i; j-- {
//		if bribes[test[j]] < 2 {
//			bribe(test, bribes, j, false)
//			bribeCount2, result2 := minimumBribesRecurse(original, test, j-1, bribes)
//			bribe(test, bribes, j, true)
//			if result2 && bribeCount2+1 < min {
//				min = bribeCount2 + 1
//				overallResult = true
//			}
//		}
//	}
//	return min, overallResult
//}
//// 1 2 3
//// 1 3 2
//// 3 1 2
//// 3 2 1
//
//func equalArr(arr1 []int32, arr2 []int32) bool {
//	if len(arr1) != len(arr2) {
//		return false
//	}
//	for i, v := range arr1 {
//		if v != arr2[i] {
//			return false
//		}
//	}
//	return true
//}
//
//// func countBribes(bribes map[int32]int8) int32 {
////     var bribeCount int32
////     for _, v := range bribes {
////         bribeCount += int32(v)
////     }
////     return bribeCount
//// }
//
//func bribe(arr []int32, bribes map[int32]int8, i int32, undo bool) {
//	tmp := arr[i]
//	arr[i] = arr[i-1]
//	arr[i-1] = tmp
//	if undo {
//		bribes[tmp] -= 1
//	} else {
//		bribes[tmp] += 1
//	}
//}

func main() {
	reader := bufio.NewReaderSize(os.Stdin, 1024*1024)

	tTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
	checkError(err)
	t := int32(tTemp)

	for tItr := 0; tItr < int(t); tItr++ {
		nTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
		checkError(err)
		n := int32(nTemp)

		qTemp := strings.Split(readLine(reader), " ")

		var q []int32

		for i := 0; i < int(n); i++ {
			qItemTemp, err := strconv.ParseInt(qTemp[i], 10, 64)
			checkError(err)
			qItem := int32(qItemTemp)
			q = append(q, qItem)
		}

		minimumBribes(q)
	}
}

func readLine(reader *bufio.Reader) string {
	str, _, err := reader.ReadLine()
	if err == io.EOF {
		return ""
	}

	return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
	if err != nil {
		panic(err)
	}
}
