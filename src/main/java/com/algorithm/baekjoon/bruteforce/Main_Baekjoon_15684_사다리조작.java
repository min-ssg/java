package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사다리 조작
 * 난이도 골드 4
 * 구현
 */
public class Main_Baekjoon_15684_사다리조작 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int n = scan.nextInt(); // 열
            int m = scan.nextInt();
            int h = scan.nextInt(); // 행
            int[][] ladders = new int[h+1][n+1];
            for (int i = 1; i <= m; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                ladders[x][y] = 1;
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= n; j++) {
                    sb.append(ladders[i][j]).append(' ');
                }
                sb.append('\n');
            }

            Solution15684 solution15684 = new Solution15684(n,m,h,ladders);

            solution15684.solve();
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

class Solution15684 {

    private int n,m,h, count;
    private int[][] ladders,visited;
    private int[] selected;

    private boolean possible;

    private int[] dx = {1,0,0};
    private int[] dy = {0,-1,1};

    public Solution15684(int n, int m, int h, int[][] ladders) {
        this.n = n;
        this.m = m;
        this.h = h;
        this.ladders = ladders.clone();
        this.visited = new int[h+1][n+1];
    }


    public void solve() {

        for (int i = 1; i <= 3; i++) {
            count = i;
            selected = new int[i];
            recurrentFunction(1, 0);

            if (possible) {
                System.out.println(count);
                return;
            }
        }

        System.out.println(-1);
    }

    private void recurrentFunction(int row, int count) {
        if (possible) {
            return;
        }

        if (count == this.count) {

            possible = true;

            for (int col = 1; col <= n; col++) {
                int c = col;

                for (int x = 1; x <= h; x++) { // 행

                    if (ladders[x][c] == 1) {
                        c++;
                    } else if (c > 1 && ladders[x][c-1] == 1) {
                        c--;
                    }
                }

                if (col != c) {
                    possible = false;
                    return;
                }
            }
        }

        for (int i = row; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (ladders[i][j-1] == 0 && ladders[i][j] == 0 && ladders[i][j+1] == 0) {
                    ladders[i][j] = 1;
                    recurrentFunction(i, count+1);
                    ladders[i][j] = 0;
                }
            }
        }
    }
}
