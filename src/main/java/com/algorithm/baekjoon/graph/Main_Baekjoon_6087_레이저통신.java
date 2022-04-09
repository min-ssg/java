package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_6087_레이저통신 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int W = scan.nextInt();
            int H = scan.nextInt();

            char[][] map = new char[H][W];
            for (int i = 0; i < H; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            Solution6087 solution6087 = new Solution6087();
            int answer = solution6087.solve(W,H,map);
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

class Solution6087 {

    private int W,H, min = Integer.MAX_VALUE;
    char[][] map;
    int[][] visited;

    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    public int solve(int w, int h, char[][] map) {
        this.W = w;
        this.H = h;
        this.map = map;

        this.visited = new int[H][W];
//        for (int i = 0; i < H; i++) {
//            Arrays.fill(visited[i], -1);
//        }

        return bfs();
    }

    private int bfs() {

        int startX = 0, startY = 0, endX = 0, endY = 0, check = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') {
                    if (check == 0) {
                        startX = i;
                        startY = j;
                        check++;
                    } else {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }

        Queue<Point> queue = new LinkedList<>();
        visited[startX][startY] = 1;
        queue.add(new Point(startX, startY, 0, -1)); // direct = -1 인경우는 C 시작점인경우.

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            int direct = point.direct;
            int mirrorCount = point.mirrorCount;

            if (x == endX && y == endY) {
                min = Math.min(min, mirrorCount);
                continue;
            }

            for (int i = 0; i < 4; i++) {

                if (x + dx[i] < 0 || x + dx[i] >= H
                ||  y + dy[i] < 0 || y + dy[i] >= W) continue;

                if (map[x + dx[i]][y + dy[i]] == '*') continue;

                int mc = mirrorCount;
                if (direct != -1 && direct != i) {
                    mc += 1;
                }

                if (x + dx[i] == 3 && y + dy[i] == 7) {
                    System.out.println("mirrorCount = " + mirrorCount);
                    System.out.println("direct = " + direct + " || " + x + ", " + y + " = " + mc);
                    System.out.println((x + dx[i]) + ", " + (y + dy[i]) + " = " + visited[x + dx[i]][y + dy[i]]);
                }

                if (visited[x + dx[i]][y + dy[i]] == 0 || visited[x + dx[i]][y + dy[i]] >= mc) {
                    visited[x + dx[i]][y + dy[i]] = mc;
                    queue.add(new Point(x + dx[i], y + dy[i], mc, i));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(visited[i][j]).append('\t');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());


        return min;
    }

    private class Point {
        int x, y;
        int mirrorCount, direct;

        public Point(int x, int y, int mirrorCount, int direct) {
            this.x = x;
            this.y = y;
            this.mirrorCount = mirrorCount;
            this.direct = direct;
        }
    }
}
