package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_Baekjoon_16964_DFS스페셜저지 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            int[] expected = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                graph[x].add(y);
                graph[y].add(x);
            }

            for (int i = 1; i <= N; i++) {
                expected[i] = scan.nextInt();
            }

            Solution16964 solution16964 = new Solution16964();

            int answer = solution16964.solve(N,graph,expected);
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

class Solution16964 {

    StringBuilder sb = new StringBuilder();
    private int N, index = 1;
    ArrayList<Integer>[] graph;
    int[] expected, orders, visitOrders;
    boolean[] visited;

    public int solve(int n, ArrayList<Integer>[] graph, int[] expected) {
        this.N = n;
        this.graph = graph;
        this.expected = expected;

        this.orders = new int[N + 1];
        this.visitOrders = new int[N + 1];
        this.visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            orders[expected[i]] = i;
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], (x,y) -> Integer.compare(orders[x], orders[y]));
        }

        dfs(1);

        return validation();
    }

    private void dfs(int x) {

        visited[x] = true;
        visitOrders[index++] = x;

        for (int next : graph[x]) {
            if (visited[next]) continue;
            dfs(next);
        }
    }

    private int validation() {

        for (int i = 1; i <= N; i++) {
            if (expected[i] != visitOrders[i]) return 0;
        }
        return 1;
    }
}

