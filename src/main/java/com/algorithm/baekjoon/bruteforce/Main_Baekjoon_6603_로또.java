package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_6603_로또 {

    static StringBuilder sb = new StringBuilder();
    static int K, L;
    static int[] S, selected = new int[7];

    public static void main(String[] args) {
        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);
            while ((K = scan.nextInt()) != 0) {
                S = new int[K+1];

                for (int i = 1; i <= K; i++) {
                    S[i] = scan.nextInt();
                }

                recurrentFunction(1);
                sb.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k) {
        if (k == 7) {

            for (int i = 1; i <= 6; i++) {
                sb.append(S[selected[i]]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = selected[k-1] + 1; i <= K; i++) {
            selected[k] = i;
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
