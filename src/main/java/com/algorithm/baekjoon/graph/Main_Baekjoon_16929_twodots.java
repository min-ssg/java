package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_16929_twodots {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            char[][] map = new char[N][M];
            
            for (int i = 0; i < N; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < str.length(); j++) {
                    map[i][j] = str.charAt(j);
                }
            }

            Solution16929 solution16929 = new Solution16929();
            String answer = solution16929.solve(N,M,map);
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

class Solution16929 {

    private int N, M, fx, fy;
    char[][] map;
    boolean[][] visited;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    public String solve(int n, int m, char[][] map) {

        this.N = n;
        this.M = m;
        this.map = map;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.visited = new boolean[N][M];
                this.fx = i;
                this.fy = j;
                if (dfs(i,j,1)) {
                    return "Yes";
                }
            }
        }

        return "No";
    }

    private boolean dfs(int x, int y, int count) {

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

            if (map[x][y] != map[x + dx[i]][y + dy[i]]) continue;

            if (visited[x + dx[i]][y + dy[i]]) {
                if (fx == x + dx[i] && fy == y + dy[i] && count >= 4) return true;
                continue;
            }

            visited[x + dx[i]][y + dy[i]] = true;
            if (dfs(x + dx[i], y + dy[i], count + 1)) return true;
        }

        return false;
    }
}
