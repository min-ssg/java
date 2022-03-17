package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_13913_숨바꼭질4 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int K = scan.nextInt();

            Solution13913 solution13913 = new Solution13913();
            solution13913.solve(N,K);
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

class Solution13913 {

    StringBuilder sb = new StringBuilder();
    private int N,K;
    Point[] points;

    public void solve(int n, int k) {
        this.N = n;
        this.K = k;

        this.points = new Point[100_001];

        for (int i = 0; i <= 100_000; i++) {
            points[i] = new Point(i,-1,-1);
        }

        Point point = bfs();

        findPath(point);
        System.out.println(sb.toString());
    }

    private void findPath(Point point) {
        if (point.x == N) {
            sb.append(point.x).append(' ');
            return;
        }
        findPath(points[point.parent]);
        sb.append(point.x).append(' ');
    }

    private Point bfs() {

        Queue<Point> queue = new LinkedList<>();
        points[N].distance = 0;
        queue.add(points[N]);

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == K) {
                sb.append(point.distance).append('\n');
                return point;
            }

            if (point.x * 2 <= 100_000 && points[point.x * 2].distance == -1) {
                points[point.x * 2].distance = point.distance + 1;
                points[point.x * 2].parent = point.x;

                queue.add(points[point.x * 2]);
            }

            if (point.x + 1 <= 100_000 && points[point.x + 1].distance == -1) {
                points[point.x + 1].distance = point.distance + 1;
                points[point.x + 1].parent = point.x;

                queue.add(points[point.x + 1]);
            }

            if (point.x - 1 >= 0 && points[point.x - 1].distance == -1) {
                points[point.x - 1].distance = point.distance + 1;
                points[point.x - 1].parent = point.x;

                queue.add(points[point.x - 1]);
            }
        }

        return null;
    }

    private class Point {
        int x, parent;
        long distance;

        public Point(int x, int parent, long distance) {
            this.x = x;
            this.parent = parent;
            this.distance = distance;
        }
    }
}
