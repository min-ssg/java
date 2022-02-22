package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15666_N과M12 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution15666 solution15666 = new Solution15666();
            solution15666.solve(N,M,A);

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

class Solution15666 {

    private StringBuilder sb = new StringBuilder();

    private int N,M;
    private int[] numbers, selected;

    public void solve(int n, int m, int[] a) {
        this.N = n;
        this.M = m;
        selected = new int[M+1];

        numbers = duplicateRemoveArray(a);

        recurrentFunction(1, 0);

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

        for (int i = index; i < numbers.length; i++) {
            int candidate = numbers[i];

            selected[k] = candidate;
            recurrentFunction(k+1, i);
            selected[k] = 0;
        }
    }

    private int[] duplicateRemoveArray(int[] a) {
        return Arrays.stream(a).distinct().sorted().toArray();
    }
}
