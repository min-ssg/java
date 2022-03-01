package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_10870_피보나치함수5 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // N < 20;

            Solution10870 solution10870 = new Solution10870();
            int answer = solution10870.solve(N);
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

class Solution10870 {

    private int N;
    private int[] dp;

    public int solve(int N) {
        this.N = N;
        this.dp = new int[N+1];

        if (N == 0) return 0;

        dp[1] = 1;

        int answer = fibonacci(N);

        return answer;
    }

    private int fibonacci(int n) {

        if (n < 0) return 0;
        if (dp[n] != 0) return dp[n];

        return fibonacci(n-1) + fibonacci(n-2);
    }
}
