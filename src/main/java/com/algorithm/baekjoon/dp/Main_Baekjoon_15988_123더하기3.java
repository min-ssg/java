package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_15988_123더하기3 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution15988 solution15988 = new Solution15988(N,A);
            solution15988.solve();

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

class Solution15988 {

    private StringBuilder sb = new StringBuilder();
    private int N;
    private int[] A;
    private long[] dp;

    public Solution15988(int n, int[] a) {
        this.N = n;
        this.A = a.clone();
    }

    public void solve() {

        predefined();

        for (int n : A) {
            sb.append(subSolve(n)).append('\n');
        }

        System.out.println(sb.toString());
    }

    private void predefined() {
        int n = 1_000_000;
        int mod = 1_000_000_009;

        dp = new long[n+1];

        dp[1] = 1; dp[2] = 2; dp[3] = 4;

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod + dp[i - 3] % mod) % mod;
        }
    }

    private int subSolve(int n) {
        if (n < 3) {
            return n;
        }

        return (int) dp[n];
    }
}
