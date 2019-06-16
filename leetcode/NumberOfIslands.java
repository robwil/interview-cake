package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    // DFS approach:
    // O(N*M) time, O(N*M) space in worst case due to recursion stack

    private void markVisited(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            markVisited(grid, i - 1, j);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            markVisited(grid, i + 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            markVisited(grid, i, j - 1);
        }
        if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
            markVisited(grid, i, j + 1);
        }
    }

    public int numIslandsDfs(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    markVisited(grid, i, j);
                }
            }
        }
        return islands;
    }

    // BFS approach:
    // O(N*M) time
    // O(min(N, M)) space
    public class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslandsBfs(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    // begin BFS from position i,j
                    // any time we add to queue, we also must mark as visited to prevent infinite looping or dupe work
                    Queue<Position> q = new LinkedList<>();
                    q.offer(new Position(i, j));
                    grid[i][j] = '0';
                    while (!q.isEmpty()) {
                        Position pos = q.poll();
                        // add any non-0 neighbors to queue for further processing
                        if (pos.x + 1 < grid.length && grid[pos.x + 1][pos.y] == '1') {
                            q.offer(new Position(pos.x + 1, pos.y));
                            grid[pos.x + 1][pos.y] = '0';
                        }
                        if (pos.x - 1 >= 0 && grid[pos.x - 1][pos.y] == '1') {
                            q.offer(new Position(pos.x - 1, pos.y));
                            grid[pos.x - 1][pos.y] = '0';
                        }
                        if (pos.y + 1 < grid[pos.x].length && grid[pos.x][pos.y + 1] == '1') {
                            q.offer(new Position(pos.x, pos.y + 1));
                            grid[pos.x][pos.y + 1] = '0';
                        }
                        if (pos.y - 1 >= 0 && grid[pos.x][pos.y - 1] == '1') {
                            q.offer(new Position(pos.x, pos.y - 1));
                            grid[pos.x][pos.y - 1] = '0';
                        }
                    }
                }
            }
        }
        return islands;
    }


    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        System.out.println("Expected 1. Number of islands = " + new NumberOfIslands().numIslandsBfs(grid));
        grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };
        System.out.println("Expected 3. Number of islands = " + new NumberOfIslands().numIslandsBfs(grid));
        grid = new char[][]{
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        };
        System.out.println("Expected 1. Number of islands = " + new NumberOfIslands().numIslandsBfs(grid));
    }
}
