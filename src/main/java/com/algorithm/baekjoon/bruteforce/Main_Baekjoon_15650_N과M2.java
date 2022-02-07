package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N과 M(2)
 * 난이도: 실버3
 * 시간제한: 1초
 * 완전탐색: 중복 없이, M개 고르기
 */
public class Main_Baekjoon_15650_N과M2 {

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
        if (k == M + 1) {
            // 출력
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int candidate = selected[k-1] + 1; candidate <= N; candidate++) {
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

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
