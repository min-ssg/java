package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_1697_숨바꼭질 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int K = scan.nextInt();

            Solution1697 solution1697 = new Solution1697();
            int answer = solution1697.solve(N,K);
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

class Solution1697 {

    private int N,K;
    private int[] distance;

    public int solve(int n, int k) {
        this.N = n;
        this.K = k;
        this.distance = new int[200_001];

        for (int i = 0; i <= 200_000; i++) {
            distance[i] = -1;
        }

        return bfs(n);
    }

    private int bfs(int n) {

        if (N == K) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        distance[n] = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int d = distance[x];

            if (x - 1 >= 0 && distance[x-1] == -1) {
                queue.add(x-1);
                distance[x - 1] = d + 1;
            }

            if (x + 1 <= 100_000 && distance[x + 1] == -1) {
                queue.add(x+1);
                distance[x + 1] = d + 1;
            }

            if (x * 2 <= 200_000 && distance[x * 2] == -1) {
                queue.add(x * 2);
                distance[x * 2] = d + 1;
            }
        }

        return distance[K];
    }
}
