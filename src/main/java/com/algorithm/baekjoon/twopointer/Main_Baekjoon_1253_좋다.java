package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1253_좋다 {

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

    private static int isMatch(int index) {
        int T = A[index];

        int L = 0;
        int R = N-1;

        while (L<R) {
            if (L == index) {
                L++;
                continue;
            }

            if (R == index) {
                R--;
                continue;
            }

            if (A[L] + A[R] < T) {
                L++;
            } else if (A[L] + A[R] > T) {
                R--;
            } else {
                return 1;
            }
        }

        return 0;
    }

    private static void solution() {
        Arrays.sort(A);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += isMatch(i);
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
            while (st==null || !st.hasMoreElements()) {
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
