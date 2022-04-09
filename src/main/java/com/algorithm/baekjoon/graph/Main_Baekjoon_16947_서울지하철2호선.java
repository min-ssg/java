package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_16947_서울지하철2호선 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            ArrayList<Integer>[] graph = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                graph[x].add(y);
                graph[y].add(x);
            }

            Solution16497 solution16497 = new Solution16497();
            solution16497.solve(N,graph);

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

class Solution16497 {

    StringBuilder sb = new StringBuilder();
    private int N;
    ArrayList<Integer>[] graph;
    boolean[] isCycle, visited;

    public void solve(int n, ArrayList<Integer>[] graph) {

        this.N = n;
        this.graph = graph;
        this.isCycle = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int start = i;
            isCycle[i] = dfs(i, start, 0);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            sb.append(bfs(i)).append(' ');
        }

        System.out.println(sb.toString());

    }

    private int bfs(int start) {

        if (isCycle[start]) return 0;

        int[] distance = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        distance[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int d = distance[x];

            for (int i = 0; i < graph[x].size(); i++) {
                int next = graph[x].get(i);

                if (isCycle[next]) return d + 1;

                if (visited[next]) continue;

                visited[next] = true;
                distance[next] = d + 1;
                queue.add(next);
            }
        }

        return -1;
    }

    private boolean dfs(int current, int start, int count) {

        if (current == start && count >= 2) {
            return true;
        }

        boolean cycle = false;
        visited[current] = true;

        for (int i = 0; i < graph[current].size(); i++) {
            int next = graph[current].get(i);

            if (!visited[next]) {
                cycle = dfs(next, start, count + 1);
            } else {
                if (next == start && count >= 2) {
                    cycle = dfs(next, start, count);
                }
            }

            if (cycle) {
                return true;
            }
        }

        return false;
    }
}
