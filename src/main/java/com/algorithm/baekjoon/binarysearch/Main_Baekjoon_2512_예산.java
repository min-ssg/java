package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2512_예산 {

    static FastReader scan = new FastReader();
    static int N,M;
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

        M = scan.nextInt();
        System.out.println("M = " + M);
    }

    private static boolean determination(int T) {

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.min(T, A[i]);
        }
        return sum <= M;
    }

    private static void solution() {
        Arrays.sort(A);

        int L = 0, R = 0, answer = 0;
        for (int i = 0; i < N; i++) R = Math.max(R, A[i]);

        while (L <= R) {

            int middle = (L+R)/2;

            if (determination(middle)) {
                answer = middle;
                L = middle + 1; // 최대값을 찾는 문제
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
