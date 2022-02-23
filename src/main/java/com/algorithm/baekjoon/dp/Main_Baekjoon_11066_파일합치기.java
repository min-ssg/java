package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * dp문제이지만, priorityQueue로 해보려 한다.
 */
public class Main_Baekjoon_11066_파일합치기 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            int T = scan.nextInt();

            for (int i = 0; i < T; i++) {

                int K = scan.nextInt();
                int[] P = new int[K+1];

                for (int j = 1; j <= K; j++) {
                    P[j] = scan.nextInt();
                }

                Solution11066 solution11066 = new Solution11066();
                sb.append(solution11066.solve(K,P)).append('\n');
            }

            System.out.println(sb.toString());
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

class Solution11066 {

    private int[] A;
    private int[][] sum, dp;

    public int solve(int k, int[] p) {

        init(k, p);

        for (int len = 2; len <= k; len++) {
            for (int i = 1; i <= k-len+1; i++) { // i 시작점, j 끝점
                int j = i + len - 1;

                dp[i][j] = Integer.MAX_VALUE;

                for (int K = i; K < j; K++) { // k 1부터 끝점 앞까지
                    dp[i][j] = Math.min(dp[i][j], dp[i][K] + dp[K+1][j] + sum[i][j]);
                }
            }
        }

        return dp[1][k];
    }

    private void init(int k, int[] p) {
        A = p.clone();
        sum = new int[k+1][k+1];
        dp = new int[k+1][k+1];

        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= k; j++) {
                sum[i][j] = sum[i][j-1] + A[j];
            }
        }
    }
}
