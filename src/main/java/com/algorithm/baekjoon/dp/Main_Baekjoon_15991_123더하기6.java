package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_15991_123더하기6 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution15991 solution15991 = new Solution15991(N,A);
            solution15991.solve();

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

class Solution15991 {

    private StringBuilder sb = new StringBuilder();
    private int N;
    private int[] A, dp;

    public Solution15991(int n, int[] a) {
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

    private int subSolve(int n) {
        return dp[n];
    }

    private void predefined() {
        int mod = 1_000_000_009;
        dp = new int[100_001];
        dp[0] = 1; dp[1] = 1;
        dp[2] = 2; dp[3] = 2;
        dp[4] = 3; dp[5] = 3;

        for (int i = 6; i <= 100_000; i++) {
            if (i % 2 == 0) { // 짝수
                dp[i] = dp[i-6] % mod;
                dp[i] = (dp[i] + dp[i-4]) % mod;
                dp[i] = (dp[i] + dp[i-2]) % mod;
            } else {
                dp[i] = dp[i-1];
            }
        }
    }
}
