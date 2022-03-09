package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_Baekjoon_2606_바이러스 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int V = scan.nextInt();
            int E = scan.nextInt();

            ArrayList<Integer>[] graph = new ArrayList[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= E; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                graph[x].add(y);
                graph[y].add(x);
            }

            Solution2606 solution2606 = new Solution2606();
            int answer = solution2606.solve(V,E,graph);
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

class Solution2606 {

    private int V,E;
    private boolean[] visited;

    ArrayList<Integer>[] graph;

    public int solve(int v, int e, ArrayList<Integer>[] graph) {
        this.V = v;
        this.E = e;
        this.graph = graph;

        this.visited = new boolean[V+1];

        return dfs(1) - 1; // 1번은 자기자신은 제외.
    }

    private int dfs(int x) {

        visited[x] = true;
        int numberOfVirus = 1;

        for (int next : graph[x]) {
            if (visited[next]) continue;

            numberOfVirus += dfs(next);
        }

        return numberOfVirus;
    }
}
