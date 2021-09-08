package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_Baekjoon_11057_오르막수 {

    public static void main(String[] args) {

        int[] dp = {1,2,3,4,5,6,7,8,9,10};
        int[] dp2 = {1,2,3,4,5,6,7,8,9,10};
        int answer = 10;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 3; i <= N; i++) {
                for (int j = 1; j < 10; j++) {
                    dp[j] = (dp[j-1] + dp[j]);
                    dp2[j] = (dp2[j-1] + dp2[j]) % 10007;
                }
                System.out.println(Arrays.toString(dp) + " => " + Arrays.stream(dp).sum() + ":" + Arrays.stream(dp).sum() % 10007);
                System.out.println(Arrays.toString(dp2) + " => " + Arrays.stream(dp2).sum());
                Arrays.stream(dp).forEach((value -> System.out.print(value % 10007 + ", ")));
                System.out.println();
            }

            answer = N == 1 ? answer : Arrays.stream(dp).sum() ;

            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
