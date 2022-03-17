package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_13549_숨바꼭질3 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int K = scan.nextInt();

            Solution13549 solution13549 = new Solution13549();
            long answer = solution13549.solve(N,K);
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

class Solution13549 {

    private int N,K;
    private long[] distance;

    public long solve(int n, int k) {
        this.N = n;
        this.K = k;
        this.distance = new long[100_001];

        for (int i = 0; i <= 100_000; i++) {
            distance[i] = -1;
        }

        if (N == K) {
            return 0;
        }

        return bfs();
    }

    private long bfs() {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        distance[N] = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            int temp = x;

            if (x != 0) {
                while (temp * 2 <= 100_000) {
                    temp *= 2;
                    if (distance[temp] != -1) continue;
                    queue.add(temp);
                    distance[temp] = distance[x];
                }

                if (x + 1 <= 100_000 && distance[x + 1] == -1) {
                    queue.add(x + 1);
                    distance[x + 1] = distance[x] + 1;
                }
            }

            if (x - 1 >= 0 && distance[x - 1] == -1) {
                queue.add(x - 1);
                distance[x - 1] = distance[x] + 1;
            }
        }

        return distance[K];
    }
}
