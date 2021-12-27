package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_11728_배열합치기 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] A,B;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N];
        B = new int[M];

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }

        for (int i = 0; i < M; i++) {
            B[i] = scan.nextInt();
        }
    }

    private static void solution() {

        int p1 = 0, p2 = 0;

       while (p1 < N && p2 < M) {

           if (A[p1] <= B[p2]) {
               sb.append(A[p1++]);
           } else {
               sb.append(B[p2++]);
           }

           sb.append(' ');
       }

       for (int i = p1; i < N; i++) {
           sb.append(A[i]).append(' ');
       }

       for (int i = p2; i < M; i++) {
           sb.append(B[i]).append(' ');
       }

       System.out.println(sb.toString());
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
