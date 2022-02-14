package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1806_부분합_retry {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int S = scan.nextInt();
            int[] A = new int[N+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Solution1806 solution1806 = new Solution1806();

            int answer = solution1806.solve(N,S,A);
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

class Solution1806 {

    public int solve(int N, int S, int[] A) {
        int L = 1;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int R = 1; R <= N; R++) {

            sum += A[R];

            while (sum >= S && L <= R) {
                min = Math.min(min, R-L+1);
                sum -= A[L++];
            }
        }

        if (L == 1) {
            min = 0;
        }

        return min;
    }
}