package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1654_랜선자르기 {

    static FastReader scan = new FastReader();
    static int N,K;
    static int[] A;

    public static void main(String[] args) {
         input();
         solution();
    }

    private static void input() {
        K = scan.nextInt();
        N = scan.nextInt();

        A = new int[K];

        for (int i = 0; i < K; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static boolean determination(int length) {

        int count = 0;
        for (int lan : A) {
            count += lan / length;
        }

        return count >= N;
    }

    private static void solution() {

        long left = 1;
        long right = (1 << 31) - 1; // 2147483647
        int answer = 0;
        while (left <= right) {

            long middle = (left + right) / 2; // 문제의 구간...
            if (determination((int) middle)) {
                answer = (int) middle;
                left = middle + 1;
            } else {
                right = middle - 1;
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
