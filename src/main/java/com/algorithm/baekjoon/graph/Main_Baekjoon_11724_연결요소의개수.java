package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_11724_연결요소의개수 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int V = scan.nextInt();
            int E = scan.nextInt();

            ArrayList<Integer>[] graph = new ArrayList[V+1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                int from = scan.nextInt();
                int to = scan.nextInt();

                graph[from].add(to);
                graph[to].add(from);
            }

            Solution11724 solution11724 = new Solution11724();
            int answer = solution11724.solve(V,graph);
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

class Solution11724 {

    private int V;
    private boolean[] visited;
    ArrayList<Integer>[] GRAPH;

    public int solve(int v, ArrayList<Integer>[] graph) {
        this.V = v;
        this.visited = new boolean[V+1];
        this.GRAPH = graph;

        int connectedComponentCounts = 0;

        for (int n = 1; n <= V; n++) {
            if (visited[n]) continue;
            dfs(n);
            connectedComponentCounts++;
        }

        return connectedComponentCounts;
    }

    private void dfs(int vertex) {

        visited[vertex] = true;

        for (int next : GRAPH[vertex]) {
            if (visited[next]) continue;
            dfs(next);
        }
    }
}
