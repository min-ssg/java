package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_7576_토마토 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int M = scan.nextInt();
            int N = scan.nextInt();

            int[][] farm = new int[N][M];

            boolean isFull = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    farm[i][j] = scan.nextInt();
                    if (farm[i][j] == 0) {
                        isFull = false;
                    }
                }
            }

            int answer = 0;

            if (!isFull) {
                Solution7576 solution7576 = new Solution7576();
                answer = solution7576.solve(N,M,farm);
            }

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

class Solution7576 {

    private int N,M;
    private int[][] farm;
    private boolean[][] isMatured;

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};

    private Queue<Box> queue = new LinkedList<>();

    public int solve(int n, int m, int[][] farm) {
        this.N = n;
        this.M = m;
        this.farm = farm.clone();

        this.isMatured = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (farm[i][j] == 1) {
                    queue.add(new Box(i,j,0));
                    isMatured[i][j] = true;
                }
            }
        }

        return howLongTakeTomatoMatured();
    }

    private int howLongTakeTomatoMatured() {

        int day = 0;

        while (!queue.isEmpty()) {
            Box box = queue.poll();
            int x = box.x;
            int y = box.y;
            int k = box.k;
            day = k;
            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) {
                    continue;
                }

                if (isMatured[x + dx[i]][y + dy[i]] || farm[x + dx[i]][y + dy[i]] != 0) {
                    continue;
                }

                queue.add(new Box(x+dx[i], y+dy[i], k+1));
                farm[x+dx[i]][y+dy[i]] = 1;
                isMatured[x+dx[i]][y+dy[i]] = true;
            }
        }

        if(!isAllMatured()) {
            day = -1;
        }

        return day;
    }

    private boolean isAllMatured() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (farm[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private class Box {
        protected int x;
        protected int y;
        protected int k; // 익은 순서.

        public Box(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}