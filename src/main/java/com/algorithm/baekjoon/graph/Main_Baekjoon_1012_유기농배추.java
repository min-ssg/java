package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1012_유기농배추 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int T = scan.nextInt();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < T; i++) {

                int M = scan.nextInt();
                int N = scan.nextInt();
                int K = scan.nextInt();

                int[][] MAP = new int[N][M];

                for (int k = 0; k < K; k++) {
                    int y = scan.nextInt();
                    int x = scan.nextInt();

                    MAP[x][y] = 1;
                }

                Solution1012 solution1012 = new Solution1012();
                int answer = solution1012.solve(N, M, MAP);
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

class Solution1012 {

    private int N,M;
    private int[][] MAP;
    private boolean[][] visited;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map.clone();
        this.visited = new boolean[N][M];

        int whiteWarmCounts = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0 || visited[i][j]) continue;

                dfs(i,j);
                whiteWarmCounts++;
            }
        }

        return whiteWarmCounts;
    }

    private void dfs(int x, int y) {

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

            if (visited[x + dx[i]][y + dy[i]]) continue;

            if (MAP[x + dx[i]][y + dy[i]] == 0) continue;

            dfs(x + dx[i], y + dy[i]);
        }
    }
}
