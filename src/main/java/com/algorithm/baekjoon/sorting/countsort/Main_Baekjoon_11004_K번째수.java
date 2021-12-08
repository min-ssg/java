package com.algorithm.baekjoon.sorting.countsort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_11004_K번째수 {

    static FastReader scan = new FastReader();
    static int N, K;
    static final int DIVISOR = 1<<16;
    static int[][] A;

    static int[] COUNT;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        K = scan.nextInt();

        A = new int[N][2];

        for (int i = 0; i < N; i++) {
            int a = scan.nextInt() + 1_000_000_000;
            int Q = a / DIVISOR;
            int R = a % DIVISOR;

            A[i][0] = Q;
            A[i][1] = R;
        }
    }

    private static void solution() {

        int[] division = firstStep();
        int quotient = division[0];
        int remainCount = division[1];
        System.out.println("remainCount = " + remainCount);
        int remainder = secondStep(quotient, remainCount);

        int answer = quotient * DIVISOR + remainder - 1_000_000_000;
        System.out.println(answer);
    }

    private static int[] firstStep() {

        initCountArray(0);

        return getFirstStepResult();
    }

    private static void initCountArray(int mode) {

        COUNT = new int[DIVISOR];

        for (int i = 0; i < N; i++) {
            int Q = A[i][mode];
            COUNT[Q] += 1;
        }
    }

    private static int[] getFirstStepResult() {

        int quotient = 0;
        int remainCount = 0;

        if (COUNT[0] >= K) {
            return new int[]{quotient, K};
        }

        for (int i = 1; i < DIVISOR; i++) {

            COUNT[i] += COUNT[i-1];

            if (COUNT[i] >= K) {
                quotient = i;
                remainCount = K - COUNT[i-1];
                break;
            }
        }
        return new int[]{quotient, remainCount};
    }

    private static int secondStep(int quotient, int remainCount) {

        COUNT = new int[DIVISOR];

        for (int i = 0; i < N; i++) {
            int Q = A[i][0];

            if (Q == quotient) {
                int R = A[i][1];
                COUNT[R] += 1;
            }
        }

        int remainder = 0;

        for (int i = 1; i < DIVISOR; i++) {
            COUNT[i] += COUNT[i-1];
            if (COUNT[i] >= remainCount) {
                remainder = i;
                break;
            }
        }
        
        return remainder;
    }

    private static class FastReader {
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
