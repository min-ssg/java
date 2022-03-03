package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 스티커, 이전의 계단오르기, 포도주시식 문제와 같이 해당 시점에서 올 수 있는 경우,
 * 그 중 값이 가장 높은 값과 그리고 후보군이 될 수 있는 값을 다이나믹프로그래밍으로
 * 이용해서 푸는 문제.
 */
public class Main_Baekjoon_9465_스티커 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int T = scan.nextInt();

            for (int i = 1; i <= T; i++) {
                int N = scan.nextInt();
                int[][] A = new int[N+1][2];

                for (int j = 1; j <= N; j++) {
                    A[j][0] = scan.nextInt();
                }

                for (int j = 1; j <= N; j++) {
                    A[j][1] = scan.nextInt();
                }

                Solution9465 solution9465 = new Solution9465();
                int answer = solution9465.solve(N, A);
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

class Solution9465 {

    private int[][] dp;

    public int solve(int n, int[][] a) {

        dp = new int[n+1][2];
        dp[1][0] = a[1][0];
        dp[1][1] = a[1][1];

        if (n == 1) return Math.max(a[1][0], a[1][1]);

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][1], Math.max(dp[i-2][0], dp[i-2][1])) + a[i][0];
            dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-2][0], dp[i-2][1])) + a[i][1];
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return Math.max(dp[n][0], dp[n][1]);
    }
}