'use strict';

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', inputStdin => {
    inputString += inputStdin;
});

process.stdin.on('end', _ => {
    inputString = inputString.replace(/\s*$/, '')
        .split('\n')
        .map(str => str.replace(/\s*$/, ''));

    main();
});

function readLine() {
    return inputString[currentLine++];
}

const SinglyLinkedListNode = class {
    constructor(nodeData) {
        this.data = nodeData;
        this.next = null;
    }
};

const SinglyLinkedList = class {
    constructor() {
        this.head = null;
        this.tail = null;
    }

    insertNode(nodeData) {
        const node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
};

function printSinglyLinkedList(node, sep) {
    while (node != null) {
        process.stdout.write(String(node.data));

        node = node.next;

        if (node != null) {
            process.stdout.write(sep);
        }
    }
}

// Complete the reversePrint function below.

/*
 * For your reference:
 *
 * SinglyLinkedListNode {
 *     int data;
 *     SinglyLinkedListNode next;
 * }
 *
 */
function reversePrint(head) {
    // best case is O(N) time because we need to hit every element
    // naive approach is to store everything in array and then print that, O(N) space
    // I don't think we can do better unless we're willing to spend more time

    // // Step 1: calculate N
    // let n = 0;
    // for (let current = head; current != null; current = current.next) {
    //     n++;
    // }

    // // Step 2: walk Linked List, saving each element to reversed position in array
    // const arr = [];
    // let i = 0;
    // for (let current = head; current != null; current = current.next) {
    //     arr[n - i - 1] = current.data;
    //     i++;
    // }

    // // Step 3: print reversed array
    // for (let i = 0; i < n; i++) {
    //     console.log(arr[i]);
    // }

    // Actually we can do better.
    // If we reverse Linked List in place in 1 pass, then we can reverse it again while printing on 2nd pass.
    // This is a clever way to save space "in place" without letting that side effect show outside the function.

    // Step 1: Reverse linked list in place
    let prev = null;
    for (let current = head; current != null;) {
        const next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }

    // Step 2: Reverse linked list in place, while printing
    const newHead = prev;
    prev = null;
    for (let current = newHead; current != null;) {
        const next = current.next;
        console.log(current.data);
        current.next = prev;
        prev = current;
        current = next;
    }
}

function main() {