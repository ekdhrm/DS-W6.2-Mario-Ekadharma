# Data Structures: Queue Assignment
**Course:** Data Structures | **Topic:** Queue & Priority Queue  
**Author:** Mario | **Language:** Java 21

---

## Overview

This assignment contains four problems that explore different applications
of the Queue data structure, ranging from basic FIFO queues to priority queues
and round-robin scheduling.

---

## Q1 - A Love Letter from Gibekkk

### Problem Summary
Simulate a letter delivery service. Letters are dispatched in order of their
priority (lower number = higher urgency). Track and display the status of every
letter at each delivery step.

### Key Data Structures
- `PriorityQueue<Letter>` (min-heap) - drives delivery order by sendPriority
- `ArrayList<Letter>` - registry for ordered display at each print cycle

### Logic Walkthrough
1. All letters enter the queue at once on startup
2. On each cycle, the highest-priority letter is polled (PENDING)
3. Clock advances by that letter's delivery duration
4. Letter is marked SENT, next cycle begins
5. State is printed at every step until the queue is empty

### Status Transitions
```
QUEUED -> PENDING -> SENT
```
- QUEUED  : sitting in the delivery queue
- PENDING : clock is running, being delivered right now
- SENT    : delivery complete

### How to Compile & Run
```bash
javac LoveLetter.java
java LoveLetter
```
Then type input manually, or use a file:
```bash
java LoveLetter < input.txt
```

### Test Cases

**Test 1 - Basic 3 letters in priority order**
```
Input:
3
Jevlin 5 1
Mario 3 2
Bimo 7 3

Expected Output:
Time : 0
1 | Jevlin | PENDING
2 | Mario | QUEUED
3 | Bimo | QUEUED

Time : 5
1 | Jevlin | SENT
2 | Mario | PENDING
3 | Bimo | QUEUED

Time : 8
1 | Jevlin | SENT
2 | Mario | SENT
3 | Bimo | PENDING

Time : 15
1 | Jevlin | SENT
2 | Mario | SENT
3 | Bimo | SENT
```

**Test 2 - Priority NOT in input order (tests heap reordering)**
```
Input:
4
Dito 4 3
Rakha 2 1
Sinta 6 4
Yudi 3 2

Expected Output:
Time : 0
1 | Raka | PENDING
2 | Yudi | QUEUED
3 | Dito | QUEUED
4 | Sinta | QUEUED

Time : 2
1 | Raka | SENT
2 | Yudi | PENDING
3 | Dito | QUEUED
4 | Sinta | QUEUED

Time : 5
1 | Raka | SENT
2 | Yudi | SENT
3 | Dito | PENDING
4 | Sinta | QUEUED

Time : 9
1 | Raka | SENT
2 | Yudi | SENT
3 | Dito | SENT
4 | Sinta | PENDING

Time : 15
1 | Raka | SENT
2 | Yudi | SENT
3 | Dito | SENT
4 | Sinta | SENT
```

**Test 3 - Single letter edge case**
```
Input:
1
Aiko 10 1

Expected Output:
Time : 0
1 | Aiko | PENDING

Time : 10
1 | Aiko | SENT
```

**Test 4 - Priority 0 (minimum boundary)**
```
Input:
3
Vega 1 0
Nova 2 5
Luna 3 10

Expected Output:
Time : 0
0 | Vega | PENDING
5 | Nova | QUEUED
10 | Luna | QUEUED

Time : 1
0 | Vega | SENT
5 | Nova | PENDING
10 | Luna | QUEUED

Time : 3
0 | Vega | SENT
5 | Nova | SENT
10 | Luna | PENDING

Time : 6
0 | Vega | SENT
5 | Nova | SENT
10 | Luna | SENT
```

---

## Q2 - Respin with The Great Kasmir

### Problem Summary
Given a sequence of add and take-out operations, determine which data structure
the sequence is consistent with: Stack, Queue, Priority Queue, ambiguous, or none.

### Key Data Structures
- `ArrayDeque<Integer>` - simulates Stack (LIFO)
- `LinkedList<Integer>` - simulates Queue (FIFO)
- `PriorityQueue<Integer>` (max-heap) - simulates Priority Queue

### Logic Walkthrough
1. Read the operations for one group
2. Replay the sequence independently against all three structures
3. For each structure, check if every take-out matches what that structure would produce
4. Count matches and classify accordingly

### Classification Table
| Match Count | Output |
|---|---|
| 0 | tidak ada |
| 1 | stack / queue / priority queue |
| 2 or more | tidak yakin |

### Structure Behavior Reference
- Stack: take-out always returns the **most recently added** value (LIFO)
- Queue: take-out always returns the **oldest added** value (FIFO)
- Priority Queue: take-out always returns the **highest** value (Max-heap)

### How to Compile & Run
```bash
javac Respin.java
java Respin
```

### Test Cases

**Test 1 - Pure Stack**
```
Input:
6
1 30
1 10
1 20
2 20
2 10
2 30

Expected Output:
stack
```

**Test 2 - Pure Queue**
```
Input:
6
1 10
1 20
1 30
2 10
2 20
2 30

Expected Output:
queue
```

**Test 3 - Pure Max Priority Queue**
```
Input:
6
1 10
1 50
1 30
2 50
2 30
2 10

Expected Output:
priority queue
```

**Test 4 - No matching structure**
```
Input:
4
1 10
1 20
2 15
2 10

Expected Output:
tidak ada
```

**Test 5 - Ambiguous (single add + take-out matches all three)**
```
Input:
2
1 42
2 42

Expected Output:
tidak yakin
```

**Test 6 - Multiple groups in one input**
```
Input:
4
1 7
1 7
2 7
2 7
6
1 3
1 8
1 1
2 8
2 3
2 1

Expected Output:
tidak yakin
priority queue
```

---

## Q3 - Door Response Queue

### Problem Summary
Simulate a round-robin consultation queue. Each student has a limited number
of chances. After each turn, if chances remain they rejoin the back of the queue.
If not, they leave entirely.

### Key Data Structure
- `LinkedList<Student>` used as a Queue (FIFO)

### Logic Walkthrough
1. All students enter the queue in input order
2. Dequeue the front student
3. Decrement their chances and print result
4. If chances > 0: print "Try Again" and re-enqueue at the back
5. If chances = 0: print "Get Out" and discard
6. Repeat until queue is empty

### How to Compile & Run
```bash
javac DoorResponse.java
java DoorResponse
```

### Test Cases

**Test 1 - Given sample from problem**
```
Input:
3
Aristo Inno Vergo
2 1 2

Expected Output:
Aristo|Try Again|1
Inno|Get Out|0
Vergo|Try Again|1
Aristo|Get Out|0
Vergo|Get Out|0
```

**Test 2 - All students with 1 chance**
```
Input:
3
Toni Budi Cici
1 1 1

Expected Output:
Toni|Get Out|0
Budi|Get Out|0
Cici|Get Out|0
```

**Test 3 - One student cycling alone**
```
Input:
1
Gita
4

Expected Output:
Gita|Try Again|3
Gita|Try Again|2
Gita|Try Again|1
Gita|Get Out|0
```

**Test 4 - Mixed chances, full round-robin**
```
Input:
4
Aldo Bela Ciko Dina
3 1 2 1

Expected Output:
Aldo|Try Again|2
Bela|Get Out|0
Ciko|Try Again|1
Dina|Get Out|0
Aldo|Try Again|1
Ciko|Get Out|0
Aldo|Get Out|0
```

---

## Q4 - Greedy Diamond Heights Park Ride

### Problem Summary
Sort theme park visitors into a queue by how much money they carry, richest first.
Any visitor named "Jeff" is blacklisted and removed before the queue is built.
Visitors with equal money can appear in any order relative to each other.

### Key Data Structure
- `PriorityQueue<Visitor>` (max-heap, ordered by moneyCarried)

### Logic Walkthrough
1. Read all visitor names and money amounts
2. For each visitor, skip if blacklisted (name == "Jeff")
3. Insert remaining visitors into a max-heap by moneyCarried
4. Drain the heap into a list to produce the final sorted order
5. Print the result list

### How to Compile & Run
```bash
javac DiamondPark.java
java DiamondPark
```

### Test Cases

**Test 1 - Given sample 1**
```
Input:
4
Steve, Frederick, Lily, Amanda
3, 7, 1, 4

Expected Output:
[Frederick, Amanda, Steve, Lily]
```

**Test 2 - Given sample 2 with tied money**
```
Input:
5
Mike, Phoenix, Ellie, John, Regina
3, 2, 2, 2, 10

Expected Output:
[Regina, Mike, John, Phoenix, Ellie]
Note: John, Phoenix, Ellie are interchangeable since they carry equal money.
```

**Test 3 - Multiple Jeffs, all removed**
```
Input:
5
Anna, Jeff, Brian, Jeff, Cara
10, 50, 30, 20, 5

Expected Output:
[Brian, Anna, Cara]
```

**Test 4 - All visitors carry equal money**
```
Input:
3
Xena, Yogi, Zara
5, 5, 5

Expected Output:
[Xena, Yogi, Zara]
Note: Any order is valid since all amounts are equal.
```

**Test 5 - Single visitor**
```
Input:
1
Hana
42

Expected Output:
[Hana]
```

**Test 6 - Jeff is the only visitor**
```
Input:
1
Jeff
99

Expected Output:
[]
```

---

## File Structure

```
DS-W6.2-MARIO-EKADHARMA/
├── .vscode/
├── bin/
├── lib/
├── src/
│   ├── question1/
│   │   └── image.png
│   │   └── LoveLetter.java    # Q1: Priority queue delivery scheduler
│   ├── question2/
│   │   └── image.png
│   │   └── Respin.java        # Q2: Data structure classifier
│   ├── question3/
│   │   └── image.png
│   │   └── DoorResponse.java  # Q3: Round-robin consultation queue
│   └── question4/
│   │   └── image.png
│       └── DiamondPark.java   # Q4: Wealth-sorted visitor queue
└── README.md                  # This file
```