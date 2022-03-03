package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동물원 역시, 스티커, 계단오르기, 포도주시식 처럼
 * 해당 시점이 이전의 상태와 중복되는 점화식을 찾는다.
 */
public class Main_Baekjoon_1309_동물원 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            Solution1309 solution1309 = new Solution1309();
            int answer = solution1309.solve(N);
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

class Solution1309 {

    private int[][] dp;

    public int solve(int n) {
        dp = new int[n+1][3];
        dp[1][0] = 1; // 마지막 양쪽 우리에 사자가 없는 경우
        dp[1][1] = 1; // 마지막 왼쪽 우리에 사자가 있는 경우
        dp[1][2] = 1; // 마지막 오른쪽 우리에 사자가 있는 경우

        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }

        return (dp[n][0] + dp[n][1] + dp[n][2]) % 9901;
    }
}
