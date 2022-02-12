package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_2580_스도쿠 {

    static StringBuilder sb = new StringBuilder();
    static int[][] board = new int[10][10];
    static int N = 0;
    static int[] x,y, selected;
    static boolean found = false;


    public static void main(String[] args) {

        input();
        recurrentFunction(1);

        System.out.println(sb.toString());
    }

    private static void recurrentFunction(int k) {

        if (k == N+1) {

            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }
            found = true;
            return;
        }

        for (int candidate = 1; candidate <= 9; candidate++) {
            int row = x[k];
            int col = y[k];
            boolean isPossible = true;
            for (int i = 1; i <= 9; i++) {
                if (board[row][i] == candidate || board[i][col] == candidate) {
                    isPossible = false;
                    break;
                }
            }
            if (!isPossible) continue;

            int row_start = 0;
            int row_end = 0;
            int col_start = 0;
            int col_end = 0;

            switch (row) {
                case 1: case 2: case 3:
                    row_start = 1;
                    row_end = 3;
                    break;
                case 4: case 5: case 6:
                    row_start = 4;
                    row_end = 6;
                    break;
                case 7: case 8: case 9:
                    row_start = 7;
                    row_end = 9;
                    break;
            }

            switch (col) {
                case 1: case 2: case 3:
                    col_start = 1;
                    col_end = 3;
                    break;
                case 4: case 5: case 6:
                    col_start = 4;
                    col_end = 6;
                    break;
                case 7: case 8: case 9:
                    col_start = 7;
                    col_end = 9;
                    break;
            }

            for (int a = row_start; a <= row_end; a++) {
                for (int b = col_start; b <= col_end; b++) {
                    if (board[a][b] == candidate) {
                        isPossible = false;
                        break;
                    }
                }
            }
            if (!isPossible) continue;

            board[row][col] = candidate;
            recurrentFunction(k+1);
            board[row][col] = 0;

            if (found) return;
        }
    }

    private static void input() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    board[i][j] = scan.nextInt();

                    if (board[i][j] == 0) {
                        N++;
                    }
                }

            }

            x = new int[N+1];
            y = new int[N+1];
            selected = new int[N+1];
            int temp = 1;

            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {

                    if (board[i][j] == 0) {
                        x[temp] = i;
                        y[temp] = j;
                        temp++;
                    }
                }
            }

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
