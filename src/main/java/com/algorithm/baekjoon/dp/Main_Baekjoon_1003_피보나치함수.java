package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1003_피보나치함수 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int T = scan.nextInt(); // N <= 40
            int[] A = new int[T];
            for (int i = 0; i < T; i++) {
                A[i] = scan.nextInt();
            }

            Solution1003 solution1003 = new Solution1003();
            solution1003.solve(T,A);


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

class Solution1003 {

    private StringBuilder sb = new StringBuilder();
    private int T;
    private int[] A;
    private int[][] fb;
    public void solve(int t, int[] a) {
        init(t,a);

        assignmentFibonacci();
        for (int n : A) {
            sb.append(fb[n][0]).append(' ').append(fb[n][1]).append('\n');
        }

        System.out.println(sb.toString());
    }

    private void assignmentFibonacci() {
        fb = new int[40+1][2];
        fb[0][0] = 1; fb[0][1] = 0;
        fb[1][0] = 0; fb[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            fb[i][0] = fb[i-1][0] + fb[i-2][0];
            fb[i][1] = fb[i-1][1] + fb[i-2][1];
        }
    }

    private void init(int t, int[] a) {
        this.T = t;
        this.A = a.clone();
    }
}
