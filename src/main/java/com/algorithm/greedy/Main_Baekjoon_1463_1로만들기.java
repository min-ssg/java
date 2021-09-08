package com.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_Baekjoon_1463_1로만들기 {

    public static int N;
    public static int[] dp;

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            N  = Integer.parseInt(bf.readLine());
            dp = new int[N+1];
            dp[1] = 0; dp[2] = 1; dp[3] = 1;

            int answer = 0;

            for (int i = 4; i <= N; i++) {
                dp[i] = bfs(i);
            }

            System.out.println(Arrays.toString(dp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int bfs(int number) {
        if (dp[number] != 0) return dp[number];
        int from1 = 1; int from2 = 1; int from3 = 1;

        from3 += number % 3 == 0 ? bfs(number / 3) : 1000001;
        from2 += number % 2 == 0 ? bfs(number / 2) : 1000001;
        from1 += bfs(number - 1);

        return Math.min(from1, Math.min(from2, from3));
    }
}
