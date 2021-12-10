package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1764_듣보잡 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M; // 1<= N,M <= 500000
    static String[] A, B;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        M = scan.nextInt();

        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextLine();
        }
        B = new String[M];
        for (int i = 0; i < M; i++) {
            B[i] = scan.nextLine();
        }
    }

    private static boolean findName(String name) {
        int L = 0;
        int R = N - 1;

        while (L <= R) {
            int middle = (L + R) / 2;

            if (A[middle].equals(name)) {
                sb.append(name).append('\n');
                return true;
            } else if (A[middle].compareTo(name) < 1) {
                L = middle + 1;
            } else {
                R = middle - 1;
            }
        }

        return false;
    }

    private static void solution() {
        Arrays.sort(A);
        Arrays.sort(B);

        int count = 0;
        for (int i = 0; i < M; i++) {
            String name = B[i];
            if (findName(name)) {
                count++;
            }
        }

        System.out.println(count);
        System.out.println(sb);
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
    }
}
