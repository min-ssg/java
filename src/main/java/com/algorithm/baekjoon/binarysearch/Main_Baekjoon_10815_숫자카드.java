package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 제목: 숫자카드
 * 난이도: 실버4
 * 시간제한: 2초
 * 메모리제한: 256MB
 */
public class Main_Baekjoon_10815_숫자카드 {

    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();
    static int N, M; // 1<= N,M <= 500000
    static int[] A;
    static int[] T; // Target;

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
        T = new int[M];
        for (int i = 0; i < M; i++) {
            T[i] = scan.nextInt();
        }
    }

    private static void solution() {

        Arrays.sort(A);

        for (int i = 0; i < M; i++) {
            int result = binarySearch(T[i]);
            sb.append(result);
            if (i != M-1) {
                sb.append(' ');
            }
        }

        System.out.println(sb);
    }

    private static int binarySearch(int target) {

        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (A[middle] == target) {
                return 1;
            } else if (A[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return 0;
    }

    private static class FastReader {

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
