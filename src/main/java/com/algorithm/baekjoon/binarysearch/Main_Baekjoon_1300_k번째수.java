package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1300_k번째수 {
    static FastReader scan = new FastReader();

    static int N,K;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
    }

    private static boolean determination(long candidate) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, candidate / i);
        }

        return count >= K;
    }

    private static void solution() {

        long L = 1;
        long R = Integer.MAX_VALUE;
        long answer = 0;

        while (L<=R) {
            long middle = (L+R)/2;

            if (determination(middle)) {
                answer = middle;
                R = middle - 1;
            } else {
                L = middle + 1;
            }
        }

        System.out.println(answer);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st==null || !st.hasMoreElements()) {
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
