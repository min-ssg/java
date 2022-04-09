package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_16490_BFS스페셜저지 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            int[] expected = new int[N];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                graph[x].add(y);
                graph[y].add(x);
            }

            for (int i = 0; i < N; i++) {
                expected[i] = scan.nextInt();
            }

            Solution16490 solution16490 = new Solution16490();
            int answer = solution16490.solve(N, graph, expected);
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

class Solution16490 {

    StringBuilder sb = new StringBuilder();
    private int N;
    ArrayList<Integer>[] graph;
    int[] expected, visited, distances, orders;

    public int solve(int n, ArrayList<Integer>[] graph, int[] expected) {
        this.N = n;
        this.graph = graph;
        this.expected = expected;

        this.visited = new int[N + 1];
        this.distances = new int[N + 1];
        this.orders = new int[N + 1];


        for (int i = 0; i < N; i++) {
            orders[expected[i]] = (i + 1);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], (x, y) -> Integer.compare(orders[x], orders[y]));
        }

        bfs();

        return validation();
    }

    private int validation() {
        for (int i = 0; i < N; i++) {
            if (expected[i] != visited[i + 1]) {
                return 0;
            }
        }

        return 1;
    }

    private void bfs() {

        int k = 1;
        Queue<Integer> queue = new LinkedList<>();
        distances[1] = 1;
        queue.add(1);

        int index = 1;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int distance = distances[x];
            visited[k++] = x;

            for (int i = 0; i < graph[x].size(); i++) {
                int next = graph[x].get(i);
                if (distances[next] != 0) continue;

                distances[next] = distance + 1;
                queue.add(next);
            }
        }
    }
}