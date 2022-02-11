package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15663_N과M9 {

    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] numbers = new int[10001];
    static int[] A, orders;

    public static void main(String[] args) {
        input();
        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            N = scan.nextInt();
            M = scan.nextInt();
            A = new int[N+1];
            orders = new int[M+1];
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Arrays.sort(A); // 정렬 O(NlogN)

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k) {
        if (k == M+1) {
            for (int i = 1; i <= M; i++) {
                sb.append(orders[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int previous = 0;
        for (int i = 1; i <= N; i++) {
            int candidate = A[i];
            if (candidate == previous || A[i] == 0) continue;
            previous = candidate;
            A[i] = 0;
            orders[k] = candidate;
            recurrentFunction(k+1);
            A[i] = candidate;
            orders[k] = 0;
        }
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) {
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
