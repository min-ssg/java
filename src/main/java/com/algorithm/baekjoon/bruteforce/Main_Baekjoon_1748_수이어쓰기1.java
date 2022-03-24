package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1748_수이어쓰기1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            Solution1748 solution1748 = new Solution1748();
            int answer = solution1748.solve(N);
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

class Solution1748 {

    private int[] dp = new int[10];

    public int solve(int n) {

        int answer = 0;

        for (int i = 1; i <= 8; i++) {
            dp[i] = (int) Math.pow(10, i-1) * 9;
        }
        dp[9] = 10;

        int index = (int) (Math.log10(n) + 1);
        answer += (n - (int) (Math.pow(10, index - 1)) + 1) * index;
//        n = (int) (Math.pow(10, index - 1) - 1);
        for (int i = index - 1; i >= 1; i--) {
            answer += dp[i] * i;
        }
        return answer;
    }
}
