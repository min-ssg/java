package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2688_줄어들지않아 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int T = scan.nextInt();

            Solution2688.dynamicProgramming();
            Solution2688 solution2688 = new Solution2688();
            for (int i = 1; i <= T; i++) {
                int N = scan.nextInt();
                long answer = solution2688.solve(N);
                System.out.println(answer);
            }
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

class Solution2688 {

    private static long[][] dp = new long[64+1][10];

    public static void dynamicProgramming() {

        for (int col = 0; col < 10; col++) {
            dp[1][col] = 1;
        }

        long temp = 10L;

        for (int row = 2; row <= 64; row++) {
            dp[row][0] = temp;

            for (int col = 1; col < 10; col++) {
                dp[row][col] = dp[row][col-1] - dp[row-1][col-1];
                temp += dp[row][col];
            }
        }
    }

    public static long solve(int n) {
        return Arrays.stream(dp[n]).sum();
    }
}