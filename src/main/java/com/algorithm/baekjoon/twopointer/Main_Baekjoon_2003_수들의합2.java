package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_2003_수들의합2 {

    static FastReader scan = new FastReader();
    static int N,M;
    static int[] A;


    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        M = scan.nextInt();

        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {
        int answer = 0, sum = 0;
        for (int L = 0, R = 0; L < N; L++) {

            while (R < N && sum < M) {
                sum += A[R++];
            }

            if (sum == M) {
                answer += 1;
            }

            sum -= A[L];
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
