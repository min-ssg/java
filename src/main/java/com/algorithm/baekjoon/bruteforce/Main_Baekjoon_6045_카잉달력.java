package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_6045_카잉달력 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            StringBuilder sb = new StringBuilder();

            int T = scan.nextInt();

            Solution6045 solution6045 = new Solution6045();
            for (int i = 0; i < T; i++) {
                int M = scan.nextInt();
                int N = scan.nextInt();
                int X = scan.nextInt();
                int Y = scan.nextInt();

                int answer = solution6045.solve(M, N, X, Y);
                sb.append(answer).append('\n');
            }

            System.out.println(sb.toString());
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

class Solution6045 {

    private int M,N,X,Y;

    public int solve(int m, int n, int x, int y) {
        this.M = m;
        this.N = n;
        this.X = x;
        this.Y = y;

        return solution();
    }

    private int solution() {

        while ( X <= N * M) {
            if ((X - Y) % N == 0) {
                break;
            }

            X += M;
        }

        if (X > N * M) { // Not Found
            X = -1;
        }

        return X;
    }
}
