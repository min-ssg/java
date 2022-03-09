package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_1389_케빈베이컨의6단게법칙 {
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

            Solution1389 solution1389 = new Solution1389();
            int answer = solution1389.solve(N, M, graph);
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

class Solution1389 {

    private int N,M;
    ArrayList<Integer>[] graph;
    private int[][] relationship;

    public int solve(int n, int m, ArrayList<Integer>[] graph) {
        this.N = n;
        this.M = m;
        this.graph = graph;
        this.relationship = new int[N+1][N+1]; // 각 노드가 가지는 연관 관계 수

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        return minValueIndex();
    }

    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int order = relationship[start][x];

            for (int next : graph[x]) {
                if (relationship[start][next] != 0) continue;
                if (start == next) continue;
                queue.add(next);
                relationship[start][next] = order + 1;
            }
        }
    }

    private int minValueIndex() {

        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int sum = Arrays.stream(relationship[i]).sum();
            if (minValue > sum) {
                minValue = sum;
                minIndex = i;
            }
        }

        return minIndex;
    }

    private void printRelationship() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(relationship[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }
}
