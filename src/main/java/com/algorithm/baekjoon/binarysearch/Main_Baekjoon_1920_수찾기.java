package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1920_수찾기 {

    static FastReader scan = new FastReader();
    static int N,M; // 1<= N,M <= 100000
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
            int answer = binarySearch(T);
            System.out.println(answer);
        }
    }

    private static int binarySearch(int T) {

        int L = 0;
        int R = N-1;

        while (L <= R) {

            int mid = (L + R) / 2;

            if (A[mid] == T) {
                return 1;
            } else if (A[mid] < T) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return 0;
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

        boolean hasNext() {
            return st.hasMoreElements();
        }
    }
}
