package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: 두 용액
 * 난이도: 골드5
 * 시간제한: 1초
 * 메모리제한: 128MB
 */
public class Main_Baekjoon_2470_두용액 {

    static int N; // 2 <= N <= 100000
    static int[] A;

    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
        close();
    }

    private static void close() {

    }


    private static void input() {

        N = scan.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {

        int bestSum = Integer.MAX_VALUE;
        int value1 = 0, value2 = 0;
        Arrays.sort(A);
        System.out.println(Arrays.toString(A));

        for (int left = 0; left < N; left++) {

            int result = binarySearch(left+1, N -1 , -A[left]);
            System.out.println("A[" + left + "] = " + A[left] + ", result = " + result);
            if (left < result -1 && Math.abs(A[left] + A[result-1]) < bestSum) {
                bestSum = Math.abs(A[left] + A[result-1]);
                value1 = A[left];
                value2 = A[result-1];
            }

            if (result < N && Math.abs(A[left] + A[result]) < bestSum) {
                bestSum = Math.abs(A[left] + A[result]);
                value1 = A[left];
                value2 = A[result];
            }
        }

        sb.append(value1).append(' ').append(value2);
        System.out.println(sb);
    }

    private static int binarySearch(int L, int R, int number) {

        int result = N;
        while (L <= R) {
            int M = (L + R) / 2;
            if (A[M] >= number) {
                R = M - 1;
                result = M;
            } else {
                L = M + 1;
            }
        }

        return result;
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }

        void close() {
            if (br == null) return;

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
