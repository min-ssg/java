package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_1963_소수경로 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();

            int T = scan.nextInt();
            Solution1963 solution1963 = new Solution1963();
            for (int i = 0; i < T; i++) {
                int s = scan.nextInt();
                int t = scan.nextInt();

                int answer = solution1963.solve(s,t);
                if (answer == -1) {
                    sb.append("Impossible").append('\n');
                    continue;
                }
                sb.append(answer).append('\n');
            }
            System.out.println(sb.toString());
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

class Solution1963 {

    private int S,T;
    StringBuilder sb = new StringBuilder();
    boolean[] isNotPrime = new boolean[10_000];
    int[] distance = new int[10_000];

    public int solve(int s, int t) {

        this.S = s;
        this.T = t;
        primeSetting();

        for (int i = 1000; i < 10_000; i++) {
            distance[i] = -1;
        }

        return bfs();
    }

    private int bfs() {

        Queue<Integer> queue = new LinkedList<>();
        distance[S] = 0;
        queue.add(S);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int dist = distance[x];

            if (x == T) return dist;

            int a = x / 1000;
            int b = (x - (a * 1000)) / 100;
            int c = (x - (a * 1000 + b * 100)) / 10;
            int d = x % 10;

            // 일의 자리
            int remain = a * 1000 + b * 100 + c * 10;
            for (int i = 0; i < 10; i++) {
                if (remain + i == x) continue;
                if (!isNotPrime[remain + i] && distance[remain + i] == -1) {
                    distance[remain + i] = dist + 1;
                    queue.add(remain + i);
                }
            }

            // 10의 자리
            remain = a * 1000 + b * 100 + d;
            for (int i = 0; i < 10; i++) {
                if (remain + i * 10 == x) continue;
                if (!isNotPrime[remain + i * 10] && distance[remain + i * 10] == -1) {
                    distance[remain + i * 10] = dist + 1;
                    queue.add(remain + i * 10);
                }
            }

            // 100의 자리
            remain = a * 1000 + c * 10 + d;
            for (int i = 0; i < 10; i++) {
                if (remain + i * 100 == x) continue;
                if (!isNotPrime[remain + i * 100] && distance[remain + i * 100] == -1) {
                    distance[remain + i * 100] = dist + 1;
                    queue.add(remain + i * 100);
                }
            }

            // 1000의 자리
            remain = b * 100 + c * 10 + d;
            for (int i = 1; i < 10; i++) {
                if (remain + i * 1000 == x) continue;
                if (!isNotPrime[remain + i * 1000] && distance[remain + i * 1000] == -1) {
                    distance[remain + i * 1000] = dist + 1;
                    queue.add(remain + i * 1000);
                }
            }
        }

        return -1;
    }

    private void primeSetting() {

        for (int i = 2; i <= 5_000; i++) {
            for (int j = i + i; j < 10_000; j += i) {
                isNotPrime[j] = true;
            }
        }
    }
}
