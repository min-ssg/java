package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_18353_병사배치하기 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] S = new int[N+1];

            for (int i = 1; i <= N; i++) {
                S[i] = scan.nextInt();
            }

            Solution18353 solution18353 = new Solution18353(N,S);
            int max = solution18353.solve();
            System.out.println(max);
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
            while( st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution18353 {

    private int N;
    private int[] S, dp;
    public Solution18353(int N, int[] S) {
        this.N = N;
        this.S = S.clone();
        this.dp = new int[N+1];
    }


    public int solve() {
        Arrays.fill(dp,1);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (S[i] < S[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = Arrays.stream(dp).max().getAsInt();

        return N - max;
    }
}
