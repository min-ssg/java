package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_1261_알고스팟 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int M = scan.nextInt();
            int N = scan.nextInt();

            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            Solution1261 solution1261 = new Solution1261();
            int answer = solution1261.solve(N,M,map);
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
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution1261 {

    private int N,M;
    private Point[][] points;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.points = new Point[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                points[i][j] = new Point(i,j, map[i][j], Integer.MAX_VALUE);
            }
        }

        return bfs();
    }

    private int bfs() {

        Queue<Point> queue = new LinkedList<>();
        points[0][0].visited = 0;
        queue.add(points[0][0]);

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            int x = point.x;
            int y = point.y;
            int visited = point.visited;
            System.out.println(point);
            for (int i = 0; i < 4; i++) {

                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                Point next = points[x + dx[i]][y + dy[i]];

                if (next.wall == 1) {
                    if (next.visited > visited + 1) {
                        next.visited = visited + 1;
                        queue.add(next);
                    }
                } else {
                    if (next.visited > visited) {
                        next.visited = visited;
                        queue.add(next);
                    }
                }
            }
        }

        return points[N-1][M-1].visited;
    }

    private class Point {
        int x, y, wall, visited;

        public Point(int x, int y, int wall, int visited) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.visited = visited;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ") = " + wall + ", " + visited;
        }
    }
}
