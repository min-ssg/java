package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_9019_DSLR {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            StringBuilder sb = new StringBuilder();
            int T = scan.nextInt();

            for (int i = 0; i < T; i++) {
                Solution9019 solution9019 = new Solution9019();
                int from = scan.nextInt();
                int to = scan.nextInt();
                String answer = solution9019.solve(from, to);
                sb.append(answer).append('\n');
            }

            System.out.println(sb.toString());
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

class Solution9019 {

    Point[] points = new Point[10000];
    private int from, to;


    Queue<Point> queue = new LinkedList<>();

    public String solve(int from, int to) {

        this.from = from;
        this.to = to;

        for (int i = 0; i < 10000; i++) {
            points[i] = new Point(i);
        }

        return bfs();
    }

    private String bfs() {

        queue.add(points[from]);

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            int x = point.x;
            String path = point.path;
            //System.out.println(x + ": " + path);
            if (x == to) return path;

            D(x,path);
            S(x,path);
            L(x,path);
            R(x,path);
        }

        return "";
    }

    private void D(int x, String path) {

        int next = x * 2;
        if (next > 9999) {
            next = next % 9999;
        }

        if (!points[next].path.isEmpty()) return;

        points[next].path = path + 'D';
        queue.add(points[next]);
    }

    private void S(int x, String path) {
        int next = x - 1;
        if (next < 0) {
            next = 9999;
        }

        if (!points[next].path.isEmpty()) return;

        points[next].path = path + 'S';
        queue.add(points[next]);
    }

    private void L(int x, String path) {
        int h = x / 1000;
        int t = x % 1000;
        int next = t * 10 + h;

        if (!points[next].path.isEmpty()) return;

        points[next].path = path + 'L';
        queue.add(points[next]);
    }

    private void R(int x, String path) {
        int h = x / 10;
        int t = x % 10;
        int next = t * 1000 + h;

        if (!points[next].path.isEmpty()) return;

        points[next].path = path + 'R';
        queue.add(points[next]);
    }

    private class Point {
        int x;
        String path;

        public Point(int x) {
            this.x = x;
            this.path = "";
        }
    }
}
