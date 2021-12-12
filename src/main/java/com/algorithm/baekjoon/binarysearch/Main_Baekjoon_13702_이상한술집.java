package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_13702_이상한술집 {

    static FastReader scan = new FastReader();
    static int N,K; // N<=10000, K <= 1_000_000
    static int[] A;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        K = scan.nextInt();

        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static boolean determination(int T) {

        int count = 0;
        for (int i = 0; i < N; i++) {
            count += A[i] / T;
        }

        return count >= K;
    }

    private static void solution() {
        long L = 0;
        long R = (1<<31) - 1;
        int answer = 0;
        while (L<=R) {
            int middle = (int) ((L+R)/2);

            if (determination(middle)) {
                answer = middle;
                L = middle + 1;
            } else {
                R = middle - 1;
            }
        }

        System.out.println(answer);
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
