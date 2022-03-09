package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_2352_반도체설계 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt(); // 1 <= N <= 40000
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution2352 solution2352 = new Solution2352();
            int answer = solution2352.solve(N,A);
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

class Solution2352 {

    List<Integer> dp = new ArrayList<>();
    boolean[] used = new boolean[1_000_001];

    public int solve(int n, int[] a) {

        for (int port : a) { // 1000000

            if (used[port]) continue;

            if (dp.isEmpty() || dp.get(dp.size() - 1) < port) {
                dp.add(port);
            } else {
                int L = binarySearch(port);
                used[dp.get(L)] = false;
                dp.set(L, port);
            }
            used[port] = true;
        }

        return dp.size();
    }

    private int binarySearch(int port) {

        int L = 0;
        int R = dp.size() - 1;

        while (L <= R) {
            int middle = (L + R) / 2;

            if (dp.get(middle) >= port) {
                R = middle - 1;
            } else {
                L = middle + 1;
            }
        }

        return L;
    }
}