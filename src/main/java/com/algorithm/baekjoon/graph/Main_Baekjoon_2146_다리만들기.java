package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_2146_다리만들기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution2146 solution2146 = new Solution2146();
            int answer = solution2146.solve(N, map);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution2146 {

    StringBuilder sb = new StringBuilder();
    private int N;
    int[][] map, island;
    boolean[][] visited;

    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    public int solve(int n, int[][] map) {
        this.N = n;
        this.map = map;
        this.island = new int[N][N];
        this.visited = new boolean[N][N];

        int land = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;

                dfs(new Point(i,j), land++);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < land; i++) {
            this.visited = new boolean[N][N];
            int bfsResult = bfs(i);
            result = Math.min(result, bfsResult);
        }

        return result;
    }

    private int bfs(int value) {

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (island[i][j] == value) {
                    visited[i][j] = true;
                    queue.add(new Point(i,j,0));
                }
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {

            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            int distance = point.distance;

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= N) continue;

                if (island[x + dx[i]][y + dy[i]] == value) continue;
                if (visited[x + dx[i]][y + dy[i]]) continue;

                if (island[x + dx[i]][y + dy[i]] != 0 && island[x + dx[i]][y + dy[i]] != value) {
                    return distance;
                }

                visited[x + dx[i]][y + dy[i]] = true;
                queue.add(new Point(x + dx[i], y + dy[i], distance + 1));
            }
        }

        return -1;
    }

    private void dfs(Point point, int value) {

        int x = point.x;
        int y = point.y;
        visited[x][y] = true;
        island[x][y] = value;

        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= N)  continue;

            if (map[x + dx[i]][y + dy[i]] == 0) continue;
            if (visited[x + dx[i]][y + dy[i]]) continue;

            dfs(new Point(x + dx[i], y + dy[i]), value);
        }
    }

    private class Point {
        int x, y, distance;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
