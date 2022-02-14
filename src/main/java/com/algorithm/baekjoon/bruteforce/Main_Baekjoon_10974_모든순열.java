package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_10974_모든순열 {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] selected, used;

    public static void main(String[] args) {
        input();
        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            N = scan.nextInt();
            selected = new int[N+1];
            used = new int[N+1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k) {

        if (k == N+1) {
            for (int i = 1; i <= N; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i] == 1) continue;

            selected[k] = i;
            used[i] = 1;
            recurrentFunction(k+1);
            selected[k] = 0;
            used[i] = 0;
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

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
