package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_2644_촌수계산 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt(); // 1 <= N <= 100;
            int from = scan.nextInt(); int to = scan.nextInt();
            int M = scan.nextInt(); // 관계

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

            Solution2644 solution2644 = new Solution2644();
            int answer = solution2644.solve(N,M,from,to,graph);
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

class Solution2644 {

    private int N,M,from,to;
    ArrayList<Integer>[] graph;

    private boolean[] visited;

    public int solve(int n, int m, int from, int to, ArrayList<Integer>[] graph) {
        this.N = n;
        this.M = m;
        this.from = from;
        this.to = to;
        this.graph = graph;

        this.visited = new boolean[N+1];

        return bfs(from);
    }

    private int bfs(int from) {
        Queue<Integer> queue = new LinkedList<>();
        int[] numbers = new int[N+1];

        queue.add(from);
        visited[from] = true;
        numbers[from] = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int next : graph[x]) {
                if (visited[next]) continue;

                numbers[next] = numbers[x] + 1;
                if (next == to) {
                    return numbers[next];
                }
                queue.add(next);
                visited[next] = true;
            }
        }

        return -1;
    }
}