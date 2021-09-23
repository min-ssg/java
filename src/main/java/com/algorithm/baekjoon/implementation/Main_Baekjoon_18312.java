package com.algorithm.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * # 18312
 * 시각
 * 유형: 구현, 브루트포스
 */
public class Main_Baekjoon_18312 {

    private static int N;
    private static int K;

    public static void main(String[] args) {

        Solution solution = new Solution();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int answer = solution.getAnswer(N, K);

            System.out.println("answer = " + answer);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {

    public int getAnswer(int n, int m) {
        int answer = 0;

        String value = String.valueOf(m);

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    if (checkInValue(i, j, k, m)) {
                        answer += 1;
                    }
                }
            }
        }

        return answer;
    }

    private boolean checkInValue(int i, int j, int k, int value) {
        return i % 10 == value || i / 10 == value
            || j % 10 == value || j / 10 == value
            || k % 10 == value || k / 10 == value;
    }
}
