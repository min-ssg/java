package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_Baekjoon_10819_차이를최대로 {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A, used, selected;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {

        input();
        recurrentFunction(1);
        System.out.println(max);
    }

    private static void input() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);

            N = scan.nextInt();
            A = new int[N+1];
            used = new int[N+1];
            selected = new int [N+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k) {

        if (k == N+1) {

            int sum = getValue();

            max = Math.max(max, sum);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i] == 1)  continue;

            selected[k] = A[i];
            used[i] = 1;
            recurrentFunction(k+1);
            selected[k] = 0;
            used[i] = 0;
        }
    }

    private static int getValue() {

        int sum = 0;

        for (int i = 1; i < N; i++) {
            sum += Math.abs(selected[i] - selected[i+1]);
        }

        return sum;
    }

    private static class FastReader {

        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader (BufferedReader br) {
            this.br = br;
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
