package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.*;

//https://www.acmicpc.net/problem/14501
public class Main_Baekjoon_14501_퇴사 {

    public static int N;

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            int[] t = new int[N+6];
            int[] p = new int[N+6];
            int[] dp = new int[N+6];
            System.out.println("N = " + N);
            System.out.println("N + 6 = " + (N + 6));
            for (int i = 1; i <= N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                t[i] = Integer.parseInt(stringTokenizer.nextToken());
                p[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            System.out.println(t.length);
            System.out.println(Arrays.toString(t));
            System.out.println(Arrays.toString(p));
            int max = 0;

            for (int i = 1; i <= N+1; i++) {
                System.out.println(i + t[i]);
                dp[i] = max(dp[i], max);
                dp[t[i] + i] = max(dp[t[i] + i], p[i] + dp[i]);
                max = max(dp[i], max);
            }

            System.out.println("MAX= " + max);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
