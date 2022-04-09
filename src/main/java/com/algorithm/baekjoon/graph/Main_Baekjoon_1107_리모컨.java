package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1107_리모컨 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int[] A = new int[M]; // 고장난 버튼 배열

            for (int i = 0; i < M; i++) {
                A[i] = scan.nextInt();
            }

            Solution1107 solution1107 = new Solution1107();
            int answer = solution1107.solve(N,M,A);
            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution1107 {

    private int N, M;

    boolean[] broken = new boolean[10];

    public int solve(int n, int m, int[] a) {
        this.N = n;
        this.M = m;

        for (int i = 0; i < M; i++) {
            broken[a[i]] = true;
        }

        return solution();
    }

    private int solution() {

        int result = Math.abs(N - 100);

        for (int i = 0; i <= 500_000; i++) {
            int c = i;

            int len = check(c);

            if (i == 1000) {
                System.out.println("Len = " + len);
            }

            if (len > 0) {
                int press = Math.abs(c - N);
                if (result > press + len) {
//                    System.out.println("len = " + len + ", c = " + c);
                    result = press + len;
                }
            }
        }

        return result;
    }

    private int check(int n) {
        if (n == 0) {
            if (broken[0]) {
                return 0;
            } else {
                return 1;
            }
        }

        int len = 0;

        while (n > 0) {
            if (broken[n % 10]) return 0;
            n = n / 10;
            len += 1;
        }

        return len;
    }
}