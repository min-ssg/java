package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 종이조각
 * 유형 : 브루트포스 (비트마스킹)
 * 난이도 : 골드3
 * 비트마스킹에 대해 처음 풀어봄.
 */
public class Main_Baekjoon_14391_종이조각 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            char[][] A = new char[N][M];

            for (int i = 0; i < N; i++) {
                String str = scan.nextLine();
                A[i] = str.toCharArray();
            }

            Solution14391 solution14391 = new Solution14391();
            int answer = solution14391.solve(N,M,A);
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

class Solution14391 {

    private int N, M, max = Integer.MIN_VALUE;
    char[][] A;

    public int solve(int n, int m, char[][] a) {
        this.N = n;
        this.M = m;
        this.A = a;

        for (int i = 0; i < 1 << (N * M); i++) {
            int total = 0;

            for (int row = 0; row < N; row++) {
                int rowSum = 0;
                for (int col = 0; col < M; col++) {
                    int index = row * M + col;

                    // 가로일 때,
                    if ((i & (1 << index)) != 0) {
                        rowSum = rowSum * 10 + (A[row][col] - '0');
                    } else {
                        total += rowSum;
                        rowSum = 0;
                    }
                }
                total += rowSum;
            }

            for (int col = 0; col < M; col++) {
                int colSum = 0;
                for (int row = 0; row < N; row++) {
                    int index = row * M + col;
                    if ((i & (1 << index)) == 0) {
                        colSum = colSum * 10 + (A[row][col] - '0');
                    } else {
                        total += colSum;
                        colSum = 0;
                    }
                }
                total += colSum;
            }

            max = Math.max(max, total);
        }

        return max;
    }
}
