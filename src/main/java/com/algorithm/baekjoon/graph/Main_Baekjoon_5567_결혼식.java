package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_5567_결혼식 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int M = scan.nextInt();

            ArrayList<Integer>[] graph = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                
                graph[x].add(y);
                graph[y].add(x);
            }

            Solution5567 solution5567 = new Solution5567();
            int answer = solution5567.solve(N,M,graph);
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

class Solution5567 {

    private int N,M;
    ArrayList<Integer>[] graph;
    private int[] visited;

    public int solve(int n, int m, ArrayList<Integer>[] graph) {

        this.N = n;
        this.M = m;
        this.graph = graph;

        this.visited = new int[N+1];

        bfs(1);

        return getNumberOfSamePeriod();
    }

    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int order = visited[x];

            for (int next : graph[x]) {
                if (visited[next] != 0) continue;
                queue.add(next);
                visited[next] = order + 1;
            }
        }
    }

    private int getNumberOfSamePeriod() {
        int number = 0;
        for (int i = 2; i <= N; i++) {
            if (visited[i] != 0 && visited[i] <= 3) {
                number++;
            }
        }

        return number;
    }
}
