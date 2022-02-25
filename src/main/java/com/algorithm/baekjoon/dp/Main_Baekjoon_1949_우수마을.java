package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1949_우수마을 {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            boolean[][] graph = new boolean[N+1][N+1];

            for (int i = 1; i <= N-1; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                graph[x][y] = true;
                graph[y][x] = true;
            }

            Solution1949 solution1949 = new Solution1949();
            solution1949.solve(N,A,graph);

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

class Solution1949 {

    private boolean[][] graph, visited;
    private int N;
    private int[] A;

    public void solve(int n, int[] a, boolean[][] graph) {
        this.N = n;
        this.A = a.clone();
        this.graph = graph.clone();
        visited = new boolean[n+1][n+1];

        dfs(1);

    }

    private void dfs(int index) {



        for (int row = 1; row <= N; row++) {
            if (visited[index][row] || index == row) continue;

            dfs(row);
        }
    }
}
