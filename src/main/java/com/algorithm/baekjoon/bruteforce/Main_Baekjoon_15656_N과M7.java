package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15656_N과M7 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int[] A = new int[N+1];
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Arrays.sort(A, 1, N+1);

            Solution15656 solution15656 = new Solution15656();

            solution15656.solution(N,M,A);

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

class Solution15656 {

    private StringBuilder sb = new StringBuilder();
    private int N,M;
    private int[] A, selected;

    public void solution(int n, int m, int[] a) {
        this.N = n;
        this.M = m;
        this.A = a.clone();

        selected = new int[M+1];

        recurrentFunction(1);

        System.out.println(sb.toString());
    }

    private void recurrentFunction(int k) {
        if (k == M+1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            selected[k] = A[i];
            recurrentFunction(k+1);
            selected[k] = 0;
        }
    }
}
