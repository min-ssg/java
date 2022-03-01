package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2011_암호코드 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            String N = scan.next();

            Solution2011 solution2011 = new Solution2011();
            int answer = solution2011.solve(N);
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

class Solution2011 {

    private int N;
    private int[][] dp;

    public int solve(String n) {

        if (n.charAt(0) == '0') return 0;
        this.N = n.length();
        this.dp = new int[N][2];

        if (N < 2) return 1;

        dp[0][0] = 1;

        if (n.charAt(1) != '0') {
            dp[1][0] = 1;
        }

        int temp = Integer.parseInt(n.substring(0,2));// 맨앞에 두 글자.
        if (temp >= 10 && temp <= 26) {
            dp[1][1] = 1;
        }

        for (int i = 2; i < N; i++) {

            if (n.charAt(i) != '0') {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1_000_000;
            }

            int x = Integer.parseInt(n.substring(i-1,i+1));

            if (x >= 10 && x <= 26) {
                dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % 1_000_000;
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return (dp[N-1][0] + dp[N-1][1]) % 1_000_000;
    }
}