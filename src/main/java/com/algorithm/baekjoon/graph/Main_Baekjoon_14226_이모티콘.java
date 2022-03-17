package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_14226_이모티콘 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int S = scan.nextInt();

            Solution14226 solution14226 = new Solution14226();
            int answer = solution14226.solve(S);
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

class Solution14226 {

    private boolean[][] visited;

    private static final int MAX_SIZE = 1_000;

    public int solve(int s) {

        this.visited = new boolean[MAX_SIZE+1][MAX_SIZE+1];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1,0,0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == s) {
                return point.time;
            }

            if (point.x > 0 && point.x < MAX_SIZE) {
                if (!visited[point.x][point.x]) {
                    visited[point.x][point.x] = true;
                    queue.add(new Point(point.x, point.x, point.time + 1));
                }

                if (!visited[point.x - 1][point.clipboard]) {
                    visited[point.x - 1][point.clipboard] = true;
                    queue.add(new Point(point.x - 1, point.clipboard, point.time + 1));
                }
            }
            if (point.clipboard > 0 && point.x + point.clipboard < MAX_SIZE) {
                if (!visited[point.x + point.clipboard][point.clipboard]) {
                    visited[point.x + point.clipboard][point.clipboard] = true;
                    queue.add(new Point(point.x + point.clipboard, point.clipboard, point.time + 1));
                }
            }
        }
        return 0;
    }

    private class Point {
        int x, clipboard, time;

        public Point(int x, int clipboard, int time) {
            this.x = x;
            this.clipboard = clipboard;
            this.time = time;
        }
    }
}