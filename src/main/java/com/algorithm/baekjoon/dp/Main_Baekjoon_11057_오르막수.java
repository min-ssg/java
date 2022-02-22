package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_Baekjoon_11057_오르막수 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            Solution11057 solution11057 = new Solution11057(N);
            int answer = solution11057.solve();
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

class Solution11057 {

    private int N;
    private int[][] dp;

    public Solution11057(int n) {
        this.N = n;
        this.dp = new int[N+1][10];
    }

    public int solve() {

        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = sumValue(i-1,j);
            }
        }

        return Arrays.stream(dp[N]).reduce((x,y)->(x+y) % 10_007).getAsInt();
    }

    private int sumValue(int length, int last) {

        return IntStream.rangeClosed(0,last).map(i->dp[length][i]).reduce((x,y) -> (x + y) % 10_007).getAsInt();
    }
}
