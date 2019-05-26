// https://www.hackerrank.com/challenges/crush/problem
package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
	"log"
	"sort"
)

// Complete the arrayManipulation function below.
// My initial implementation, which has runtime of O(N * m) and space of O(1).
// This is correct, but times out on some of the test cases, likely because N*M can be up to 10^12
func arrayManipulation0(n int32, queries [][]int32) int64 {
	var max int64
	for i := int32(1); i <= n; i++ {
		var sum int64
		for _, query := range queries {
			if len(query) != 3 {
				log.Fatalf("query %v not expected 3 elements", query)
			}
			if query[0] <= i && query[1] >= i {
				sum += int64(query[2])
			}
		}
		if sum > max {
			max = sum
		}
	}
	return max
}

// My second thought, to optimize time, is to use more space.
// How? If we literally make an array of size N, then we just need to iterate once through queries.
// However, that would still take O(N*M) in the worst case because each M query can touch up to N elements of the array.
// Even though both are O(N*M), this one is significantly faster because it's average case is better. But still need more optimization.
func arrayManipulation1(n int32, queries [][]int32) int64 {
	arr := make([]int64, n)
	for _, query := range queries {
		if len(query) != 3 {
			log.Fatalf("query %v not expected 3 elements", query)
		}
		for i := query[0]; i <= query[1]; i++ {
			arr[i-1] += int64(query[2])
		}
	}
	var max int64
	for i := int32(0); i < n; i++ {
		if arr[i] > max {
			max = arr[i]
		}
	}
	return max
}

// Now I'm thinking, there's got to be a way to avoid that O(M*N) to get sums.
// One way is to do a first pass through queries that reverse sorts them by end index (query[1]). This will take O(m lg m).
// Then, we can go through O(N) and use a short-circuited loop through queries that will still be worst-case N*M but much better average case.
// However, turns out this is worse than arrayManipulation1 (not exactly sure why, but I guess it's still M*N and has the extra sort work)
func arrayManipulation2(n int32, queries [][]int32) int64 {
	sort.Slice(queries, func(i, j int) bool {
		return queries[i][1] >= queries[j][1]
	})
	var max int64
	for i := int32(1); i <= n; i++ {
		var sum int64
		for _, query := range queries {
			if len(query) != 3 {
				log.Fatalf("query %v not expected 3 elements", query)
			}
			if query[1] < i {
				break
			}
			if query[0] <= i && query[1] >= i {
				sum += int64(query[2])
			}
		}
		if sum > max {
			max = sum
		}
	}
	return max
}

// Looking at solution that is O(N), their idea is to store difference between current element and previous element instead of actual sum at each position.
// This prevents the need to do N*M, because during the O(M) loop we only have to do constant time operations (2 array updates)
func arrayManipulation3(n int32, queries [][]int32) int64 {
	arr := make([]int64, n)
	for _, query := range queries {
		if len(query) != 3 {
			log.Fatalf("query %v not expected 3 elements", query)
		}
		a := query[0] - 1 //-1 because our array is 0-based
		b := query[1] - 1 //-1 because our array is 0-based
		k := int64(query[2])
		arr[a] += k // store addition at a position, to represent that sum starting
		if b+1 < n {
			arr[b+1] -= k // store subtraction at b+1 position, to represent that sum ending
		}
	}
	var max int64
	var sum int64 // running sum
	for i := int32(0); i < n; i++ {
		sum += arr[i]
		if sum > max {
			max = sum
		}
	}
	return max
}

func arrayManipulation(n int32, queries [][]int32) int64 {
	return arrayManipulation3(n, queries)
}

func main() {
	reader := bufio.NewReaderSize(os.Stdin, 1024*1024)

	stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
	checkError(err)

	defer stdout.Close()

	writer := bufio.NewWriterSize(stdout, 1024*1024)

	nm := strings.Split(readLine(reader), " ")

	nTemp, err := strconv.ParseInt(nm[0], 10, 64)
	checkError(err)
	n := int32(nTemp)

	mTemp, err := strconv.ParseInt(nm[1], 10, 64)
	checkError(err)
	m := int32(mTemp)

	var queries [][]int32
	for i := 0; i < int(m); i++ {
		queriesRowTemp := strings.Split(readLine(reader), " ")

		var queriesRow []int32
		for _, queriesRowItem := range queriesRowTemp {
			queriesItemTemp, err := strconv.ParseInt(queriesRowItem, 10, 64)
			checkError(err)
			queriesItem := int32(queriesItemTemp)
			queriesRow = append(queriesRow, queriesItem)
		}

		if len(queriesRow) != int(3) {
			panic("Bad input")
		}

		queries = append(queries, queriesRow)
	}

	result := arrayManipulation(n, queries)

	fmt.Fprintf(writer, "%d\n", result)

	writer.Flush()
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
