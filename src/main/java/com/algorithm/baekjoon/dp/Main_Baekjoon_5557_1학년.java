package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_5557_1학년 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            
            int N = scan.nextInt(); // 3 <= N <= 100
            int[] A = new int[N+1];
            
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Solution5557 solution5557 = new Solution5557();
            long answer = solution5557.solve(N,A);
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

class Solution5557 {

    private int N, count;
    private int[] A;
    private long[][] dp;
    private Queue<Integer> queue = new LinkedList<>();

    public long solve(int n, int[] a) {
        this.N = n;
        this.A = a.clone();
        this.dp = new long[N+1][21];

        dp[1][A[1]] = 1;
        for (int i = 2; i < N; i++) {

            for (int prev = 0; prev <= 20; prev++) {
                for (int cur : new int[]{prev - A[i], prev + A[i]}) {
                    if (cur < 0 || cur > 20) continue;
                    dp[i][cur] += dp[i-1][prev];
                }
            }
        }

        return dp[N-1][A[N]];
    }
}
