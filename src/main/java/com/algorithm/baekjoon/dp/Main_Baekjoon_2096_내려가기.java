package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_2096_내려가기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[][] A = new int[N+1][3];

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < 3; j++) {
                    A[i][j] = scan.nextInt();
                }
            }

            Solution2096 solution2096 = new Solution2096();
            solution2096.solve(N,A);
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

class Solution2096 {

    private StringBuilder sb = new StringBuilder();
    private int N;
    private int[][] A;
    private int[][][] dp;

    public void solve(int n, int[][] a) {
        this.N = n;
        this.A = a.clone();
        this.dp = new int[N+1][3][2];

        dp[1][0][0] = A[1][0]; // 최대
        dp[1][0][1] = A[1][0]; // 최소
        dp[1][1][0] = A[1][1]; // 최대
        dp[1][1][1] = A[1][1]; // 최소
        dp[1][2][0] = A[1][2]; // 최대
        dp[1][2][1] = A[1][2]; // 최소

        for (int row = 2; row <= N; row++) {

            dp[row][0][0] = Math.max(dp[row-1][0][0], dp[row-1][1][0]) + A[row][0];
            dp[row][0][1] = Math.min(dp[row-1][0][1], dp[row-1][1][1]) + A[row][0];

            dp[row][1][0] = Math.max(dp[row-1][0][0], Math.max(dp[row-1][1][0],dp[row-1][2][0])) + A[row][1];
            dp[row][1][1] = Math.min(dp[row-1][0][1], Math.min(dp[row-1][1][1],dp[row-1][2][1])) + A[row][1];

            dp[row][2][0] = Math.max(dp[row-1][1][0], dp[row-1][2][0]) + A[row][2];
            dp[row][2][1] = Math.min(dp[row-1][1][1], dp[row-1][2][1]) + A[row][2];
        }

        int max = Math.max(dp[N][0][0], Math.max(dp[N][1][0], dp[N][2][0]));
        int min = Math.min(dp[N][0][1], Math.min(dp[N][1][1], dp[N][2][1]));
        sb.append(max).append(' ').append(min);

        System.out.println(sb.toString());
    }
}