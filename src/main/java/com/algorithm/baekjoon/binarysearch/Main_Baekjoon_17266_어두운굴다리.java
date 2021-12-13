package com.algorithm.baekjoon.binarysearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_17266_어두운굴다리 {
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
        A = new int[M];

        for (int i = 0; i < M; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static boolean determination(int height) {
        int last = 0;

        for (int i = 0; i < M; i++) {

            if (A[i] - height <= last) {
                last = A[i] + height;
                continue;
            }

            return false;
        }

        return last >= N;
    }

    private static void solution() {

        int L = 1;
        int R = 100_000;
        int answer = 0;

        while (L<=R) {
            int middle = (L+R)/2;

            if (determination(middle)) {
                System.out.println("middle = " + middle);
                answer = middle;
                R = middle - 1;
            } else {
                L = middle + 1;
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
