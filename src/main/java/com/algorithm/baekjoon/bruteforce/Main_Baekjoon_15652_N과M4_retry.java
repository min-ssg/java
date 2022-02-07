package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_15652_N과M4_retry {

    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] selected;

    public static void main(String[] args) {
        input();
        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private static void input() {
        FastReader scan = new FastReader(new BufferedReader(new InputStreamReader(System.in)));
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
    }

    private static void recurrentFunction(int k) {
        if (k == M+1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        int start = selected[k-1] == 0 ? 1 : selected[k-1];
        for (int candidate = start; candidate <= N; candidate++) {
            selected[k] = candidate;
            recurrentFunction(k+1);
            selected[k] = 0;
        }
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
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
