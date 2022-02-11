package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1182_부분수열 {

    static int N, S, answer = 0;
    static int[] A;

    public static void main(String[] args) {

        input();
        recurrentFunction(1,0);
        if (S == 0) { // 공집합일 때, 값을 하나 빼주어야 함.
            answer--;
        }

        System.out.println(answer);
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            N = scan.nextInt();
            S = scan.nextInt();
            A = new int[N+1];
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k, int value) {
        if (k == N+1) {
            if (value == S) answer++;
            return;
        }

        recurrentFunction(k+1, value + A[k]);
        recurrentFunction(k+1, value);
    }

    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        String next() throws IOException {
            while ( st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

}
