package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_9663_NQueen {

    static int N, answer = 0;
    static int[] col;

    public static void main(String[] args) {
        input();
        recurrentFunction(1);
    }

    private static void recurrentFunction(int row) {

        if (row == N + 1) {
//            if (validation()) {
//                answer++;
//            }
            answer++;
            return;
        }

        for (int candidate = 1; candidate <= N; candidate++) {
            // row 행의 c열에 놓을 수 있는지 확인
            boolean isPossible = true;
            for (int i = 1; i <= row-1; i++) {
                // (i, col[i])
                if (attackable(row, candidate, i, col[i])) {
                    isPossible = false;
                }
            }

            if (!isPossible) continue;

            col[row] = candidate;
            recurrentFunction(row + 1);
            col[row] = 0;
        }
    }

    private static boolean validation() {
        for (int i = 1; i <= N; i++) {
            // (i, col[i])
            for (int j = 1; j < i; j++) {
                // (j, col[j])
                if (attackable(i, col[i], j, col[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean attackable(int r1, int c1, int r2, int c2) {
        if (c1 == c2) return true;
        if (r1 - c1 == r2 - c2) return true; // 왼쪽 위로 공격가능
        if (r1 + c1 == r2 + c2) return true; // 오른쪽 위로 공격가능
        return false;
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);
            N = scan.nextInt();
            col = new int[N+1];

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
