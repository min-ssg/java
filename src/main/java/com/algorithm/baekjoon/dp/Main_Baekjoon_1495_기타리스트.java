package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_Baekjoon_1495_기타리스트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // 1 <= N <= 50
            int S = scan.nextInt();
            int M = scan.nextInt(); // M <= 1000

            int[] V = new int[N+1];

            for (int i = 1; i <= N; i++) {
                V[i] = scan.nextInt();
            }

            Solution1495 solution1495 = new Solution1495();
            int answer = solution1495.solve(N,S,M,V);
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

class Solution1495 {

    private int N,S,M;
    private int[] V;
    private boolean[][] DP;

    public int solve(int n, int s, int m, int[] v) {
        this.N = n;
        this.S = s;
        this.M = m;
        this.V = v.clone();

        DP = new boolean[N+1][M+1];

        return dynamicProgramming();
    }

    private int dynamicProgramming() {

        DP[0][S] = true;
        System.out.println(Arrays.toString(DP[0]));
        for (int i = 1; i <= N; i++) {

            for (int prev = 0; prev <= M; prev++) {
                if (!DP[i-1][prev]) continue;
                if (prev - V[i] >= 0) DP[i][prev - V[i]] = true;
                if (prev + V[i] <= M) DP[i][prev + V[i]] = true;
            }
            if (isAvailableNextStep(i)) return -1;
            System.out.println(Arrays.toString(DP[i]));
        }

        int max = 0;

        for (int volume = M; volume >= 0; volume--) {
            if (DP[N][volume]) {
                max = volume;
                break;
            }
        }

        return max;
    }

    private boolean isAvailableNextStep(int index) {
        return IntStream.rangeClosed(0,M).noneMatch(i -> DP[index][i]);
    }
}
