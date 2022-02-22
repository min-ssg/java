package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2579_계단오르기 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Solution2579 solution2579 = new Solution2579(N,A);
            int answer = solution2579.solve();

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

class Solution2579 {

    private int N;
    private int[] A;
    private int[][] dp,come;

    public Solution2579(int n, int[] a) {
        this.N = n;
        this.A = a.clone();
    }

    public int solve() {

        if (N == 1) {
            return A[N];
        }

        dp = new int[N+1][2];

        dp[1][0] = 0; dp[2][0] = A[2];
        dp[1][1] = A[1]; dp[2][1] = A[1] + A[2];

        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.max(dp[i-2][0] + A[i], dp[i-2][1] + A[i]);
            dp[i][1] = dp[i-1][0] + A[i];
        }

        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }

        return Math.max(dp[N][0], dp[N][1]);
    }
}
