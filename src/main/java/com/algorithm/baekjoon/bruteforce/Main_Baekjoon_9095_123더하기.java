package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_9095_123더하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            FastReader scan = new FastReader(br);
            int T = scan.nextInt();
            Solution9095 solution9095 = new Solution9095();
            for (int i = 0; i < T; i++) {
                int N = scan.nextInt();
                int answer = solution9095.solve(N);
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

class Solution9095 {

    private int N;
    private int count;

    public int solve(int n) {
        this.N = n;

        return recursive(0);
    }

    private int recursive(int sum) {
        if (sum > N) {
            return 0;
        }

        if (sum == N) {
            return 1;
        }

        int count = 0;

        for (int i = 1; i <= 3; i++) {
            count += recursive(sum + i);
        }

        return count;
    }
}
