package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15664_N과M10 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int[] A = new int[N+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Solution15664 solution15664 = new Solution15664();

            solution15664.solve(N,M,A);

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

class Solution15664 {

    private StringBuilder sb = new StringBuilder();
    private int N,M;
    private int[] A, selected;
    private int[] used = new int[10_001];

    public void solve(int n, int m, int[] a) {
        this.N = n;
        this.M = m;
        this.A = a.clone();

        selected = new int[M+1];

        Arrays.sort(A, 1, N+1);

        for (int i = 1; i <= N; i++) {
            used[A[i]]++;
        }

        recurrentFunction(1, 1);
        System.out.println(sb.toString());
    }

    private void recurrentFunction(int k, int index) {
        if (k == M+1) {

            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = index; i <= N; i++) {

            int candidate = A[i];

            if (used[candidate] == 0 || A[i-1] == candidate) continue;

            selected[k] = candidate;
            used[candidate]--;
            recurrentFunction(k+1, i);
            selected[k] = 0;
            used[candidate]++;
        }
    }
}
