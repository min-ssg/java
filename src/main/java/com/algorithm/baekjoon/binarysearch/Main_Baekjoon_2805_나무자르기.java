package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_2805_나무자르기 {

    static FastReader scan = new FastReader();

    static int N,M; // 1<=N<=1_000_000, 1<=M<=2_000_000_000
    static int[] A; // Array

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

    private static boolean determination(int height) {
        // height로 나무들을 잘랐을 때, M만큼 얻을 수 있다면 true, 없으면 false를 return
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] > height) {
                sum += A[i] - height;
            }
        }
        return sum >= M;
    }

    private static void solution() {
        long left = 0;
        long right = 2_000_000_000; // 20억
        long answer = 0;

        while (left <= right) {
            int middle = (int) ((left+right)/2);
            if (determination(middle)) {
                answer = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        System.out.println(answer);
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br  = new BufferedReader(new InputStreamReader(System.in));
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
