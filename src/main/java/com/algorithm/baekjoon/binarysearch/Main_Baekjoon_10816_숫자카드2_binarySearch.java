package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_Baekjoon_10816_숫자카드2_binarySearch {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
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
    }

    private static void solution() {

        Arrays.sort(A);

        for (int i = 0; i < M; i++) {
            int T = scan.nextInt();

            int upper = upperBound(0, N-1, T);
            int lower = lowerBound(0, N -1, T);
            System.out.println("lower = " + lower);
            System.out.println("upper = " + upper);
            sb.append(upper - lower).append(' ');
        }

        System.out.println(sb);
    }

    private static int lowerBound(int L, int R, int T) {

        int answer = R+1;

        while (L <= R) {
            int middle = (L + R) / 2;
            if (A[middle] >= T) {
                answer = middle;
                R = middle - 1;
            } else {
                L = middle + 1;
            }
        }

        return answer;
    }

    private static int upperBound(int L, int R, int T) {

        int answer = R+1;

        while (L <= R) {
            int middle = (L + R) / 2;
            if (A[middle] > T) {
                answer = middle;
                R = middle - 1;
            } else {
                L = middle + 1;
            }
        }

        return answer;
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
