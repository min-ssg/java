package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2473_세용액 {

    static FastReader scan = new FastReader();
    static int N;
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
        long min = 3_000_000_000L;
        int v1 = 0,v2 = 0,v3 = 0;
        for (int i = 0; i < N - 2; i++) {
            int L = i+1, R = N - 1;
            int target = -A[i];

            while (L < R) {
                long mix = Math.abs(-(long)target + A[L] + A[R]);
                if (min > mix) {
                    min = mix;
                    v1 = -target;
                    v2 = A[L];
                    v3 = A[R];
                }

                if (A[L] + A[R] > target) {
                    R--;
                } else {
                    L++;
                }
            }
        }

        System.out.println(v1 + " " + v2 + " " + v3);
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
