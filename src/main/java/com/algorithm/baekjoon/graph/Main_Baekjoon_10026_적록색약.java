package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_10026_적록색약 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            char[][] map = new char[N][N];
            char[][] xMap = new char[N][N];

            for (int i = 0; i < N; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'G') {
                        xMap[i][j] = 'R';
                        continue;
                    }
                    xMap[i][j] = map[i][j];
                }
            }

            Solution10026 solution10026 = new Solution10026();
            solution10026.solve(N, map, xMap);

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
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution10026 {

    StringBuilder sb = new StringBuilder();
    private int N, count, xCount;
    char[][] map, xMap;
    boolean[][] visited, xVisited;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public void solve(int n, char[][] map, char[][] xMap) {
        this.N = n;
        this.map = map;
        this.xMap = xMap;
        this.visited = new boolean[N][N];
        this.xVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i,j, map[i][j], 0);
                    count += 1;
                }

                if (!xVisited[i][j]) {
                    bfs(i,j,xMap[i][j], 1);
                    xCount += 1;
                }
            }
        }

        System.out.println(count + " " + xCount);
    }

    private void bfs(int i, int j, char c, int flag) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i,j));
        if (flag == 0) {
            visited[i][j] = true;
        } else {
            xVisited[i][j] = true;
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;

            for (int n = 0; n < 4; n++) {
                if (x + dx[n] < 0 || x + dx[n] >= N
                ||  y + dy[n] < 0 || y + dy[n] >= N) continue;

                if (flag == 0) {
                    if (map[x + dx[n]][y + dy[n]] != c) continue;
                    if (visited[x + dx[n]][y + dy[n]]) continue;

                    visited[x + dx[n]][y + dy[n]] = true;
                    queue.add(new Point(x + dx[n], y + dy[n]));
                } else {
                    if (xMap[x + dx[n]][y + dy[n]] != c) continue;
                    if (xVisited[x + dx[n]][y + dy[n]]) continue;

                    xVisited[x + dx[n]][y + dy[n]] = true;
                    queue.add(new Point(x + dx[n], y + dy[n]));
                }
            }
        }
    }

    private class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
