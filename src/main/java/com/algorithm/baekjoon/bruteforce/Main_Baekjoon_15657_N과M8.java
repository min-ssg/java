package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_15657_N과M8 {

    static int N, M;
    static int[] A;

    public static void main(String[] args) {
        input();
        Solution15657 solution = new Solution15657();
        solution.solution(N, M, A);
    }

    private static void input() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            N = scan.nextInt();
            M = scan.nextInt();
            A = new int[N+1];
            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }
            Arrays.sort(A, 1, N+1);
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

class Solution15657 {

    private StringBuilder sb = new StringBuilder();
    private int N,M;
    private int[] selected, A;
    public void solution(int N, int M, int[] A) {
        selected = new int[M+1];
        this.N = N;
        this.M = M;
        this.A = A.clone();

        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private void recurrentFunction(int k) {
        if (k == M+1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            int candidate = A[i];
            if (candidate < selected[k-1]) continue; // 이전 선택값보다 현재 후보값이 더 작으면 패스!!

            selected[k] = candidate;
            recurrentFunction(k+1);
            selected[k] = 0;
        }
    }
}
