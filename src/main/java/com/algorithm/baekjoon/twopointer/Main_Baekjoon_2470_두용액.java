package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2470_두용액 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N; // 2<= N <= 100_000
    static int[] A;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {

        Arrays.sort(A);
        int v1 = 0, v2 = 0;
        int L = 0, R = N-1;
        int min = Integer.MAX_VALUE;
        while (L < R) {
            int mix = A[L] + A[R];

            if (min > Math.abs(mix)) {
                min = Math.abs(mix);
                v1 = A[L];
                v2 = A[R];
            }

            if (mix < 0) {
                L++;
            } else if (mix > 0) {
                R--;
            }
        }
        sb.append(v1).append(' ').append(v2);
        System.out.println(sb);
    }

    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
