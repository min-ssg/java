package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_Baekjoon_1987_알파벳 {

    static int R,C;
    static char[][] board;
//    static boolean[][] visited;
//    static Set<Character> alphabet = new HashSet<>();
    static boolean[] alphabet = new boolean['Z' - 'A' + 1];// A~Z

    static int max = Integer.MIN_VALUE;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) {
        input();
//        recurrentFunction(0,0); // 좌측 상단에서부터 시작
        recurrentFunction(0,0,0);
        System.out.println(max);
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            R = scan.nextInt();
            C = scan.nextInt();

            board = new char[R][C];
//            visited = new boolean[R][C];

            for (int i = 0; i < R; i++) {
                char[] rows = scan.nextLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    board[i][j] = rows[j];
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int x, int y, int count) {

//        if (visited[x][y] && alphabet.contains(board[x][y])) {

        if ( alphabet[board[x][y] - 'A']) {
            return;
        }

        System.out.println("(" + x + "," + y + ") = " + board[x][y]);
        alphabet[board[x][y] - 'A'] =  true;
        count++;
//        visited[x][y] = true;
        max = Math.max(max, count);

        for (int i = 0; i < 4; i++) {

            if (x + dx[i] < 0 || x + dx[i] > R-1
             || y + dy[i] < 0 || y + dy[i] > C-1) continue;

            recurrentFunction(x + dx[i], y + dy[i], count);
        }
        alphabet[board[x][y]-'A'] = false;
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

        public String nextLine() throws IOException {
            return br.readLine();
        }
    }
}
