package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_6236_용돈관리 {

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

    private static boolean determination(int T) {

        int count = 1, money = T;
        for (int i = 0; i < N; i++) {
            if (money - A[i] >= 0) {
                money -= A[i];
            } else {
                count++;
                money = T;
            }
        }

        return count <= M;
    }

    private static void solution() {

        int L = Arrays.stream(A).max().getAsInt();
        int R = 1_000_000_000, answer = 0;

        while (L<=R) {
            int middle = (L+R)/2;

            if (determination(middle)) {
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
