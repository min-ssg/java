package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_15565_귀여운라이언 {

    static FastReader scan = new FastReader();
    static int N,K;
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

    private static void solution() {

        int answer = Integer.MAX_VALUE;
        int L = 0, R = 0, count = 0;

        while (L < N && A[L] == 2) {
            L++;
        }

        for (R = L; R < N; R++) {

            if (A[R] == 1) {
                count++;
            }

            if (count == K) {
                answer = Math.min(answer, R-L+1);
                L++;
                count--;
                while (L < N && A[L] == 2) {
                    L++;
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
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
