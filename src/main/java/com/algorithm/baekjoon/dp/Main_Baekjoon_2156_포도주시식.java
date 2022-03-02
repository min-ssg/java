package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 포도주 시식, 계단 오르기(2579) 비슷한 유형임.
 */
public class Main_Baekjoon_2156_포도주시식 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt(); // 1 <= N <= 10000
            int[] A = new int[N+1]; // 0 <= A[i] <= 1000
            // Integer 범위내에서 가능함.
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Solution2156 solution2156 = new Solution2156();
            int answer = solution2156.solve(N,A);
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

class Solution2156 {

    private int N, max;
    private int[] A;
    private int[][] dp;

    public int solve(int n, int[] a) {
        this.N = n;
        this.A = a.clone();
        this.dp = new int[N+1][2];

        if (N == 1) {
            return A[N];
        }

        dynamicProgramming();

        return max;
    }

    private void dynamicProgramming() {
        dp[1][0] = A[1];
        dp[2][0] = A[2];
        dp[2][1] = A[1] + A[2];

        max = dp[2][1];

        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.max(dp[i-2][0] + A[i], dp[i-2][1] + A[i]);
            dp[i][0] = Math.max(dp[i][0], Math.max(dp[i-3][0], dp[i-3][1]) + A[i]);
            dp[i][1] = dp[i-1][0] + A[i];

            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
    }
}