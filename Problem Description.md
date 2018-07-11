## Problem Description

You have a map of an archipelago, and you want to calculate the size of islands in the map. So you decided to write a program for the calculation.

### Input
A 2D grid of `~` and `x` characters, where `~` stands for sea and `x` for land. Each island is a 4-directionally connected group of `x`s.

### Output: 

1. The size of the island that contains position `(0, 3)`.
2. The size of the island that contains position `(4, 5)`.
3. The size of the island that contains position `(7, 2)`.
4. "SAME" if the position `(5, 5)` and `(3, 6)` belong to the same island, "DIFFERENT" otherwise.
4. "SAME" if the position `(2, 8)` and `(7, 8)` belong to the same island, "DIFFERENT" otherwise.
4. "SAME" if the position `(9, 2)` and `(4, 0)` belong to the same island, "DIFFERENT" otherwise.
7. The number of islands in the map.
8. The size of the largest island.

(For test input 1 through 6, if one of the given position does not belong to any island, output 'NO ISLAND'. All the given positions are row-major pairs of zero-based integers)

### Test Input:
```
~~~xx~~~~~
~~~xx~~x~~
~~~~~x~~~~
~~~~xxx~~~
x~~~xxx~xx
xx~~~x~x~~
~~~~~~~x~~
~~~xx~~x~~
~~~xxxx~~x
~~x~~~~~~~
```

### Test Output:

```
Output #1: 4
Output #2: 8
Output #3: NO ISLAND
Output #4: SAME
Output #5: NO ISLAND
Output #6: DIFFERENT
Output #7: 9
Output #8: 8
```
