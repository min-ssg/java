package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;

public class Main_Baekjoon_16928_뱀과사다리게임 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int M = scan.nextInt();

            int[][] x = new int[N + M][2];

            for (int i = 0; i < N + M; i++) {
                x[i][0] = scan.nextInt();
                x[i][1] = scan.nextInt();
            }

            Solution16928 solution16928 = new Solution16928();
            int answer = solution16928.solve(N,M,x);
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

class Solution16928 {

    private int N,M;
    Point[] points = new Point[101];

    public int solve(int n, int m, int[][] x) {
        this.N = n;
        this.M = m;

        for (int i = 1; i <= 100; i++) {
            points[i] = new Point(i, -1, "NONE");
        }

        for (int i = 0; i < N + M; i++) {
            if (x[i][0] < x[i][1]) {
                points[x[i][0]].type = "사다리";
            } else {
                points[x[i][0]].type = "뱀";
            }
            points[x[i][0]].to = x[i][1];
        }

        return bfs();
    }

    private int bfs() {

        StringBuilder sb = new StringBuilder();
        Queue<Point> queue = new LinkedList<>();

        Point p = points[1];
        p.distance = 0;
        queue.add(p);

        while (!p.isEmpty()) {
            p = points[p.to];
            p.distance = 0;
            queue.add(p);
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int from = point.from;
            int distance = point.distance;

            for (int i = 1; i <= 6; i++) {
                if (from + i > 100) continue;
                Point next = points[from + i];

                if (next.distance != -1) continue;

                if (!next.isEmpty()) {
                    if (points[next.to].distance == -1) {
                        points[next.to].distance = distance + 1;
                        queue.add(points[next.to]);
                    }
                    continue;
                }

                next.distance = distance + 1;
                queue.add(next);
            }
        }

        for (int i = 1; i <= 100; i++) {
            sb.append('[').append(i).append(',').append(points[i].distance).append(']').append(' ');
        }
        System.out.println(sb.toString());

        return points[100].distance;
    }

    private class Point {
        String type;
        int from, to, distance;
        boolean visited;

        public Point(int from, int distance, String type) {
            this.from = from;
            this.distance = distance;
            this.type = type;
        }

        public boolean isEmpty() {
            return "NONE".equals(type);
        }
    }
}
