package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_Baekjoon_11725_트리의부모찾기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int V = scan.nextInt(); // 2 <= V <= 1,000,000

            ArrayList<Integer>[] tree = new ArrayList[V+1];

            for (int i = 1; i <= V; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 1; i < V; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                tree[x].add(y);
                tree[y].add(x);
            }

            Solution11725 solution11725 = new Solution11725();
            solution11725.solve(V,tree);
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

class Solution11725 {

    StringBuilder sb = new StringBuilder();
    private int V;
    private int[] root;
    private boolean[]  visited;

    ArrayList<Integer>[] tree;

    public void solve(int v, ArrayList<Integer>[] tree) {
        this.V = v;
        this.root = new int[V+1];
        this.visited = new boolean[V+1];
        this.tree = tree;

        dfs(1);

        for (int i = 2; i <= V; i++) {
            sb.append(root[i]).append('\n');
        }

        System.out.println(sb.toString());
    }

    private void dfs(int x) {
        visited[x] = true;

        for (int next : tree[x]) {
            if (visited[next]) continue;
            dfs(next);
            root[next] = x;
        }
    }
}
