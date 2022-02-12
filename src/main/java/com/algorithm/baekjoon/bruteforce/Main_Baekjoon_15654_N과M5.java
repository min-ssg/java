package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15654_N과M5 {

    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] A, selected;
    static boolean[] used;

    public static void main(String[] args) {

        input();
        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private static void recurrentFunction(int k) {
        if (k == M+1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i]) continue;

            int candidate = A[i];

            selected[k] = candidate;
            used[i] = true;
            recurrentFunction(k+1);
            selected[k] = 0;
            used[i] = false;
        }
    }

    private static void input() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            N = scan.nextInt();
            M = scan.nextInt();
            A = new int[N+1];
            used = new boolean[N+1];
            selected = new int[M+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Arrays.sort(A, 1, N+1);
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
