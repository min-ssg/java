package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_1260_DFS와BFS {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // vertex
            int M = scan.nextInt(); // edge
            int start = scan.nextInt();

            boolean[][] vertex = new boolean[N+1][N+1];
            ArrayList<Integer>[] adjacency = new ArrayList[N+1];

            for (int i = 1; i <= N; i++) {
                adjacency[i] = new ArrayList<>();
            }

            for (int i = 1; i<= M; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                vertex[x][y] = true;
                vertex[y][x] = true;

                adjacency[x].add(y);
                adjacency[y].add(x);
            }

            for (int i = 1; i <= N; i++) {
                adjacency[i].sort(Integer::compareTo);
            }


            Solution1260Matrix solution1260Matrix = new Solution1260Matrix();
            Solution1260List solution1260List = new Solution1260List();
            solution1260Matrix.solve(N,M,start,vertex);
            solution1260List.solve(N,M,start,adjacency);
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

class Solution1260Matrix {

    private StringBuilder sb = new StringBuilder();
    private int N,M;
    private boolean[][] vertex;
    private boolean[] visited;

    public void solve(int n, int m, int start, boolean[][] vertex) {
        this.N = n;
        this.M = m;
        this.vertex = vertex.clone();

        initVisited();
        dfs(start);
        sb.append('\n');
        initVisited();
        bfs(start);
        sb.append('\n');

        System.out.println(sb.toString());
    }

    public void initVisited() {
        this.visited = new boolean[N+1];
    }

    private void dfs(int x) {

        visited[x] = true;
        sb.append(x).append(' ');

        for (int i = 1; i <= N; i++) {
            boolean isNext = vertex[x][i];
            if (isNext && !visited[i]) {
                dfs(i);
            }
        }
    }

    private void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(' ');
            for (int i = 1; i <= N; i++) {
                boolean isNext = vertex[x][i];

                if (isNext && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}

class Solution1260List {
    private StringBuilder sb = new StringBuilder();
    private int V,E;
    private ArrayList<Integer>[] adjacency;
    private boolean[] visited;

    public void solve(int n, int m, int start, ArrayList<Integer>[] adjacency) {
        this.V = n;
        this.E = m;
        this.adjacency = adjacency;

        initVisited();
        dfs(start);
        sb.append('\n');
        initVisited();
        bfs(start);
        sb.append('\n');

        System.out.println(sb.toString());
    }

    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(' ');
            for (int next : adjacency[x]) {
                if (visited[next]) continue;

                queue.add(next);
                visited[next] = true;
            }
        }
    }

    private void dfs(int x) {

        visited[x] = true;
        sb.append(x).append(' ');
        for (int next : adjacency[x]) {
            if (visited[next]) continue;

            dfs(next);
        }
    }

    private void initVisited() {
        this.visited = new boolean[V+1];
    }
}