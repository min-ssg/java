package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 테트로미노
 * 14500
 * 골드5
 * 브루트 포스 문제이고, 해당 모형에 맞게 재귀함수, 예외 처리를 할 줄 알아야 함.
 */
public class Main_Baekjoon_14500_테트로미노 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            int[][] map = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution14500 solution14500 = new Solution14500();
            int answer = solution14500.solve(N,M,map);

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

class Solution14500 {

    private int N, M, result;
    int[][] map;
    boolean[][] visited;

    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.map = map;
        this.visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, map[i][j], 1);
                visited[i][j] = false;
                checkExceptionShape(i,j);
            }
        }

        return result;
    }

    private void dfs(int x, int y, int sum, int count) {
        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {

            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

            if (visited[x + dx[i]][y + dy[i]]) continue;

            visited[x + dx[i]][y + dy[i]] = true;
            dfs(x + dx[i], y + dy[i], sum + map[x + dx[i]][y + dy[i]], count + 1);
            visited[x + dx[i]][y + dy[i]] = false;
        }
    }

    private void checkExceptionShape(int x, int y) {

        int sum = 0;

        // ㅜ
        if (x >= 0 && x + 1 <= N - 1 && y >= 0 && y + 2 <= M - 1) {
            sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1];
            result = Math.max(result, sum);
        }

        // ㅏ
        if (x >= 0 && x + 2 <= N - 1 && y >= 0 && y + 1 <= M - 1) {
            sum = map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y + 1];
            result = Math.max(result, sum);
        }

        // ㅗ
        if (x - 1 >= 0 && x <= N - 1 && y >= 0 && y + 2 <= M - 1) {
            sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x - 1][y + 1];
            result = Math.max(result, sum);
        }

        // ㅓ
        if (x - 1 >= 0 && x + 1 <= N - 1 && y >= 0 && y + 1 <= M - 1) {
            sum = map[x][y] + map[x][y + 1] + map[x - 1][y + 1] + map[x + 1][y + 1];
            result = Math.max(result, sum);
        }
    }
}
