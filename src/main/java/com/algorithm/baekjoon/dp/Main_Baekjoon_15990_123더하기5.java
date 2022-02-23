package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15990_123더하기5 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution15990 solution15990 = new Solution15990();
            solution15990.solve(N,A);

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
            while ( st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution15990 {

    private StringBuilder sb = new StringBuilder();
    private int N;
    private int[] A;

    private int[][] dp = new int[100_001][4];

    public void solve(int n, int[] a) {

        this.N = n;
        this.A = a.clone();

        for (int target : A) {
            sb.append(subSolve(target)).append('\n');
        }

        System.out.println(sb.toString());
        return;
    }

    private int subSolve(int target) {

        int mod = 1_000_000_009;

        dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0;
        dp[2][1] = 0; dp[2][2] = 1; dp[2][3] = 0;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        for (int i = 4; i <= target; i++) {
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % mod;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % mod;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % mod;
        }

        return ((dp[target][1] + dp[target][2]) % mod + dp[target][3]) % mod;
    }
}
