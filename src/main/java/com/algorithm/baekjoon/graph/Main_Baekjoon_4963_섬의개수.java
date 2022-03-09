package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_4963_섬의개수 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();

            while (true) {
                int M = scan.nextInt();
                int N = scan.nextInt();

                if (N == 0 && M == 0) break;

                int[][] MAP  = new int[N][M];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        MAP[i][j] = scan.nextInt();
                    }
                }

                Solution4963 solution4963 = new Solution4963();
                int answer = solution4963.solve(N,M,MAP);
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

class Solution4963 {

    private int N, M;
    private int[][] MAP;
    private boolean[][] visited;

    private int[] dx = {-1,-1,0,1,1,1,0,-1};
    private int[] dy = {0,1,1,1,0,-1,-1,-1};

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map;
        this.visited = new boolean[N][M];

        int landCounts = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0 || visited[i][j]) continue;
                dfs(i,j);
                landCounts++;
            }
        }
        return landCounts;
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

            if (MAP[x + dx[i]][y + dy[i]] == 0) continue;

            if (visited[x + dx[i]][y + dy[i]]) continue;

            dfs(x + dx[i], y + dy[i]);
        }
    }
}
