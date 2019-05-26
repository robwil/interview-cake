package main

import (
	"testing"
	"math/rand"
)

var result int64

func BenchmarkArrayManipulation0_100(b *testing.B) {
	m := 100
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation0(n32, queries)
	result = r
}

func BenchmarkArrayManipulation0_500(b *testing.B) {
	m := 500
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation0(n32, queries)
	result = r
}

func BenchmarkArrayManipulation1_100(b *testing.B) {
	m := 100
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation1(n32, queries)
	result = r
}

func BenchmarkArrayManipulation1_500(b *testing.B) {
	m := 500
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation1(n32, queries)
	result = r
}

func BenchmarkArrayManipulation2_100(b *testing.B) {
	m := 100
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation2(n32, queries)
	result = r
}

func BenchmarkArrayManipulation2_500(b *testing.B) {
	m := 500
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation2(n32, queries)
	result = r
}

func BenchmarkArrayManipulation3_100(b *testing.B) {
	m := 100
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation2(n32, queries)
	result = r
}

func BenchmarkArrayManipulation3_500(b *testing.B) {
	m := 500
	n32 := int32(b.N)
	var r int64
	queries := generateQueries(m, n32)
	r = arrayManipulation2(n32, queries)
	result = r
}

func generateQueries(m int, n32 int32) [][]int32 {
	queries := make([][]int32, m)
	for i := 0; i < m; i++ {
		r1 := rand.Int31n(n32)
		r2 := r1 + rand.Int31n(n32)
		if r2 >= n32 {
			r2 = n32 - 1
		}
		queries[i] = []int32{1 + r1, 1 + r2, rand.Int31n(n32)}
	}
	return queries
}

