package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_Baekjoon_10816_숫자카드2 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();

        for (int i = 0; i < N; i++) {
            int K = scan.nextInt();

            if (map.containsKey(K)) {
                int V = map.get(K);
                map.put(K, V+1);
                continue;
            }

            map.put(K, 1);
        }

        M = scan.nextInt();
    }

    private static void solution() {

        for (int i = 0; i < M; i++) {
            int T = scan.nextInt();
            int count = map.getOrDefault(T, 0);
            sb.append(count);
            if (i < M-1) {
                sb.append(' ');
            }
        }

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
    }
}
