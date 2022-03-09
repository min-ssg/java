package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_11403_경로찾기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[][] graph = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }

            Solution11403 solution11403 = new Solution11403();
            solution11403.solve(N, graph);
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

class Solution11403 {

    StringBuilder sb = new StringBuilder();
    private int N;
    private int[][] graph;
    private boolean[] visited;

    public void solve(int n, int[][] graph) {
        this.N = n;
        this.graph = graph;

        for (int i = 0; i < N; i++) {
            bfs(i);
        }

        System.out.println(sb.toString());
    }

    private void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>();
        this.visited = new boolean[N];

        queue.add(start);

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int next = 0; next < N; next++) {
                if (graph[x][next] == 0) continue;
                if (visited[next]) continue;

                queue.add(next);
                visited[next] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(visited[i] ? 1 : 0).append(' ');
        }
        sb.append('\n');
    }
}