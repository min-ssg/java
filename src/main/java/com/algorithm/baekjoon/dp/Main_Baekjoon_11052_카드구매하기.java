package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_11052_카드구매하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[] A = new int[N+1];
            
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Solution11052 solution11052 = new Solution11052();
            int answer = solution11052.solve(N, A);
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

class Solution11052 {

    private int N;
    private int[] A;
    private int[] dp;

    public int solve(int n, int[] a) {
        init(n,a);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i-j] + A[j]);
            }
        }

        return dp[N];
    }

    private void init(int n, int[] a) {
        this.N = n;
        this.A = a.clone();
        this.dp = new int[N+1];
    }


}
