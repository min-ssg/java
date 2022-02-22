package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_10971_외판원순환2 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[][] W = new int[N+1][N+1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    W[i][j] = scan.nextInt();
                }
            }

            Solution10971 solution10971 = new Solution10971(N,W);
            solution10971.solve();

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

class Solution10971 {

    private StringBuilder sb = new StringBuilder();
    private final int N;
    private final int[][] W;
    private int[] selected;
    private boolean[] visited;

    private int min = Integer.MAX_VALUE;

    public Solution10971(int N, int[][] W) {
        this.N = N;
        this.W = W.clone();
    }


    public void solve() {
        selected = new int[N+1];
        visited = new boolean[N+1];
        recurrentFunction(1);
        System.out.println(sb.toString());
        System.out.println(min);
    }

    private void recurrentFunction(int k) {
        if (k == N+1 && W[selected[N]][selected[1]] != 0) {
            appendPath();
            int value = getValue();
            min = Math.min(min, value);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i] || (k-1 > 0 && W[selected[k-1]][i] == 0)) continue;

            selected[k] = i;
            visited[i] = true;
            recurrentFunction(k+1);
            selected[k] = 0;
            visited[i] = false;
        }
    }

    private int getValue() {
        int sum = 0;
        for (int i = 1; i < N; i++) {
            sum += W[selected[i]][selected[i+1]];
        }

        return sum + W[selected[N]][selected[1]];
    }

    private void appendPath() {
        for (int i = 1; i <= N; i++) {
            sb.append(selected[i]).append(' ');
        }
        sb.append('\n');
    }
}
