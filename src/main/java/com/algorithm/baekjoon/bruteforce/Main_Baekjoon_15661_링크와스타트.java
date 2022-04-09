package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_15661_링크와스타트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            int[][] A = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    A[i][j] = scan.nextInt();
                }
            }

            Solution15661 solution15661 = new Solution15661();
            int answer = solution15661.solve(N,A);
            System.out.println(answer);

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

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution15661 {

    private int N, min = Integer.MAX_VALUE;
    private int[][] A;

    List<Integer> start = new ArrayList<>();
    List<Integer> link = new ArrayList<>();

    public int solve(int n, int[][] a) {
        this.N = n;
        this.A = a;

        recurrentFunction(1);

        return min;
    }

    private void recurrentFunction(int k) {
        if (k == N + 1) {

            if (start.size() > 0 && link.size() > 0) {
                int startSum = 0;
                int linkSum = 0;

                for (int i = 0; i < start.size(); i++) {
                    for (int j = i + 1; j < start.size(); j++) {
                        startSum += A[start.get(i)][start.get(j)] + A[start.get(j)][start.get(i)];
                    }
                }

                for (int i = 0; i < link.size(); i++) {
                    for (int j = i + 1; j < link.size(); j++) {
                        linkSum += A[link.get(i)][link.get(j)] + A[link.get(j)][link.get(i)];
                    }
                }

                min = Math.min(min, Math.abs(startSum - linkSum));
            }

            return;
        }

        start.add(k);
        recurrentFunction(k + 1);
        start.remove(start.size() - 1);

        link.add(k);
        recurrentFunction(k + 1);
        link.remove(link.size() - 1);
    }
}
