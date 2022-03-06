package com.algorithm.programmers.highscorekit;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  프로그래머스 등굣길 문제
 *  (1,1) -> (m,n) 까지 최단거리 + 웅더이(puddles)를 피해서
 */
public class Main_Programmers_등굣길 {

    private int[][] dp;
    private boolean[][] noway;

    public int solution(int m, int n, int[][] puddles) {

        dp = new int[m+1][n+1];
        noway = new boolean[m+1][n+1];

        dp[0][1] = 1;

        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];

            noway[x][y] = true;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (noway[i][j]) continue;

                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
            }
        }

        return dp[m][n];
    }
}

